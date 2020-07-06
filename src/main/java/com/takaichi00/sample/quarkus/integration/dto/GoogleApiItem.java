package com.takaichi00.sample.quarkus.integration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GoogleApiItem {

  @JsonbProperty(value = "volumeInfo")
  private VolumeInfo volumeInfo;

}
