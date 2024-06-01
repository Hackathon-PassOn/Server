package cmc.cmc15th_hackathon.domain.menu.repository;

import cmc.cmc15th_hackathon.domain.quiz.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
