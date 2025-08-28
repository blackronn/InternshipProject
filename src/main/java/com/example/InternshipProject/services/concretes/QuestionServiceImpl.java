package com.example.InternshipProject.services.concretes;

import com.example.InternshipProject.entities.concretes.DailyQuestion;
import com.example.InternshipProject.entities.concretes.Intern;
import com.example.InternshipProject.entities.concretes.InternAnswer;
import com.example.InternshipProject.entities.concretes.Question;
import com.example.InternshipProject.repositories.DailyQuestionRepository;
import com.example.InternshipProject.repositories.InternAnswerRepository;
import com.example.InternshipProject.repositories.InternRepository;
import com.example.InternshipProject.repositories.QuestionRepository;
import com.example.InternshipProject.services.abstracts.QuestionService;
import com.example.InternshipProject.services.dtos.requests.SubmitAnswerRequest;
import com.example.InternshipProject.services.dtos.responses.QuestionResponse;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final DailyQuestionRepository dailyQuestionRepository;
    private final InternRepository internRepository;
    private final InternAnswerRepository internAnswerRepository;
    private static final List<String> CATEGORIES = List.of("Proaktiflik", "İşbirliği / İletişim", "Süreç Memnuniyeti");


    public QuestionServiceImpl(QuestionRepository questionRepository,
                               DailyQuestionRepository dailyQuestionRepository,
                               InternRepository internRepository,
                               InternAnswerRepository internAnswerRepository) {
        this.questionRepository = questionRepository;
        this.dailyQuestionRepository = dailyQuestionRepository;
        this.internRepository = internRepository; // EKLENDİ
        this.internAnswerRepository = internAnswerRepository; // EKLENDİ
    }


    @Override
    public List<QuestionResponse> getTodaysQuestionsForDisplay() {
        // Bu metot, dış dünyaya (Controller'a) sunulan metottur.
        // İçerideki private metodu çağırır ve sonucu DTO'ya çevirir.
        return getTodaysQuestionsInternal().stream()
                .map(q -> new QuestionResponse(q.getId(), q.getText(), q.getCategory()))
                .collect(Collectors.toList());
    }

    // Bu metodu 'private' yaparak, sadece bu sınıfın içinden erişilebilir hale getiriyoruz.
    private List<Question> getTodaysQuestionsInternal() {
        LocalDate today = LocalDate.now();
        List<DailyQuestion> todaysQuestions = dailyQuestionRepository.findByDate(today);

        if (todaysQuestions.isEmpty()) {
            selectAndSaveTodaysQuestions();
            todaysQuestions = dailyQuestionRepository.findByDate(today);
        }

        return todaysQuestions.stream()
                .map(DailyQuestion::getQuestion)
                .collect(Collectors.toList());
    }

    // Her gün gece yarısı 00:01'de çalışarak yeni günün sorularını seçer.
    @Scheduled(cron = "0 1 0 * * ?")
    public void selectAndSaveTodaysQuestions() {
        System.out.println("Yeni günün soruları seçiliyor...");
        LocalDate today = LocalDate.now();

        // O gün için eski bir kayıt varsa diye temizle (opsiyonel ama güvenli)
        dailyQuestionRepository.deleteAll(dailyQuestionRepository.findByDate(today));

        List<DailyQuestion> newDailyQuestions = new ArrayList<>();
        for (String category : CATEGORIES) {
            List<Question> questionsInCategory = questionRepository.findByCategory(category);
            if (!questionsInCategory.isEmpty()) {
                // Kategoriye ait soruları karıştır ve ilkini al (rastgele seçim).
                Collections.shuffle(questionsInCategory);
                Question selectedQuestion = questionsInCategory.get(0);

                DailyQuestion dailyQuestion = new DailyQuestion();
                dailyQuestion.setQuestion(selectedQuestion);
                dailyQuestion.setDate(today);
                newDailyQuestions.add(dailyQuestion);
            }
        }
        dailyQuestionRepository.saveAll(newDailyQuestions);
        System.out.println("Günün soruları başarıyla seçildi ve kaydedildi.");
    }

        @Override
        @Transactional // Birden fazla kaydı tek işlemde yapmak için
        public void submitAnswers(List<SubmitAnswerRequest> requests) {
            LocalDate today = LocalDate.now();
            List<InternAnswer> answersToSave = new ArrayList<>();

            for (SubmitAnswerRequest req : requests) {
                // Basitlik adına, stajyer ve sorunun var olduğunu varsayıyoruz.
                // Gerçek bir projede findById ile kontrol eklenmelidir.
                Intern intern = internRepository.getReferenceById(req.getInternId());
                Question question = questionRepository.getReferenceById(req.getQuestionId());

                InternAnswer internAnswer = new InternAnswer();
                internAnswer.setIntern(intern);
                internAnswer.setQuestion(question);
                internAnswer.setAnswer(req.getAnswer());
                internAnswer.setAnswerDate(today);
                answersToSave.add(internAnswer);
            }
            internAnswerRepository.saveAll(answersToSave);
        }

    @Scheduled(cron = "0 0 2 * * ?")
    @Transactional
    public void cleanupOldInternAnswers() {
        System.out.println("ZAMANLANMIŞ GÖREV: Eski stajyer cevapları temizleniyor...");

        // Repository'deki yeni metodumuzu çağırarak, bugünden önceki tüm cevapları siliyoruz.
        internAnswerRepository.deleteByAnswerDateBefore(LocalDate.now());

        System.out.println("Dünden önceki tüm cevaplar başarıyla silindi.");
    }

}