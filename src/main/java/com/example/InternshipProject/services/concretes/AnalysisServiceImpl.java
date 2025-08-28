package com.example.InternshipProject.services.concretes;

import com.example.InternshipProject.entities.concretes.AnalysisResult;
import com.example.InternshipProject.entities.concretes.Intern;
import com.example.InternshipProject.entities.concretes.InternAnswer;
import com.example.InternshipProject.repositories.AnalysisResultRepository;
import com.example.InternshipProject.repositories.InternAnswerRepository;
import com.example.InternshipProject.repositories.InternRepository;
import com.example.InternshipProject.services.abstracts.AnalysisService;
import com.example.InternshipProject.services.dtos.responses.AnalysisResponse;
import com.example.InternshipProject.services.dtos.requests.AnswerItem;
import com.example.InternshipProject.services.dtos.responses.OverallAnalysis;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    private final InternAnswerRepository answerRepository;
    private final RestTemplate restTemplate;
    private final AnalysisResultRepository resultRepository;
    private final InternRepository internRepository;

    @Value("${analysis.api.url}")
    private String pythonApiUrl;

    public AnalysisServiceImpl(InternAnswerRepository answerRepository,
                               RestTemplate restTemplate,
                               AnalysisResultRepository resultRepository,
                               InternRepository internRepository) {
        this.answerRepository = answerRepository;
        this.restTemplate = restTemplate;
        this.resultRepository = resultRepository;
        this.internRepository = internRepository;
    }

    @Override
    @Transactional
    public AnalysisResponse analyzeInternPerformance(Integer internId, LocalDate date) {
        // Adım 1 & 2: Cevapları hazırla (Değişiklik yok)
        List<InternAnswer> answers = answerRepository.findByInternIdAndAnswerDate(internId, date);
        if (answers.isEmpty()) {
            throw new RuntimeException("Bu tarihe ait analiz edilecek cevap bulunamadı. Stajyer ID: " + internId);
        }
        List<AnswerItem> answerItems = answers.stream()
                .map(ans -> new AnswerItem(ans.getQuestion().getCategory(), ans.getAnswer()))
                .collect(Collectors.toList());

        // Adım 3: Python'a gönderilecek isteği hazırla (Değişiklik yok)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("intern_id", internId);
        try {
            ObjectMapper mapper = new ObjectMapper();
            body.add("answers_json", mapper.writeValueAsString(answerItems));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cevaplar JSON formatına çevrilemedi.", e);
        }
        body.add("pdf_report", new ByteArrayResource("dummy content".getBytes()) {
            @Override
            public String getFilename() { return "dummy.pdf"; }
        });
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // ==========================================================
        // ==     ADIM 4: PYTHON'DAN GELEN HAM VERİYİ YAKALAMA     ==
        // ==========================================================

        // Önce cevabı String olarak alıyoruz.
        ResponseEntity<String> responseAsString = restTemplate.postForEntity(
                pythonApiUrl + "/analyze",
                requestEntity,
                String.class // Cevabı AnalysisResponse yerine String olarak istiyoruz
        );

        String jsonResponse = responseAsString.getBody();

        // --- JSON DEDEKTİFİ LOGLARI ---
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println("--- PYTHON'DAN GELEN HAM JSON CEVABI ---");
        System.out.println(jsonResponse);
        System.out.println("-----------------------------------------------------");

        AnalysisResponse analysisResponse;
        try {
            // Şimdi String'i manuel olarak AnalysisResponse nesnesine çeviriyoruz.
            ObjectMapper mapper = new ObjectMapper();
            analysisResponse = mapper.readValue(jsonResponse, AnalysisResponse.class);
        } catch (JsonProcessingException e) {
            System.out.println("HATA: JSON, AnalysisResponse NESNESİNE ÇEVRİLEMEDİ!");
            e.printStackTrace();
            throw new RuntimeException("Python'dan gelen cevap işlenemedi.", e);
        }

        System.out.println("--- JAVA NESNESİNE ÇEVRİLDİKTEN SONRA ---");
        System.out.println("AnalysisResponse nesnesi null mu?: " + (analysisResponse == null));
        if (analysisResponse != null) {
            System.out.println("OverallAnalysis nesnesi null mu?: " + (analysisResponse.getOverallAnalysis() == null));
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        // --- HATA AYIKLAMA BİTTİ ---

        // Adım 5: Analiz sonucunu veritabanına kaydet.
        if (analysisResponse != null && analysisResponse.getOverallAnalysis() != null) {
            Intern intern = internRepository.findById(internId)
                    .orElseThrow(() -> new RuntimeException("Analiz sonucu kaydedilecek stajyer bulunamadı. ID: " + internId));

            AnalysisResult resultToSave = new AnalysisResult();
            resultToSave.setIntern(intern);
            resultToSave.setAnalysisDate(date);

            OverallAnalysis overall = analysisResponse.getOverallAnalysis();
            resultToSave.setDevelopmentScore(overall.getDevelopmentScore());
            resultToSave.setMotivationScore(overall.getMotivationScore());
            resultToSave.setMotivationStatus(overall.getMotivationStatus());
            resultToSave.setRiskLevel(overall.getRiskLevel());
            resultToSave.setSummary(overall.getSummary());

            resultRepository.save(resultToSave);
            System.out.println("### BAŞARILI: Analiz sonucu, Stajyer ID " + internId + " için veritabanına kaydedildi. ###");
        } else {
            System.out.println("### UYARI: OverallAnalysis nesnesi null olduğu için veritabanına kayıt yapılamadı! ###");
        }

        return analysisResponse;
    } }