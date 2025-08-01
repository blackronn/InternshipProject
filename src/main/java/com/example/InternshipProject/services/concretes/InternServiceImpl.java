package com.example.InternshipProject.services.concretes;

import com.example.InternshipProject.entities.concretes.InternMentorRelation;
import com.example.InternshipProject.entities.concretes.Mentor;
import com.example.InternshipProject.entities.concretes.Office;
import com.example.InternshipProject.repositories.InternMentorRelRepository;
import com.example.InternshipProject.repositories.OfficeRepository;
import com.example.InternshipProject.services.abstracts.InternService;
import com.example.InternshipProject.services.dtos.requests.CreateInternRequest;
import com.example.InternshipProject.services.dtos.responses.OfficeResponse;
import org.springframework.stereotype.Service;
import com.example.InternshipProject.entities.concretes.Intern;
import com.example.InternshipProject.repositories.InternRepository;
import com.example.InternshipProject.services.dtos.responses.InternResponse;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class InternServiceImpl implements InternService {

    private final InternRepository internRepository;
    private final InternMentorRelRepository relationRepository;
    private final OfficeRepository officeRepository;

    public InternServiceImpl(InternRepository internRepository, InternMentorRelRepository relationRepository,OfficeRepository officeRepository) {
        this.internRepository = internRepository;
        this.relationRepository = relationRepository;
        this.officeRepository = officeRepository;
    }


    @Override
    public Intern getInternByID(int id) {
        return internRepository.findById(id).orElse(null);
    }
    @Override
    public void addIntern(CreateInternRequest request) {
        Intern intern = new Intern();

        intern.setName(request.getName());
        intern.setSurname(request.getSurname());
        intern.setEmail(request.getEmail());
        intern.setPhoneNumber(request.getPhoneNumber());
        intern.setPassword(request.getPassword());
        intern.setStartDate(request.getStartDate());
        intern.setEndDate(request.getEndDate());
        intern.setUniversity(request.getUniversity());
        intern.setDepartment(request.getDepartment());

        intern.setIsActive(1);

        internRepository.save(intern);
    }

    @Override
    public Intern deleteInternByID(int id) {
        Intern intern = getInternByID(id);
        internRepository.deleteById(id);
        return intern;
    }

    @Override
    public Intern updateIntern(int id, Intern internDetails) {
        Intern intern = getInternByID(id);

        intern.setName(internDetails.getName());
        intern.setSurname(internDetails.getSurname());
        intern.setEmail(internDetails.getEmail());
        intern.setPhoneNumber(internDetails.getPhoneNumber());
        intern.setPassword(internDetails.getPassword());
        intern.setStartDate(internDetails.getStartDate());
        intern.setEndDate(internDetails.getEndDate());
        intern.setUniversity(internDetails.getUniversity());
        intern.setDepartment(internDetails.getDepartment());

        return internRepository.save(intern);
    }
    @Override
    public List<Intern> getAllInterns() {
        // Repository nesnesi üzerinden findAll() metodunu çağırıyoruz.
        return this.internRepository.findAll();
    }
    @Override
    public InternResponse getByEmailAndSyncOffice(String email, String officeLocation) {
        Intern intern = internRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("Stajyer bulunamadı: " + email));

        // Eğer stajyerin ofisi atanmamışsa VE dışarıdan bir officeLocation geldiyse atama yap
        if (intern.getOffice() == null && officeLocation != null && !officeLocation.isEmpty()) {
            Office officeToAssign = officeRepository.findByName(officeLocation)
                    .orElseThrow(() -> new RuntimeException("Veritabanında ofis bulunamadı: " + officeLocation));

            intern.setOffice(officeToAssign);
            internRepository.save(intern);
        }

        // DTO dönüşümünü merkezi yardımcı metoda devrediyoruz
        return convertToResponse(intern);
    }
    @Override
    public boolean existsByEmail(String email) {
        return internRepository.existsByEmail(email);
    }

    @Override
    public List<InternResponse> findInternsByMentorId(Integer mentorId) {
        // 1. Adım: Repository'yi çağırarak veritabanından Intern entity'lerini al.
        List<Intern> interns = internRepository.findInternsByMentorId(mentorId);

        // 2. Adım: Gelen entity listesini DTO listesine dönüştür. (DTO Mapping)
        // Bu, API'nızın sadece gerekli verileri dışarıya açmasını sağlar.
        return interns.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Bu yardımcı metot, bir Intern entity'sini InternResponse DTO'suna çevirir.
    private InternResponse convertToResponse(Intern intern) {
        InternResponse response = new InternResponse();
        response.setId(intern.getId());
        response.setName(intern.getName());
        response.setSurname(intern.getSurname());
        response.setEmail(intern.getEmail());
        response.setUniversity(intern.getUniversity());
        response.setDepartment(intern.getDepartment());
        response.setManagerEmail(intern.getManagerEmail());
        response.setStartDate(
                intern.getStartDate() != null ? intern.getStartDate().toString() : null
        );
        response.setEndDate(
                intern.getEndDate() != null ? intern.getEndDate().toString() : null
        );
        // ... InternResponse'da olmasını istediğiniz diğer alanlar ...

        if (intern.getOffice() != null) {
            OfficeResponse officeDto = new OfficeResponse();
            officeDto.setId(intern.getOffice().getId());
            officeDto.setName(intern.getOffice().getName());
            response.setOffice(officeDto);
        }

        // Mentor bilgisini de bu merkezi metoda taşıyoruz
        List<InternMentorRelation> relations = relationRepository.findByIntern(intern);
        if (relations != null && !relations.isEmpty()) {
            Mentor mentor = relations.get(0).getMentor();
            response.setMentorName(mentor.getName() + " " + mentor.getSurname());
            response.setMentorEmail(mentor.getEmail());
        }

        return response;
    }
}
