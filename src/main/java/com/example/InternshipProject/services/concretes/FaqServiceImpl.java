// src/main/java/com/example/InternshipProject/services/concretes/FaqServiceImpl.java
package com.example.InternshipProject.services.concretes;

import com.example.InternshipProject.entities.concretes.Faq;
import com.example.InternshipProject.repositories.FaqRepository;
import com.example.InternshipProject.services.abstracts.FaqService;
import com.example.InternshipProject.services.dtos.responses.FaqResponse;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FaqServiceImpl implements FaqService {

    private final FaqRepository faqRepository;

    public FaqServiceImpl(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    @Override
    public List<FaqResponse> getAllFaqs() {
        List<Faq> faqs = faqRepository.findAll();
        return faqs.stream()
                .map(this::convertToFaqResponse)
                .collect(Collectors.toList());
    }

    private FaqResponse convertToFaqResponse(Faq faq) {
        FaqResponse response = new FaqResponse();
        response.setId(faq.getId());
        response.setQuestion(faq.getQuestion());
        response.setAnswer(faq.getAnswer());
        return response;
    }
}