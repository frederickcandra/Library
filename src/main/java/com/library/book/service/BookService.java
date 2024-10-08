package com.library.book.service;

import com.library.book.model.BookModel;
import com.library.book.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private static final Logger log = LoggerFactory.getLogger(BookService.class);
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookModel> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookModel getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book Not Found"));
    }

    public BookModel createBook(BookModel book) {
        return bookRepository.save(book);
    }

    public BookModel updateBook(Long id,BookModel bookDetails) {

        BookModel existingBook = getBookById(id);

        if(bookDetails.getName() != null && !bookDetails.getName().isEmpty()){
            existingBook.setName(bookDetails.getName());
        }
        if(bookDetails.getAuthor() != null && !bookDetails.getAuthor().isEmpty()){
            existingBook.setAuthor(bookDetails.getAuthor());
        }
        if(bookDetails.getPublisher() != null && !bookDetails.getPublisher().isEmpty()){
            existingBook.setPublisher(bookDetails.getPublisher());
        }
        return bookRepository.save(existingBook);
    }
    public void deleteBook(Long id) {
        if(!bookRepository.existsById(id)){
            log.error("Book with id" + id + "Not Found");
            throw new RuntimeException("Book with id" + id + " Not Found");
        }
        bookRepository.deleteById(id);
        log.info("Book with id" + id + " Deleted");
    }
}
