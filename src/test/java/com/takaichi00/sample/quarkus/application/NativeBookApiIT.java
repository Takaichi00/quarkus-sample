package com.takaichi00.sample.quarkus.application;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeBookApiIT extends BookControllerTest {
  // Execute the same tests but in native mode.
}
