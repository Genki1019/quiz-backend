package com.example.quiz.repository;

import com.example.quiz.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * クイズリポジトリ
 */
@Repository
public interface QuizRepository extends JpaRepository<QuizEntity, Long> {
    List<QuizEntity> findByCategory(int category);

    @Query("SELECT q FROM QuizEntity q " +
            "WHERE (:id IS NULL OR q.id = :id) " +
            "AND (:category IS NULL OR q.category = :category) " +
            "AND (:question IS NULL OR q.question LIKE CONCAT('%', :question, '%')) " +
            "AND (:answer IS NULL OR q.answer LIKE CONCAT('%', :answer, '%'))" +
            "AND (:explanation IS NULL OR q.explanation LIKE CONCAT('%', :explanation, '%'))")
    List<QuizEntity> searchQuizzes(
            @Param("id") Long id,
            @Param("category") Integer category,
            @Param("question") String question,
            @Param("answer") String answer,
            @Param("explanation") String explanation
    );

    @Query(value = "SELECT * FROM quiz WHERE category = :category ORDER BY RAND() LIMIT 5", nativeQuery = true)
    List<QuizEntity> findRandom5ByCategory(@Param("category") Integer category);
}
