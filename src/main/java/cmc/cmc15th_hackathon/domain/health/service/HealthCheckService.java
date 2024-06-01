package cmc.cmc15th_hackathon.domain.health.service;

import cmc.cmc15th_hackathon.domain.health.request.HealthCheckRequest;
import cmc.cmc15th_hackathon.domain.health.response.HealthCheckResponse;
import org.springframework.stereotype.Service;

@Service
public class HealthCheckService {

    public HealthCheckResponse.Parameter parameterCheck(HealthCheckRequest.Parameter request) {
        return HealthCheckResponse.Parameter.to(request.getName(), request.getLevel());
    }
}
