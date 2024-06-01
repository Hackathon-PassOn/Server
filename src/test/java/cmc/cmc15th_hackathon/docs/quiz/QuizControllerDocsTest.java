package cmc.cmc15th_hackathon.docs.quiz;

import cmc.cmc15th_hackathon.docs.RestDocsSupport;
import cmc.cmc15th_hackathon.domain.quiz.controller.QuizController;
import cmc.cmc15th_hackathon.domain.quiz.response.QuizResponse;
import cmc.cmc15th_hackathon.domain.quiz.service.QuizService;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QuizControllerDocsTest extends RestDocsSupport {
    private final QuizService quizService = mock(QuizService.class);

    @Override
    protected Object initController() {
        return new QuizController(quizService);
    }

    @DisplayName("랜덤 퀴즈 요청 API")
    @Test
    void getRandomQuiz() throws Exception {
        // given
        given(quizService.getRandomQuiz())
                .willReturn(QuizResponse.Random.builder()
                        .content("7*6?")
                        .answer("42")
                        .build());

        ResourceSnippetParameters parameters = ResourceSnippetParameters.builder()
                .tag("퀴즈 API")
                .summary("랜덤 퀴즈 요청 API")
                .responseFields(
                        fieldWithPath("code").type(NUMBER).description("상태 코드"),
                        fieldWithPath("message").type(STRING).description("상태 메시지"),
                        fieldWithPath("data.content").type(STRING).description("퀴즈 내용"),
                        fieldWithPath("data.answer").type(STRING).description("퀴즈 정답"))
                .build();

        RestDocumentationResultHandler document = documentHandler("getRandomQuiz", prettyPrint(), parameters);

        // when // then
        mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/quiz"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document);
    }
}
