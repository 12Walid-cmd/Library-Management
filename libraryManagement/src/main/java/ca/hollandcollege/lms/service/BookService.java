package ca.hollandcollege.lms.service;

import ca.hollandcollege.lms.entity.Book;

import java.util.List;

/*
 * Service interface for book operations.
 * This sits between the controller and repository.
 */
public interface BookService {

    // Return all books from the database
    List<Book> getAllBooks();

    // Save a new book or update an existing one
    void saveBook(Book book);

    // Find one book by id
    Book getBookById(Long id);

    // Delete one book by id
    void deleteBookById(Long id);

    // Count all books for dashboard statistics
    long countBooks();

    // Return only books that can still be borrowed
    List<Book> getAvailableBooks();

    // Search books by keyword
    List<Book> searchBooks(String keyword);
}