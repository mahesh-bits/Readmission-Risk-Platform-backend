package com.rrm.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.Map;

@RestController
@RequestMapping("/api/inference")
public class InferenceController {

  private final RestClient restClient;

  public InferenceController(
          @Value("${ML_BASE_URL:https://rrm-ml-service-fya6aaejd0bwc8ha.southindia-01.azurewebsites.net}") String baseUrl
  ) {
    this.restClient = RestClient.builder()
            .baseUrl(baseUrl)
            .build();
  }

  @PostMapping("/predict")
  public Map<String, Object> predict(
          @RequestBody Map<String, Object> features
  ) {
    return restClient.post()
            .uri("/v1/predict")
            .body(Map.of("features", features))
            .retrieve()
            .body(Map.class);
  }
}