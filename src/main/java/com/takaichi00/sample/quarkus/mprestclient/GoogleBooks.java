package com.takaichi00.sample.quarkus.mprestclient;

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
  }

}
