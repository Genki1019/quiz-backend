package com.example.quiz.dto;

public record ApiErrorResponseDto(
        String message,
        ApiDetailErrorResponseDto apiDetailErrorResponseDto
) {
    public ApiErrorResponseDto(String message) {
        this(message, null);
    }
}
