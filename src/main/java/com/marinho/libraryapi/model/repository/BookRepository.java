package com.marinho.libraryapi.model.repository;

import com.marinho.libraryapi.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
