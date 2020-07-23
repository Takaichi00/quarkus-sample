package com.takaichi00.sample.quarkus.integration.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import java.io.Serializable;
import java.util.List;
import javax.json.bind.annotation.JsonbProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@RegisterForReflection
public class VolumeInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonbProperty(value = "title")
  private String title;

  @JsonbProperty(value = "authors")
  private List<String> authors;
}
