package com.takaichi00.sample.quarkus.integration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="giftSeq")
  private Integer id;

}
