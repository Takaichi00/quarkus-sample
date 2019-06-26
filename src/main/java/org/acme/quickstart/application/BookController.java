package org.acme.quickstart.application;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.quickstart.application.payload.BookPayload;
import org.acme.quickstart.application.payload.BooksPayload;
import org.acme.quickstart.domain.model.Book;
import org.acme.quickstart.domain.service.BookService;


@Path("/book")
public class BookController {

  @Inject
  BookService bookService;
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public BooksPayload getAllBooks() {
    
    List<Book> books = bookService.getAllBooks();
    BooksPayload booksPayload = new BooksPayload();
    booksPayload.setBookPayload(new ArrayList<>());
    
    for (int i = 0; i < books.size(); ++i) {
      BookPayload bookPayload = new BookPayload(books.get(i).getTitle(),
                                                books.get(i).getPrice());
      booksPayload.getBookPayload().add(bookPayload);
    }
    
    return booksPayload;
  }
}
