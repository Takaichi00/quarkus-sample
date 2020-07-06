package com.takaichi00.sample.quarkus.integration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbProperty;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VolumeInfo {

  @JsonbProperty(value = "title")
  private String title;
}
