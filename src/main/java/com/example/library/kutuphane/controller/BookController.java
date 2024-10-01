package com.example.library.kutuphane.controller;

import com.example.library.kutuphane.model.Book;
import com.example.library.kutuphane.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Optional<Book> existingBook = bookService.getBookById(id);
        if (existingBook.isPresent()) {
            book.setId(id); // Güncellenmek istenen kitabın ID'sini set et
            Book updatedBook = bookService.saveBook(book); // Güncellenmiş kitabı kaydet
            return ResponseEntity.ok(updatedBook); // Güncellenmiş kitabı döndür
        } else {
            return ResponseEntity.notFound().build(); // Kitap bulunamazsa 404 döndür
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hello")
    public String test() {
        return "merhabalar";
    }
}
