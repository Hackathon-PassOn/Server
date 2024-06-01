package cmc.cmc15th_hackathon.domain.menu.controller;

import cmc.cmc15th_hackathon.domain.menu.response.RestaurantsResponse;
import cmc.cmc15th_hackathon.domain.menu.service.MenuService;
import cmc.cmc15th_hackathon.global.common.CustomResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/search")
    public CustomResponseEntity<List<RestaurantsResponse>> getNearbyRestaurants(
            @RequestParam Double x,
            @RequestParam Double y,
            @RequestParam String local,
            @RequestParam String keyword
    ) {
        return CustomResponseEntity.success(menuService.getNearbyRestaurants(x, y, local, keyword));
    }
}
