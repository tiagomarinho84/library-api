package com.marinho.libraryapi.api.resource;

import com.marinho.libraryapi.api.dto.BookDTO;
import com.marinho.libraryapi.exception.BusinessException;
import com.marinho.libraryapi.model.entity.Book;
import com.marinho.libraryapi.api.exceptions.ApiErros;
import com.marinho.libraryapi.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService service;
    private final ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO create( @RequestBody @Valid BookDTO dto ){
        log.info(" creating a book for isbn: {} ", dto.getIsbn());
        Book entity = modelMapper.map( dto, Book.class );
        entity = service.save(entity);
        return modelMapper.map(entity, BookDTO.class);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleValidationsExceptions(MethodArgumentNotValidException exception) {

        BindingResult bindingResult = exception.getBindingResult();
        return new ApiErros(bindingResult);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleBusinessException(BusinessException ex){
        return new ApiErros(ex);
    }
}
