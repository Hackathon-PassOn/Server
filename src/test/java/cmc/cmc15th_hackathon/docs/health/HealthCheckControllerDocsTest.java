package cmc.cmc15th_hackathon.docs.health;

import cmc.cmc15th_hackathon.docs.RestDocsSupport;
import cmc.cmc15th_hackathon.domain.health.controller.HealthCheckController;
import cmc.cmc15th_hackathon.domain.health.service.HealthCheckService;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HealthCheckControllerDocsTest extends RestDocsSupport {
    private final HealthCheckService healthCheckService = mock(HealthCheckService.class);

    @Override
    protected Object initController() {
        return new HealthCheckController(healthCheckService);
    }

    @DisplayName("서버 실행 확인 API")
    @Test
    void healthCheck() throws Exception {
        // given
        ResourceSnippetParameters parameters = ResourceSnippetParameters.builder()
                .tag("서버 관리 API")
                .summary("서버 헬스체크 API")
                .responseFields(
                        fieldWithPath("code").type(NUMBER).description("상태 코드"),
                        fieldWithPath("message").type(STRING).description("상태 메시지"),
                        fieldWithPath("data").type(STRING).description("서버 실행 메시지"))
                .build();

        RestDocumentationResultHandler document = documentHandler("saveBookmark", prettyPrint(), parameters);

        // when // then
        mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/check"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document);
    }
}
