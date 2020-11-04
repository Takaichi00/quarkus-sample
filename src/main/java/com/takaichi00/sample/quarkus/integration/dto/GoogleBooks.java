package com.takaichi00.sample.quarkus.integration.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class GoogleBooks {
  public String kind;
  public Integer totalItems;
  public List<Items> items;

  public static class Items {
    public VolumeInfo volumeInfo;
  }

  public static class VolumeInfo {
    public String title;
    public List<String> authors;
    public String previewLink;
  }

}
