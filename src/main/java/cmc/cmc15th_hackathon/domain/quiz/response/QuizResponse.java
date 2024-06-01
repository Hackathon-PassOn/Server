package cmc.cmc15th_hackathon.domain.quiz.response;

import lombok.*;

public class QuizResponse {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    public static class Random {
        private String content;
        private String answer;

        public static QuizResponse.Random to(String content, String answer) {
            return Random.builder()
                    .content(content)
                    .answer(answer)
                    .build();
        }
    }
}
