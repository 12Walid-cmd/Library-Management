package ca.hollandcollege.lms.service;

import ca.hollandcollege.lms.entity.Loan;

import java.util.List;

/*
 * Service interface for loan business logic.
 */
public interface LoanService {

    // Return all loans
    List<Loan> getAllLoans();

    // Borrow a book for a client
    void borrowBook(Long clientId, Long bookId);

    // Return a borrowed book
    void returnBook(Long loanId);

    // Dashboard statistics
    long countActiveLoans();

    long countOverdueLoans();
}