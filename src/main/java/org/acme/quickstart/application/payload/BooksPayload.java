package org.acme.quickstart.application.payload;

import java.util.List;

import javax.json.bind.annotation.JsonbProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BooksPayload {

  @JsonbProperty("books")
  private List<BookPayload> bookPayload;
  
}
