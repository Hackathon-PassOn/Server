package cmc.cmc15th_hackathon.docs.gpt;

import static cmc.cmc15th_hackathon.domain.gpt.request.GptRequest.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cmc.cmc15th_hackathon.docs.RestDocsSupport;
import cmc.cmc15th_hackathon.domain.gpt.controller.GptController;
import cmc.cmc15th_hackathon.domain.gpt.request.GptRequest.RandomPayerRequest;
import cmc.cmc15th_hackathon.domain.gpt.response.GptResponse;
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
                                fieldWithPath("data.title").type(JsonFieldType.STRING)
                                        .description("제목"),
                                fieldWithPath("data.content").type(JsonFieldType.STRING)
                                        .description("출력값")
                        )
                        .build();
        RestDocumentationResultHandler document = documentHandler(
                "get-random-payer-api", prettyPrint(), prettyPrint(), parameters);

        BDDMockito.given(gptService.getRandomPayer(any(RandomPayerRequest.class)))
                .willReturn(GptResponse.GptPayerResponse.builder()
                        .title("축하합니다 미리님!")
                        .content("오늘 계산할 사람은 미리입니다. 왜냐면 지갑이 텅텅?")
                        .build());
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

    @DisplayName("랜덤 메뉴 뽑기 API")
    @Test
    void gptRandomMenu() throws Exception {
        //given
        BDDMockito.given(gptService.getRandomMenu(any(RandomMenuRequest.class)))
                .willReturn(GptResponse.GptMenuResponse.builder()
                        .title("오늘의 메뉴는 마라탕!")
                        .content("마라탕이 선정된 이유는 그냥 맛있어서 입니다...")
                        .build());

        RandomMenuRequest request = new RandomMenuRequest(List.of("마라탕","짜장면","돼지갈비","파스타"));

        ResourceSnippetParameters parameters = ResourceSnippetParameters.builder()
                .tag("GPT 호출 API")
                .summary("랜덤 메뉴 뽑기")
                .requestFields(
                        fieldWithPath("menuNameList").type(JsonFieldType.ARRAY)
                                .description("메뉴 이름 리스트")
                )
                .responseFields(
                        fieldWithPath("code").type(JsonFieldType.NUMBER)
                                .description("상태코드"),
                        fieldWithPath("message").type(JsonFieldType.STRING)
                                .description("응답 메시지"),
                        fieldWithPath("data.title").type(JsonFieldType.STRING)
                                .description("제목"),
                        fieldWithPath("data.content").type(JsonFieldType.STRING)
                                .description("내용"))
                .build();
        RestDocumentationResultHandler document = documentHandler(
                "get-random-menu-api", prettyPrint(), prettyPrint(), parameters);

        //when //then
        mockMvc.perform(
                        RestDocumentationRequestBuilders.post("/gpt/random-menu")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document);
    }
}
