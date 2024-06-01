package cmc.cmc15th_hackathon.domain.gpt.service;

import cmc.cmc15th_hackathon.domain.gpt.request.GptRequest;
import cmc.cmc15th_hackathon.domain.gpt.request.GptRequest.GPTRandomPayerCallRequest;
import cmc.cmc15th_hackathon.domain.gpt.request.GptRequest.RandomPayerRequest;
import cmc.cmc15th_hackathon.domain.gpt.response.GptResponse;
import cmc.cmc15th_hackathon.domain.gpt.response.GptResponse.GptPayerResponse;
import cmc.cmc15th_hackathon.domain.gpt.response.GptResponse.RandomPayerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GptService {
    @Value("${openai.model}")
    private String model;


    @Value("${openai.api.url}")
    private String apiUrl;


    private final RestTemplate restTemplate;

    public GptPayerResponse getRandomPayer(RandomPayerRequest randomPayerRequest) {
        String peopleList = randomPayerRequest.getPeopleNameList().toString();
        String content = "오늘 나는 직장 팀원들과 밥을 먹을거야. 누가 계산할 지 너가 공정하게 골라줘야해. 조건 : 인원 중 랜덤으로 1명 선택,이름과 사유(재미있게 부정적인 하루 운세를 담아서) 30자 이내로 출력해줘. 인원 :" + peopleList;
        GPTRandomPayerCallRequest gptRandomPayerCallRequest = new GPTRandomPayerCallRequest(model, content);
        RandomPayerResponse randomPayerResponse = restTemplate.postForObject(apiUrl, gptRandomPayerCallRequest, RandomPayerResponse.class);
        return GptPayerResponse.response(randomPayerResponse.getChoices().get(0).getMessage().getContent());


    }

    public GptResponse.GptMenuResponse getRandomMenu(GptRequest.RandomMenuRequest randomMenuRequest) {
        List<String> menuNameList = randomMenuRequest.getMenuNameList();
        String menuList = menuNameList.toString();
        String content = "오늘 나는 직장 팀원들과 밥을 먹을거야. 어떤 음식을 먹을지 너가 랜덤하게 골라줘야해. 조건 : 메뉴 중 랜덤으로 1명개 선택, 메뉴와 사유(재미있게 유머있는 이유를 창작해서) 30자 이내로 출력해줘. 메뉴 :" + menuList;
        GPTRandomPayerCallRequest gptRandomMenuCallRequest = new GPTRandomPayerCallRequest(model, content);
        RandomPayerResponse randomPayerResponse = restTemplate.postForObject(apiUrl, gptRandomMenuCallRequest, RandomPayerResponse.class);

        String result = randomPayerResponse.getChoices().get(0).getMessage().getContent();
        String title = getTitle(menuNameList, result);

        return GptResponse.GptMenuResponse.response(title, result);
    }

    private static String getTitle(List<String> menuNameList, String result) {
        String title = "오늘의 메뉴는 없습니다.";
        for (String menuName : menuNameList) {
            if (result.contains(menuName)) {
                title = String.format("오늘의 메뉴는 %s!", menuName);
                break;
            }
        }
        return title;
    }
}
