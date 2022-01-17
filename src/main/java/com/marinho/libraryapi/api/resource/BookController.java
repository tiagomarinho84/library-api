package com.marinho.libraryapi.api.resource;

import com.marinho.libraryapi.api.dto.BookDTO;
import com.marinho.libraryapi.api.model.entity.Book;
import com.marinho.libraryapi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;
    private final ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO create(@RequestBody BookDTO dto) {

        Book entity = modelMapper
                .map(dto, Book.class);

        entity = service.save(entity);

        return modelMapper
                .map(entity, BookDTO.class);
    }
}
