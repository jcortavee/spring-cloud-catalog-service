package com.josecarlos.catalogservice.demo;

import com.josecarlos.catalogservice.domain.Book;
import com.josecarlos.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testdata")
public class BookDataLoader {
    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        bookRepository.deleteAll();
        var book1 = Book.of("1234567891", "Title", "Author", 9.9, "Polarsophia");
        var book2 = Book.of("1234567899", "Title2", "Author 2", 9.9, "Polarsophia");
        bookRepository.saveAll(List.of(book1, book2));
    }
}
