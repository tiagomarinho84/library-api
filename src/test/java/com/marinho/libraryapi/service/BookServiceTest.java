package com.marinho.libraryapi.service;

import com.marinho.libraryapi.model.entity.Book;
import com.marinho.libraryapi.model.repository.BookRepository;
import com.marinho.libraryapi.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class BookServiceTest {

    BookService service;
    @MockBean
    BookRepository repository;

    @BeforeEach
    void setUp() {
        this.service = new BookServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve salvar um livro")
    void saveBookTest() {

        //cenario
        Book book = Book.builder()
                .isbn("123")
                .author("Tiago")
                .title("As Aventuras")
                .build();

        Mockito.when(repository.save(book)).thenReturn(
                Book.builder()
                        .id(1L)
                        .isbn("123")
                        .author("Tiago")
                        .title("As Aventuras")
                        .build()
        );

        //execucao
        Book savedBook = service.save(book);

        //verificacao
        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getIsbn()).isEqualTo("123");
        assertThat(savedBook.getTitle()).isEqualTo("As Aventuras");
        assertThat(savedBook.getAuthor()).isEqualTo("Tiago");
    }
}
