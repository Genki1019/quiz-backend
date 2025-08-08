package com.example.quiz.controller;

import com.example.quiz.dto.QuizResponseDto;
import com.example.quiz.form.QuizRegistrationForm;
import com.example.quiz.form.QuizSearchForm;
import com.example.quiz.form.QuizUpdateForm;
import com.example.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * クイズコントローラー
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/quiz")
public class QuizController {
    private final QuizService quizService;

    /**
     * クイズ登録API
     *
     * @param quizRegistrationForm クイズ登録フォーム
     * @return クイズレスポンスDTO
     */
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public QuizResponseDto register(
            @Validated QuizRegistrationForm quizRegistrationForm
    ) {
        return quizService.registerQuiz(quizRegistrationForm);
    }

    /**
     * クイズ取得API（1件）
     *
     * @param id ID
     * @return クイズレスポンスDTO
     */
    @GetMapping("/{id}")
    public QuizResponseDto getQuiz(@PathVariable("id") long id) {
        return quizService.getQuizById(id);
    }

    /**
     * クイズ取得API（複数件）
     *
     * @param quizSearchForm クイズ検索フォーム
     * @return クイズレスポンスDTOリスト
     */
    @GetMapping("/")
    public List<QuizResponseDto> getQuizzes(@Validated QuizSearchForm quizSearchForm) {
        return quizService.searchQuizzes(quizSearchForm);
    }

    /**
     * クイズ更新API
     *
     * @param id             ID
     * @param quizUpdateForm クイズ更新フォーム
     * @return クイズレスポンスDTO
     */
    @PutMapping("/{id}")
    public QuizResponseDto updateQuiz(@PathVariable("id") long id,
                                      @Validated QuizUpdateForm quizUpdateForm) {
        return quizService.updateQuiz(id, quizUpdateForm);
    }

    /**
     * クイズ削除API
     *
     * @param id ID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuiz(@PathVariable("id") long id) {
        quizService.deleteQuiz(id);
    }

    /**
     * クイズランダム取得API
     *
     * @param category カテゴリ（番号）
     * @return クイズレスポンスDTOリスト
     */
    @GetMapping("/random")
    public List<QuizResponseDto> getRandomQuizzes(@RequestParam Integer category) {
        return quizService.getCategoryRandom(category);
    }
}