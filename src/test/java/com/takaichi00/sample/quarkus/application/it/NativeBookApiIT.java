package com.takaichi00.sample.quarkus.application.it;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeBookApiIT extends BookApiItTemplate {
  // Execute the same tests but in native mode.
}
