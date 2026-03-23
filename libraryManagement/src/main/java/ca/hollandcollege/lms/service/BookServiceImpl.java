package ca.hollandcollege.lms.service;

import ca.hollandcollege.lms.entity.Book;
import ca.hollandcollege.lms.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Implementation of the BookService interface.
 * This is where business logic for books will go.
 */
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    // Constructor injection
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        // Return all books from database
        return bookRepository.findAll();
    }

    @Override
    public void saveBook(Book book) {
        // Save a new book or update an existing one
        bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        // Find book by id or throw an error if not found
        return bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book id: " + id));
    }

    @Override
    public void deleteBookById(Long id) {
        // Delete book by id
        bookRepository.deleteById(id);
    }

    @Override
    public long countBooks() {
        // Count total books in database
        return bookRepository.count();
    }

    @Override
    public List<Book> getAvailableBooks() {
        // Return only books with at least 1 available copy
        return bookRepository.findByAvailableCopiesGreaterThan(0);
    }

    @Override
    public List<Book> searchBooks(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            return bookRepository.findAll();
        }

        return bookRepository
                .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrIsbnContainingIgnoreCase(
                        keyword, keyword, keyword
                );
    }

}