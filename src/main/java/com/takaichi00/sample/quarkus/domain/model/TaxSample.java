package com.takaichi00.sample.quarkus.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxSample {

  public int getTaxPrice(int price, double taxRate) {
    BigDecimal priceBigDecimal = new BigDecimal(price);
    BigDecimal taxRateBigDecimal = new BigDecimal(taxRate);
    BigDecimal taxPrice = priceBigDecimal.multiply(taxRateBigDecimal);
    BigDecimal roundTaxPrice = taxPrice.setScale(0, RoundingMode.HALF_EVEN);
    return roundTaxPrice.intValue();
  }
}
