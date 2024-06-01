package cmc.cmc15th_hackathon.domain.gpt.controller;


import cmc.cmc15th_hackathon.domain.gpt.request.GptRequest;
import cmc.cmc15th_hackathon.domain.gpt.request.GptRequest.RandomPayerRequest;
import cmc.cmc15th_hackathon.domain.gpt.response.GptResponse;
import cmc.cmc15th_hackathon.domain.gpt.response.GptResponse.GptPayerResponse;
import cmc.cmc15th_hackathon.domain.gpt.service.GptService;
import cmc.cmc15th_hackathon.global.common.CustomResponseEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/gpt")
@RequiredArgsConstructor
public class GptController {
    private final GptService gptService;

    @PostMapping("/random-payer")
    public CustomResponseEntity<GptPayerResponse> gptRandomPayer(@RequestBody RandomPayerRequest randomPayerRequest) {
        return CustomResponseEntity.success(gptService.getRandomPayer(randomPayerRequest));

    }

    @PostMapping("/random-menu")
    public CustomResponseEntity<GptResponse.GptMenuResponse> gptRandomMenu(
            @RequestBody @Valid GptRequest.RandomMenuRequest randomMenuRequest
    ) {
        return CustomResponseEntity.success(gptService.getRandomMenu(randomMenuRequest));
    }

}
