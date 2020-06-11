package com.takaichi00.sample.quarkus.domain;

import com.takaichi00.sample.quarkus.application.domain.BookService;
import io.quarkus.arc.DefaultBean;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

// https://quarkus.io/blog/quarkus-dependency-injection/
@DefaultBean
@ApplicationScoped
public class BookServiceImpl implements BookService {

  @Override
  public List<Book> getAllBooks() {
    return new ArrayList<>();
  }
}