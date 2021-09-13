package it;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeBookmarkApiIT extends BookmarkApiItTemplate {
  // Execute the same tests but in native mode.
}
