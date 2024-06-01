package cmc.cmc15th_hackathon.docs.gpt;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cmc.cmc15th_hackathon.docs.RestDocsSupport;
import cmc.cmc15th_hackathon.domain.gpt.controller.GptController;
import cmc.cmc15th_hackathon.domain.gpt.request.GptRequest;
import cmc.cmc15th_hackathon.domain.gpt.request.GptRequest.RandomPayerRequest;
import cmc.cmc15th_hackathon.domain.gpt.service.GptService;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

public class gptControllerDocsTest extends RestDocsSupport {
    private final GptService gptService = mock(GptService.class);


    @Override
    protected Object initController() {
        return new GptController(gptService);
    }

    @DisplayName("랜덤 사용자 뽑기 API")
    @Test
    void gptRandomPayer() throws Exception {
        RandomPayerRequest request = new RandomPayerRequest(List.of("미리", "덕배", "헤일리"));

        //given
        ResourceSnippetParameters parameters = ResourceSnippetParameters.builder()
                        .tag("GPT 호출 API")
                        .summary("랜덤 사용자 뽑기")
                        .requestFields(
                                fieldWithPath("peopleNameList").type(JsonFieldType.ARRAY)
                                        .description("사람 이름 리스트")
                        )
                        .responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("상태코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("응답 메시지"),
                                fieldWithPath("data").type(JsonFieldType.STRING)
                                        .description("출력값")
                        )
                        .build();
        RestDocumentationResultHandler document = documentHandler(
                "get-random-payer-api", prettyPrint(), prettyPrint(), parameters);

        BDDMockito.given(gptService.getRandomPayer(any(GptRequest.RandomPayerRequest.class)))
                .willReturn("덕배가 계산합니다. 오늘 지갑이 가벼워질 운세입니다.");
        //when //then
        mockMvc.perform(
                RestDocumentationRequestBuilders.post("/gpt/random-payer")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document);
    }
}
