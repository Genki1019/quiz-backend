package com.example.quiz.service;

import com.example.quiz.dto.QuizResponseDto;
import com.example.quiz.entity.QuizEntity;
import com.example.quiz.exception.QuizNotFoundException;
import com.example.quiz.form.QuizRegistrationForm;
import com.example.quiz.form.QuizSearchForm;
import com.example.quiz.form.QuizUpdateForm;
import com.example.quiz.repository.QuizRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

/**
 * クイズサービス
 */
@Service
@Transactional
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;

    /**
     * クイズを登録
     *
     * @param quizRegistrationForm クイズ登録フォーム
     * @return クイズレスポンスDTO
     */
    public QuizResponseDto registerQuiz(QuizRegistrationForm quizRegistrationForm) {
        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setCategory(quizRegistrationForm.category());
        quizEntity.setQuestion(quizRegistrationForm.question());
        quizEntity.setAnswer(quizRegistrationForm.answer());
        quizEntity.setExplanation(quizRegistrationForm.explanation());
        quizRepository.save(quizEntity);

        return QuizResponseDto.of(quizEntity);
    }

    /**
     * クイズを1件取得
     *
     * @param id ID
     * @return クイズレスポンスDTO
     */
    public QuizResponseDto getQuizById(long id) {
        return QuizResponseDto.of(getQuizEntityById(id));
    }

    /**
     * クイズを更新
     *
     * @param id             ID
     * @param quizUpdateForm クイズ更新フォーム
     * @return クイズレスポンスDTO
     */
    public QuizResponseDto updateQuiz(long id, QuizUpdateForm quizUpdateForm) {
        QuizEntity quizEntity = getQuizEntityById(id);

        updateIfNotBlank(quizUpdateForm.category(), quizEntity::setCategory);
        updateIfNotBlank(quizUpdateForm.question(), quizEntity::setQuestion);
        updateIfNotBlank(quizUpdateForm.answer(), quizEntity::setAnswer);
        updateIfNotBlank(quizUpdateForm.explanation(), quizEntity::setExplanation);

        return QuizResponseDto.of(quizRepository.save(quizEntity));
    }

    /**
     * 条件に該当するクイズを取得する
     *
     * @param quizSearchForm クイズ検索フォーム
     * @return クイズレスポンスDTOリスト
     */
    public List<QuizResponseDto> searchQuizzes(QuizSearchForm quizSearchForm) {
        return quizRepository.searchQuizzes(
                        quizSearchForm.id(),
                        quizSearchForm.category(),
                        quizSearchForm.question(),
                        quizSearchForm.answer(),
                        quizSearchForm.explanation()
                )
                .stream()
                .map(QuizResponseDto::of)
                .toList();
    }

    /**
     * クイズを削除
     *
     * @param id ID
     */
    public void deleteQuiz(long id) {
        quizRepository.deleteById(id);
    }

    /**
     * カテゴリ内からクイズを指定した件数だけ取得
     *
     * @param category カテゴリ（番号）
     * @return クイズレスポンスDTOリスト
     */
    public List<QuizResponseDto> getCategoryRandom(Integer category) {
        return quizRepository.findRandom5ByCategory(category)
                .stream()
                .map(QuizResponseDto::of)
                .toList();
    }

    /**
     * IDからクイズエンティティを取得する
     *
     * @param id ID
     * @return クイズエンティティ
     */
    private QuizEntity getQuizEntityById(long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("ID:" + id + "のクイズは存在しません"));
    }

    /**
     * 値が空でない場合に更新処理を実行
     *
     * @param value   更新する値
     * @param updater 更新処理
     */
    private void updateIfNotBlank(Integer value, Consumer<Integer> updater) {
        if (value != null) {
            updater.accept(value);
        }
    }

    /**
     * 値が空でない場合に更新処理を実行
     *
     * @param value   更新する値
     * @param updater 更新処理
     */
    private void updateIfNotBlank(String value, Consumer<String> updater) {
        if (StringUtils.isNotBlank(value)) {
            updater.accept(value);
        }
    }
}
