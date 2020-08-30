package com.takaichi00.sample.quarkus.integration.repository;

import com.takaichi00.sample.quarkus.domain.model.Isbn;
import com.takaichi00.sample.quarkus.domain.repository.BookmarkRepository;
import com.takaichi00.sample.quarkus.integration.entity.BookEntity;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@ApplicationScoped
public class BookmarkRepositoryImpl implements BookmarkRepository {

  @Inject
  EntityManager entityManager;

  @Override
  @Transactional
  public List<Isbn> getAllIsbn() {

    TypedQuery<BookEntity> query = entityManager.createQuery("From BookEntity", BookEntity.class);
    List<BookEntity> bookEntities = query.getResultList();

    List<Isbn> isbnList = new ArrayList<>();
    for (BookEntity bookEntity : bookEntities) {
      isbnList.add(Isbn.of(Long.valueOf(bookEntity.getIsbn())));
    }

    return isbnList;
  }

  @Override
  @Transactional
  public void registerBookmark(Isbn isbn) {
    entityManager.persist(BookEntity.builder().isbn(isbn.getIsbn().toString()).build());
  }
}
