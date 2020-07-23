package com.takaichi00.sample.quarkus.integration.dto;

import javax.json.bind.annotation.JsonbProperty;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@RegisterForReflection
public class GoogleApiItem implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonbProperty(value = "volumeInfo")
  private VolumeInfo volumeInfo;
}
