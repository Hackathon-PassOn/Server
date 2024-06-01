package cmc.cmc15th_hackathon.domain.gpt.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@Getter
public class GptResponse {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RandomPayerResponse{
        @JsonProperty("choices")
        private List<Choice> choices = new ArrayList<>();
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Messages{
            private String role;
            private String content;

        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Choice {
            private int index;
            private Messages message;
        }

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class GptPayerResponse{
        private String content;

        public static GptPayerResponse response(String content){
            return GptPayerResponse.builder()
                    .content(content)
                    .build();
        }



    }

}
