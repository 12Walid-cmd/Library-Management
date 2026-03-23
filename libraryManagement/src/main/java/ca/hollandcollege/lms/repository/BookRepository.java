package ca.hollandcollege.lms.repository;

import ca.hollandcollege.lms.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Return only books that still have available copies
    List<Book> findByAvailableCopiesGreaterThan(int availableCopies);

    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrIsbnContainingIgnoreCase(
            String title,
            String author,
            String isbn
    );

}