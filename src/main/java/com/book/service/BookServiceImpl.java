package com.book.service;

import com.book.dto.BookDTO;
import com.book.exception.CustomException;
import com.book.model.Book;
import com.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public String addBook(Book book) {
        bookRepository.save(book);
        return "Book Added Successfully !";
    }

    @Override
    public String updatePriceQuantity(Long id, BookDTO bookDTO) {
        Book b = bookRepository.findById(id).orElseThrow(()-> new  CustomException("Book is not present By Id"));
        b.setQuantity(bookDTO.getQuantity());
        b.setPrice(bookDTO.getPrice());
        bookRepository.save(b);
        return "book Updated Successfully ";
    }

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return  bookRepository.findById(id).orElseThrow(()->new CustomException("Book Not Found By ID"));

    }

    @Override
    public String deleteByID(Long id) {
        getBookById(id);
        bookRepository.deleteById(id);
        return "User Delete successfully !";
    }


}
