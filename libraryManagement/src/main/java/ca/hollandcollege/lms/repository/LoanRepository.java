package ca.hollandcollege.lms.repository;

import ca.hollandcollege.lms.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/*
 * Repository for loan database operations.
 */
public interface LoanRepository extends JpaRepository<Loan, Long> {

    // Return all loans ordered from newest to oldest
    List<Loan> findAllByOrderByLoanDateDesc();

    // Count all active borrowed books
    long countByStatus(String status);

    // Count overdue books that are still borrowed
    long countByStatusAndDueDateBefore(String status, LocalDate date);
}