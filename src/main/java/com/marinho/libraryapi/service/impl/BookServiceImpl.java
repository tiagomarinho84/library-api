package com.marinho.libraryapi.service.impl;

import com.marinho.libraryapi.api.model.entity.Book;
import com.marinho.libraryapi.model.repository.BookRepository;
import com.marinho.libraryapi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    @Override
    public Book save(Book book) {
        return repository.save(book);
    }
}
