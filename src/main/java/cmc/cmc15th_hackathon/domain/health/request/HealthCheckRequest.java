package cmc.cmc15th_hackathon.domain.health.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class HealthCheckRequest {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Parameter {
        @NotNull(message = "이름은 필수 값 입니다.")
        private String name;

        @NotNull(message = "레벨은 필수 값 입니다.")
        private Integer level;
    }
}
