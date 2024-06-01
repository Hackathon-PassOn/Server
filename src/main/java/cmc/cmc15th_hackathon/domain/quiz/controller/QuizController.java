package cmc.cmc15th_hackathon.domain.quiz.controller;

import cmc.cmc15th_hackathon.domain.quiz.response.QuizResponse;
import cmc.cmc15th_hackathon.domain.quiz.service.QuizService;
import cmc.cmc15th_hackathon.global.common.CustomResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @GetMapping("/quiz")
    public CustomResponseEntity<QuizResponse.Random> getRandomQuiz() {
        return CustomResponseEntity.success(quizService.getRandomQuiz());
    }
}
