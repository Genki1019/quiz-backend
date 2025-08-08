package com.example.quiz.form;

import jakarta.validation.constraints.Size;

/**
 * クイズ検索フォーム
 *
 * @param id          ID
 * @param category    カテゴリ
 * @param question    問題
 * @param answer      解答
 * @param explanation 解説
 */
public record QuizSearchForm(
        Long id,
        Integer category,

        @Size(max = 128, message = "問題は128文字以下で入力してください")
        String question,

        @Size(max = 64, message = "正解は64文字以下で入力してください")
        String answer,

        @Size(max = 256, message = "解説は256文字以下で入力してください")
        String explanation
) {
}
