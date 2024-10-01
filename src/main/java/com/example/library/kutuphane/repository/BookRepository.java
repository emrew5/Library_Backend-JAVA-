package com.example.library.kutuphane.repository;

import com.example.library.kutuphane.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
