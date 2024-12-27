package com.book.service;

import com.book.dto.BookDTO;
import com.book.model.Book;

import java.util.List;

public interface BookService {
     String addBook(Book book);

     String updatePriceQuantity(Long id, BookDTO bookDTO);

     List<Book> getAllBook();

     Book getBookById(Long id);

     String deleteByID(Long id);

}
