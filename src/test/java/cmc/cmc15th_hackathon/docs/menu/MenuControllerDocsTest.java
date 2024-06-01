package cmc.cmc15th_hackathon.docs.menu;

import cmc.cmc15th_hackathon.docs.RestDocsSupport;
import cmc.cmc15th_hackathon.domain.menu.controller.MenuController;
import cmc.cmc15th_hackathon.domain.menu.response.RestaurantsResponse;
import cmc.cmc15th_hackathon.domain.menu.service.MenuService;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static com.epages.restdocs.apispec.Schema.schema;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.JsonFieldType.ARRAY;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MenuControllerDocsTest extends RestDocsSupport {
    private final MenuService menuService = mock(MenuService.class);

    @Override
    protected Object initController() {
        return new MenuController(menuService);
    }

    @DisplayName("모임 장소 추천 조회 리스트 API")
    @Test
    void keywordCentralizedMeetingSpot() throws Exception {
        // given
        RestaurantsResponse place1 = RestaurantsResponse.builder()
                .title("멘야하나비 성신여대점")
                .thumUrl("https://ldb-phinf.pstatic.net/20230804_174/16911100078193yaWQ_JPEG/IMG_4118.JPEG")
                .distance("성신여대입구역(으)로부터 220m")
                .openTime("21:00에 라스트오더")
                .tel("02-6397-3020")
                .detail(
                        RestaurantsResponse.Detail.builder()
                                .local("성신여대입구역")
                                .title("멘야하나비 성신여대점")
                                .address("서울특별시 성북구 동소문로22길 39-5 2층")
                                .status("영업 중")
                                .openTime("21:00에 라스트오더")
                                .homePageUrl("")
                                .tel("02-6397-3020")
                                .category(List.of("일식", "일본식라면"))
                                .x("127.0186061")
                                .y("37.5914413")
                                .thumUrls(List.of(
                                        "https://ldb-phinf.pstatic.net/20230804_174/16911100078193yaWQ_JPEG/IMG_4118.JPEG",
                                        "https://ldb-phinf.pstatic.net/20230804_278/1691110001584jSktH_JPEG/IMG_4117.JPEG",
                                        "https://ldb-phinf.pstatic.net/20230324_57/1679617040749YQEdq_JPEG/KakaoTalk_20230323_100524740.jpg"
                                ))
                                .menuInfo(List.of(
                                        "마제소바 11,000",
                                        "도니꾸 마제소바 14,000",
                                        "네기시오 마제소바 14,000",
                                        "스파이시 마제소바 12,000",
                                        "소유라멘 10,000",
                                        "카레마제소바 12,000"
                                ))
                                .build()
                )
                .build();

        RestaurantsResponse place2 = RestaurantsResponse.builder()
                .title("치치 성신여대점")
                .thumUrl("https://ldb-phinf.pstatic.net/20230704_152/1688449596232YkYod_JPEG/3.jpg")
                .distance("성신여대입구역(으)로부터 213m")
                .openTime("17:30에 영업시작")
                .tel("02-921-8520")
                .detail(
                        RestaurantsResponse.Detail.builder()
                                .local("성신여대입구역")
                                .title("치치 성신여대점")
                                .address("서울특별시 성북구 동소문로20길 37-12 1층")
                                .status("곧 영업 시작")
                                .openTime("17:30에 영업시작")
                                .homePageUrl("http://www.chi-chi.co.kr/")
                                .tel("02-921-8520")
                                .category(List.of("술집", "요리주점"))
                                .x("127.0175747")
                                .y("37.5909647")
                                .thumUrls(List.of(
                                        "https://ldb-phinf.pstatic.net/20230704_152/1688449596232YkYod_JPEG/3.jpg",
                                        "https://ldb-phinf.pstatic.net/20230630_204/1688116794177JlDTz_JPEG/20230630_162032.jpg",
                                        "https://ldb-phinf.pstatic.net/20230704_54/16884496305868jaey_JPEG/1688297595651.jpg"
                                ))
                                .menuInfo(List.of(
                                        "버터갈릭감자 변동가격(업주문의)",
                                        "설탕토마토 변동가격(업주문의)"
                                ))
                                .build())
                .build();

        RestaurantsResponse place3 = RestaurantsResponse.builder()
                .title("동경산책 성신여대점")
                .thumUrl("https://ldb-phinf.pstatic.net/20220106_294/1641437440289J8dYW_JPEG/1635122589184-10.jpg")
                .distance("성신여대입구역(으)로부터 236m")
                .openTime("21:00에 영업종료")
                .tel("02-923-2666")
                .detail(
                        RestaurantsResponse.Detail.builder()
                                .local("성신여대입구역")
                                .title("동경산책 성신여대점")
                                .address("서울특별시 성북구 보문로34길 45")
                                .status("영업 중")
                                .openTime("21:00에 영업종료")
                                .homePageUrl("http://www.instagram.com/dongkyungsancheck")
                                .tel("02-923-2666")
                                .category(List.of("일식", "일식당"))
                                .x("127.0179133")
                                .y("37.5908482")
                                .thumUrls(List.of(
                                        "https://ldb-phinf.pstatic.net/20220106_294/1641437440289J8dYW_JPEG/1635122589184-10.jpg",
                                        "https://ldb-phinf.pstatic.net/20220106_112/1641437356001DoVgV_JPEG/1540180782395.jpg",
                                        "https://ldb-phinf.pstatic.net/20220106_297/1641437421551RjALN_JPEG/IMG_20180602_101017_715.jpg"
                                ))
                                .menuInfo(List.of(
                                        "아나고사케동정식 변동가격(업주문의)",
                                        "스키야끼정식 변동가격(업주문의)"
                                ))
                                .build()
                )
                .build();

        given(menuService.getNearbyRestaurants(anyDouble(), anyDouble(), anyString(), anyString()))
                .willReturn(List.of(place1, place2, place3));

        MockHttpServletRequestBuilder httpRequest = RestDocumentationRequestBuilders.get("/search")
                .param("x", "127.232943")
                .param("y", "37.6823811")
                .param("local", "성신여대입구역")
                .param("keyword", "식당");

        ResourceSnippetParameters parameters = ResourceSnippetParameters.builder()
                .tag("메뉴 API")
                .summary("위치기반 메뉴 추천 조회 API")
                .queryParameters(
                        parameterWithName("x").description("ex) 37.6823811"),
                        parameterWithName("y").description("ex) 127.232943"),
                        parameterWithName("local").description("역(또는 지역)이름"),
                        parameterWithName("keyword").description("카페 / 스터디카페 / 식당 / 도서관 / 스터디룸"))
                .responseFields(
                        fieldWithPath("code").type(NUMBER)
                                .description("상태 코드"),
                        fieldWithPath("message").type(STRING)
                                .description("상태 메세지"),
                        fieldWithPath("data[].title").type(STRING)
                                .description("가게 이름"),
                        fieldWithPath("data[].thumUrl").type(STRING)
                                .description("썸네일 이미지 URL"),
                        fieldWithPath("data[].distance").type(STRING)
                                .description("거리"),
                        fieldWithPath("data[].openTime").type(STRING)
                                .description("영업 시간"),
                        fieldWithPath("data[].tel").type(STRING)
                                .description("전화번호"),
                        fieldWithPath("data[].detail.local").type(STRING)
                                .description("지역"),
                        fieldWithPath("data[].detail.title").type(STRING)
                                .description("가게 이름"),
                        fieldWithPath("data[].detail.address").type(STRING)
                                .description("주소"),
                        fieldWithPath("data[].detail.status").type(STRING)
                                .description("영업 상태"),
                        fieldWithPath("data[].detail.openTime").type(STRING)
                                .description("영업 시간"),
                        fieldWithPath("data[].detail.homePageUrl").type(STRING)
                                .description("홈페이지 URL"),
                        fieldWithPath("data[].detail.tel").type(STRING)
                                .description("전화번호"),
                        fieldWithPath("data[].detail.category[]").type(ARRAY)
                                .description("카테고리 목록 / List<String>"),
                        fieldWithPath("data[].detail.x").type(STRING)
                                .description("위도"),
                        fieldWithPath("data[].detail.y").type(STRING)
                                .description("경도"),
                        fieldWithPath("data[].detail.thumUrls[]").type(ARRAY)
                                .description("상세 이미지 URL 목록 / List<String>"),
                        fieldWithPath("data[].detail.menuInfo[]").type(ARRAY)
                                .description("메뉴 정보 목록 / List<String>"))
                .responseSchema(schema("GroupPlaceResponse"))
                .build();

        RestDocumentationResultHandler document =
                documentHandler("get-nearby-restaurants", prettyPrint(), parameters);

        // when // then
        mockMvc.perform(httpRequest)
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document);
    }
}
