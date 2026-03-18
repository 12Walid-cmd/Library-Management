package ca.hollandcollege.lms.repository;

import ca.hollandcollege.lms.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}