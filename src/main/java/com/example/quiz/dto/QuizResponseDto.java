package com.example.quiz.dto;


import com.example.quiz.entity.QuizEntity;
import com.example.quiz.enumerate.Category;

import java.time.LocalDateTime;

/**
 * クイズレスポンスDTO
 *
 * @param id          クイズID
 * @param category    　カテゴリ
 * @param question    　問題
 * @param answer      解答
 * @param explanation 解説
 * @param createdAt   登録日時
 * @param updatedAt   更新日時
 */
public record QuizResponseDto(
        long id,
        String category,
        String question,
        String answer,
        String explanation,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static QuizResponseDto of(QuizEntity quizEntity) {
        return new QuizResponseDto(
                quizEntity.getId(),
                Category.valueFromKey(quizEntity.getCategory()),
                quizEntity.getQuestion(),
                quizEntity.getAnswer(),
                quizEntity.getExplanation(),
                quizEntity.getCreatedAt(),
                quizEntity.getUpdatedAt()
        );
    }
}
