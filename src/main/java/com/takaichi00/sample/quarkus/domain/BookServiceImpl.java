package com.takaichi00.sample.quarkus.domain;

import com.takaichi00.sample.quarkus.application.domain.BookService;

import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookServiceImpl implements BookService {

  @Override
  public List<Book> getAllBooks() {
    return null;
  }
}