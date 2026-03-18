package ca.hollandcollege.lms.controller;

import ca.hollandcollege.lms.entity.Book;
import ca.hollandcollege.lms.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
 * This controller handles all web requests related to books.
 */
@Controller
public class BookController {

    // Gives access to the books table in the database
    private final BookRepository bookRepository;

    // Constructor injection
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /*
     * Display all books.
     * URL: /books
     */
    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books/list";
    }

    /*
     * Show the Add Book form.
     * URL: /books/add
     */
    @GetMapping("/books/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/add";
    }

    /*
     * Save a new book OR update an existing one.
     * If the Book object has an id, JPA updates the row.
     * If the id is null, JPA inserts a new row.
     */
    @PostMapping("/books/save")
    public String saveBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

    /*
     * Show the Edit Book form.
     * Finds the book by id and sends it to the same form page.
     * URL example: /books/edit/1
     */
    @GetMapping("/books/edit/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book id: " + id));

        model.addAttribute("book", book);
        return "books/add";
    }

    /*
     * Delete a book by id.
     * URL example: /books/delete/1
     */
    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
}