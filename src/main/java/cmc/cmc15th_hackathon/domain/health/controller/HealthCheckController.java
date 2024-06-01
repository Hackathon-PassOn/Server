package cmc.cmc15th_hackathon.domain.health.controller;

import cmc.cmc15th_hackathon.domain.health.request.HealthCheckRequest;
import cmc.cmc15th_hackathon.domain.health.response.HealthCheckResponse;
import cmc.cmc15th_hackathon.domain.health.service.HealthCheckService;
import cmc.cmc15th_hackathon.global.common.CustomResponseEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthCheckController {

    private final HealthCheckService healthCheckService;

    @GetMapping("/check")
    private CustomResponseEntity<String> healthCheck() {
        return CustomResponseEntity.success("Server On!");
    }

    @PostMapping("/check/parameter")
    private CustomResponseEntity<HealthCheckResponse.Parameter> healthCheckForParameter(
        @Valid @RequestBody HealthCheckRequest.Parameter request
    ) {
        return CustomResponseEntity.success(healthCheckService.parameterCheck(request));
    }
}