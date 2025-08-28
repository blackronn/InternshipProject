
package com.example.InternshipProject.services.dtos.responses;

import lombok.Data;

@Data
public class FaqResponse {
    private Integer id;
    private String question;
    private String answer;
}
