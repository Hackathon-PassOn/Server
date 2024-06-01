package cmc.cmc15th_hackathon.domain.GPT.controller;


import cmc.cmc15th_hackathon.domain.GPT.request.GPTRequest.RandomPayerRequest;
import cmc.cmc15th_hackathon.domain.GPT.response.GPTResponse.RandomPayerResponse;
import cmc.cmc15th_hackathon.domain.GPT.service.GPTService;
import cmc.cmc15th_hackathon.global.common.CustomResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/gpt")
@RequiredArgsConstructor
public class GPTController {
    private final GPTService gptService;

    @PostMapping("/random-payer")
    public CustomResponseEntity<String> gptRandomPayer(@RequestBody RandomPayerRequest randomPayerRequest){
        return CustomResponseEntity.success(gptService.getRandomPayer(randomPayerRequest));

    }





}
