package com.project.productserviceapp.clients;

import com.project.productserviceapp.clients.dtos.ValidatetokenResponseDto;
import com.project.productserviceapp.clients.dtos.ValidateTokenRequestDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class AuthenticationClient {
    private RestTemplateBuilder restTemplateBuilder;

    public AuthenticationClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public ValidatetokenResponseDto validate(String token, Long userId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ValidateTokenRequestDto request = new ValidateTokenRequestDto();
        request.setToken(token);
        request.setUserId(userId);

        ResponseEntity<ValidatetokenResponseDto> response = restTemplate.postForEntity(
                "http://localhost:9000/auth/validate",
                request,
                ValidatetokenResponseDto.class
        );

        return response.getBody();
    }
}