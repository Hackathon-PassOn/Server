package cmc.cmc15th_hackathon.domain.GPT.request;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class GPTRequest {
    @Getter
    public static class Messages{
        private String role;
        private String content;
        public Messages(String role, String content){
            this.role = role;
            this.content = content;
        }
    }
    @Getter
    @NoArgsConstructor
    public static class RandomPayerRequest{
        private List<String> peopleNameList;
    }

    @Getter
    @NoArgsConstructor
    public static class GPTRandomPayerCallRequest{
        private String model;
        private List<Messages> messages;

        public GPTRandomPayerCallRequest(String model, String prompt){
            this.model = model;
            this.messages = new ArrayList<>();
            this.messages.add(new Messages("user", prompt));
        }
    }





}
