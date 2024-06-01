package cmc.cmc15th_hackathon.domain.health.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class HealthCheckResponse {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    public static class Parameter {
        @NotNull(message = "이름은 필수 값 입니다.")
        private String name;

        @NotNull(message = "레벨은 필수 값 입니다.")
        private Integer level;

        public static Parameter to(String name, Integer level) {
            return Parameter.builder()
                    .name(name)
                    .level(level)
                    .build();
        }
    }
}
