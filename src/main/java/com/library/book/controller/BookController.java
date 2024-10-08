package com.library.book.controller;

import com.library.book.model.BookModel;
import com.library.book.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/index")
    public List<BookModel> getAllBooks() {
        List<BookModel> books = bookService.getAllBooks();
        return ResponseEntity.ok(books).getBody();
    }

    @PostMapping("/create")
    public BookModel createBook(@RequestBody BookModel book) {
        BookModel bookModel = bookService.createBook(book);
        return ResponseEntity.status(201).body(bookModel).getBody();
    }

    @PutMapping("/update/{id}")
    public BookModel updateBook(@PathVariable Long id, @RequestBody BookModel book) {
        BookModel bookModel = bookService.updateBook(id, book);
        return ResponseEntity.status(201).body(bookModel).getBody();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build(); // Mengembalikan status 204 No Content
        } catch (RuntimeException e) {
            // Jika terjadi error, bisa menangani exception di sini atau melempar kembali
            return ResponseEntity.notFound().build(); // Mengembalikan status 404 Not Found
        }
    }

}
