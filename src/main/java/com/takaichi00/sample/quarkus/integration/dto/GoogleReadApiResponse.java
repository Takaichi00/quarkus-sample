package com.takaichi00.sample.quarkus.integration.dto;

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GoogleReadApiResponse {

  @JsonbProperty(value = "items")
  private List<GoogleApiItem> items;

  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  public static class GoogleApiItem {
    @JsonbProperty(value = "volumeInfo")
    private VolumeInfo volumeInfo;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class VolumeInfo {
      @JsonbProperty(value = "title")
      private String title;
    }
  }
}
