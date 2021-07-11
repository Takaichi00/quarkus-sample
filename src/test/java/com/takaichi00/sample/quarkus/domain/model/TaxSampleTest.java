package com.takaichi00.sample.quarkus.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaxSampleTest {

  @Test
  void _100円で5パーセントの税金は5円となる() {
    TaxSample testTarget = new TaxSample();
    int expected = 5;
    int actual = testTarget.getTaxPrice(100, 0.05);
    assertEquals(expected, actual);
  }

  @Test
  void _10円で5パーセントの税金は1円となる() {
    TaxSample testTarget = new TaxSample();
    int expected = 1;
    int actual = testTarget.getTaxPrice(10, 0.05);
    assertEquals(expected, actual);
  }

  @Test
  void _29円で5パーセントの税金は1円となる() {
    TaxSample testTarget = new TaxSample();
    int expected = 1;
    int actual = testTarget.getTaxPrice(29, 0.05);
    assertEquals(expected, actual);
  }

}
