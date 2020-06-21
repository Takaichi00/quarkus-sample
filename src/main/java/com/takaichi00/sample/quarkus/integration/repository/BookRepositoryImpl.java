package com.takaichi00.sample.quarkus.integration.repository;

import com.takaichi00.sample.quarkus.domain.model.Book;
import com.takaichi00.sample.quarkus.domain.repository.BookRepository;
import com.takaichi00.sample.quarkus.integration.entity.BookEntity;
import io.quarkus.arc.DefaultBean;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@DefaultBean
@ApplicationScoped
public class BookRepositoryImpl implements BookRepository {

  @Inject
  EntityManager entityManager;

  @Override
  @Transactional
  public List<Book> getAllBooks() {

    TypedQuery<BookEntity> query = entityManager.createQuery("From BookEntity", BookEntity.class);
    List<BookEntity> bookEntities = query.getResultList();

    return Arrays.asList(
            Book.builder()
                    .isbn(bookEntities.get(0).getIsbn())
                    .title("test-title")
                    .authors(Arrays.asList("authors1", "authors2"))
                    .price(1000)
                    .build()
    );
  }
}
