package cmc.cmc15th_hackathon.domain.gpt.service;

import cmc.cmc15th_hackathon.domain.gpt.request.GptRequest.GPTRandomPayerCallRequest;
import cmc.cmc15th_hackathon.domain.gpt.request.GptRequest.RandomPayerRequest;
import cmc.cmc15th_hackathon.domain.gpt.response.GptResponse.RandomPayerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GptService {
    @Value("${openai.model}")
    private String model;


    @Value("${openai.api.url}")
    private String apiUrl;


    private final RestTemplate restTemplate;

    public String getRandomPayer(RandomPayerRequest randomPayerRequest){
        String peopleList = randomPayerRequest.getPeopleNameList().toString();
        String content = "오늘 나는 직장 팀원들과 밥을 먹을거야. 누가 계산할 지 너가 공정하게 골라줘야해. 조건 : 인원 중 랜덤으로 1명 선택,이름과 사유(재미있게 부정적인 하루 운세를 담아서) 30자 이내로 출력해줘. 인원 :"+peopleList;
        GPTRandomPayerCallRequest gptRandomPayerCallRequest = new GPTRandomPayerCallRequest(model, content);
        RandomPayerResponse randomPayerResponse = restTemplate.postForObject(apiUrl, gptRandomPayerCallRequest, RandomPayerResponse.class);
        return randomPayerResponse.getChoices().get(0).getMessage().getContent() ;


    }

}
