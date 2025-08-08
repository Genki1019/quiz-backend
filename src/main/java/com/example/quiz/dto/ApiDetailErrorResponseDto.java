package com.example.quiz.dto;

import lombok.Data;

@Data
public class ApiDetailErrorResponseDto {
    private String question;
    private String answer;
    private String explanation;
}
