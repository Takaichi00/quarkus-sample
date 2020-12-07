package com.takaichi00.sample.quarkus.integration.repository;

import com.takaichi00.sample.quarkus.common.constant.Error;
import com.takaichi00.sample.quarkus.common.exception.ApplicationException;
import com.takaichi00.sample.quarkus.domain.model.Isbn;
import com.takaichi00.sample.quarkus.domain.repository.BookmarkRepository;
import com.takaichi00.sample.quarkus.integration.entity.BookEntity;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@ApplicationScoped
public class BookmarkRepositoryImpl implements BookmarkRepository {

  @Inject
  EntityManager entityManager;

  @Override
  @Transactional
  public List<Isbn> getAllIsbn() {

    TypedQuery<BookEntity> query =
      entityManager.createQuery("FROM BookEntity", BookEntity.class);
    List<BookEntity> bookEntities = query.getResultList();

    List<Isbn> isbnList = new ArrayList<>();
    for (BookEntity bookEntity : bookEntities) {
      isbnList.add(Isbn.of(Long.valueOf(bookEntity.getIsbn())));
    }

    return isbnList;
  }

  @Override
  @Transactional
  public Isbn registerBookmark(Isbn isbn) {
    try {
      entityManager.persist(BookEntity.builder().isbn(isbn.getIsbn().toString()).build());
    } catch (PersistenceException e) {
      throw new ApplicationException(
        Error.REGISTER_BOOKMARK_IS_FAILED.getErrorMessage(null),
        e,
        Error.REGISTER_BOOKMARK_IS_FAILED
      );
    }
    return isbn;
  }

  @Override
  @Transactional
  public void deleteBookmark(Isbn isbn) {
    Query query = entityManager.createQuery("DELETE FROM BookEntity WHERE isbn = :isbn")
                               .setParameter("isbn", isbn.toString());
    query.executeUpdate();
  }
}
