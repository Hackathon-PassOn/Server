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

    public GptResponse.GptPayerResponse getRandomPayer(RandomPayerRequest randomPayerRequest) {
        List<String> peopleNameList = randomPayerRequest.getPeopleNameList();
        String peopleList = peopleNameList.toString();
        String content = String.format("이름 : [%s]\n 조건 : 이름에서 1명 선택\n 이름과 사유(재미있게 부정적인 하루 운세를 담아서) 30자 이내로 출력해줘.", peopleList);
        GPTRandomPayerCallRequest gptRandomPayerCallRequest = new GPTRandomPayerCallRequest(model, content);
        RandomPayerResponse randomPayerResponse = restTemplate.postForObject(apiUrl, gptRandomPayerCallRequest, RandomPayerResponse.class);
        String result = randomPayerResponse.getChoices().get(0).getMessage().getContent();
        String title = getPeopleTitle(peopleNameList, result);
        return GptResponse.GptPayerResponse.response(title, result);

    }

    public GptResponse.GptMenuResponse getRandomMenu(GptRequest.RandomMenuRequest randomMenuRequest) {
        List<String> menuNameList = randomMenuRequest.getMenuNameList();
        String menuList = menuNameList.toString();
        String content = String.format("메뉴 : [%s]\n 조건 : 메뉴에서 1개 선택\n 메뉴와 사유(재미있게 유머있는 이유를 창작해서) 30자 이내로 출력해줘.", menuList);
        GPTRandomPayerCallRequest gptRandomMenuCallRequest = new GPTRandomPayerCallRequest(model, content);
        RandomPayerResponse randomPayerResponse = restTemplate.postForObject(apiUrl, gptRandomMenuCallRequest, RandomPayerResponse.class);

        String result = randomPayerResponse.getChoices().get(0).getMessage().getContent();
        String title = getMenuTitle(menuNameList, result);

        return GptResponse.GptMenuResponse.response(title, result);
    }

    private static String getMenuTitle(List<String> menuNameList, String result) {
        String title = "오늘의 메뉴는 없습니다.";
        for (String menuName : menuNameList) {
            if (result.contains(menuName)) {
                title = menuName;
                break;
            }
        }
        return title;
    }

    private static String getPeopleTitle(List<String> peopleNameList, String result) {
        String title = "";
        for (String peopleName : peopleNameList) {
            if (result.contains(peopleName)) {
                title = peopleName+"님";
                break;
            }
        }
        return title;
    }
}
