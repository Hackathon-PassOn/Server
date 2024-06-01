package cmc.cmc15th_hackathon.domain.menu.service;

import cmc.cmc15th_hackathon.domain.menu.response.NaverMapListDto;
import cmc.cmc15th_hackathon.domain.menu.response.RestaurantsResponse;
import cmc.cmc15th_hackathon.global.common.Result;
import cmc.cmc15th_hackathon.global.exception.CustomException;
import cmc.cmc15th_hackathon.global.util.DistanceCalculator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class MenuService {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<RestaurantsResponse> getNearbyRestaurants(Double x, Double y, String local, String keyword) {
        // 네이버 API 요청
        URI uri = createNaverRequestUri(local, keyword, x, y);
        ResponseEntity<NaverMapListDto> naverResponse = restTemplate.getForEntity(uri.toString(), NaverMapListDto.class);

        // 응답 처리
        Optional<NaverMapListDto> optionalBody = Optional.ofNullable(naverResponse.getBody());
        if (!naverResponse.getStatusCode().is2xxSuccessful() || optionalBody.isEmpty()) {
            throw new CustomException(Result.NOT_REQUEST_NAVER);
        }

        // 장소 정보 가져오기
        List<NaverMapListDto.placeList> placeList = optionalBody.get().getResult().getPlace().getList();

        return placeList.stream().map(toPlaceEntity(x, y, local)).toList();
    }

    private static URI createNaverRequestUri(String local, String keyword, Double x, Double y) {
        String searchCoord = y + ";" + x;
        return UriComponentsBuilder.fromHttpUrl("https://map.naver.com/p/api/search/allSearch")
                .queryParam("query", local + keyword)
                .queryParam("type", "all")
                .queryParam("searchCoord", searchCoord)
                .queryParam("boundary", "")
                .build()
                .toUri();
    }

    private static Function<NaverMapListDto.placeList, RestaurantsResponse> toPlaceEntity(Double x, Double y, String local) {
        return naver -> {
            RestaurantsResponse response = RestaurantsResponse.response(naver, local);
            double placeX = Double.parseDouble(naver.getX());
            double placeY = Double.parseDouble(naver.getY());
            int calculateDistance = (int) DistanceCalculator.getDistance(x, y, placeY, placeX);

            String distance = String.format("%s(으)로부터 %sm", local, calculateDistance);
            response.setDistance(distance);
            return response;
        };
    }


}
