package com.takaichi00.sample.quarkus;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

public class TestUtils {

  private TestUtils() {
    // do nothing
  }

  public static String readMockResponseFile(String filename) throws IOException {
    try (FileInputStream input = new FileInputStream("src/test/resources/__files/" + filename)) {
      return IOUtils.toString(input, StandardCharsets.UTF_8);
    }
  }
}
