package ca.hollandcollege.lms.service;

import ca.hollandcollege.lms.entity.Book;
import ca.hollandcollege.lms.entity.Client;
import ca.hollandcollege.lms.entity.Loan;
import ca.hollandcollege.lms.repository.BookRepository;
import ca.hollandcollege.lms.repository.ClientRepository;
import ca.hollandcollege.lms.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/*
 * Implementation of loan business logic.
 */
@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;

    public LoanServiceImpl(LoanRepository loanRepository,
                           BookRepository bookRepository,
                           ClientRepository clientRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Loan> getAllLoans() {
        // Return all loans ordered by newest first
        return loanRepository.findAllByOrderByLoanDateDesc();
    }

    @Override
    public void borrowBook(Long clientId, Long bookId) {

        // Find the client who is borrowing
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client id: " + clientId));

        // Find the selected book
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book id: " + bookId));

        // Check if at least one copy is available
        if (book.getAvailableCopies() <= 0) {
            throw new IllegalStateException("No available copies for this book.");
        }

        // Create a new loan
        Loan loan = new Loan();
        loan.setClient(client);
        loan.setBook(book);
        loan.setLoanDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(14)); // 2-week loan period
        loan.setStatus("BORROWED");

        // Reduce the number of available copies
        book.setAvailableCopies(book.getAvailableCopies() - 1);

        // Optional: update status if no copies remain
        if (book.getAvailableCopies() == 0) {
            book.setStatus("Unavailable");
        }

        // Save the updated book and new loan
        bookRepository.save(book);
        loanRepository.save(loan);
    }

    @Override
    public void returnBook(Long loanId) {

        // Find the loan record
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid loan id: " + loanId));

        // Prevent returning the same book twice
        if ("RETURNED".equalsIgnoreCase(loan.getStatus())) {
            throw new IllegalStateException("This book has already been returned.");
        }

        // Update the loan record
        loan.setReturnDate(LocalDate.now());
        loan.setStatus("RETURNED");

        // Increase available copies for the related book
        Book book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);

        // Optional: set status back to available
        if (book.getAvailableCopies() > 0) {
            book.setStatus("Available");
        }

        // Save updates
        bookRepository.save(book);
        loanRepository.save(loan);
    }

    @Override
    public long countActiveLoans() {
        return loanRepository.countByStatus("BORROWED");
    }

    @Override
    public long countOverdueLoans() {
        return loanRepository.countByStatusAndDueDateBefore("BORROWED", LocalDate.now());
    }
}