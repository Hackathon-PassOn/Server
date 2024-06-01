package cmc.cmc15th_hackathon.domain.quiz.service;

import cmc.cmc15th_hackathon.domain.menu.repository.QuizRepository;
import cmc.cmc15th_hackathon.domain.quiz.entity.Quiz;
import cmc.cmc15th_hackathon.domain.quiz.response.QuizResponse;
import cmc.cmc15th_hackathon.global.common.Result;
import cmc.cmc15th_hackathon.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;

    @Transactional(readOnly = true)
    public QuizResponse.Random getRandomQuiz() {
        Quiz quiz = quizRepository.findById(getRandomNumber()).orElseThrow(
                () -> new CustomException(Result.FAIL)
        );

        return QuizResponse.Random.to(quiz.getContent(), quiz.getAnswer());
    }

    public static long getRandomNumber() {
        Random random = new Random();
        return random.nextLong(7) + 1;
    }
}
