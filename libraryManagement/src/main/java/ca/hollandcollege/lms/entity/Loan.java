package ca.hollandcollege.lms.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

/*
 * This entity represents one borrowing transaction.
 * A client borrows one book for a period of time.
 */
@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Many loans can belong to one client.
     */
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    /*
     * Many loans can belong to one book.
     */
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    /*
     * Date when the book was borrowed.
     */
    @Column(name = "loan_date", nullable = false)
    private LocalDate loanDate;

    /*
     * Date when the book should be returned.
     */
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    /*
     * Date when the book was actually returned.
     * This can stay null until the book is returned.
     */
    @Column(name = "return_date")
    private LocalDate returnDate;

    /*
     * Loan status:
     * BORROWED or RETURNED
     */
    @Column(nullable = false)
    private String status;

    public Loan() {
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    /*
     * Check if the loan is overdue.
     */
    public boolean isOverdue() {
        return "BORROWED".equals(this.status)
                && this.dueDate.isBefore(java.time.LocalDate.now());
    }
}