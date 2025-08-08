package com.example.quiz.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * クイズ登録フォーム
 *
 * @param category    カテゴリ
 * @param question    問題
 * @param answer      正解
 * @param explanation 解説
 */
public record QuizRegistrationForm(
        @NotNull(message = "カテゴリは必ず選択してください")
        Integer category,

        @NotBlank(message = "問題は必ず入力してください")
        @Size(max = 128, message = "問題は128文字以下で入力してください")
        String question,

        @NotBlank(message = "正解は必ず入力してください")
        @Size(max = 64, message = "正解は64文字以下で入力してください")
        String answer,

        @Size(max = 256, message = "解説は256文字以下で入力してください")
        String explanation
) {
}
