package com.josecarlos.catalogservice;

import com.josecarlos.catalogservice.domain.Book;
import jakarta.validation.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import  static org.assertj.core.api.Assertions.assertThat;


public class BookValidationTests {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceds() {
        var book = Book.of("1234567890", "Title", "Author", 14.5, "Polarsophia");

        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails() {
        var book = Book.of("a123412344", "Title", "Author", 9.9, "Polarsophia");

        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("The ISBN format must be valid.");
    }
}
