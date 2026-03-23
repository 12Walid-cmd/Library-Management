package ca.hollandcollege.lms.controller;

import ca.hollandcollege.lms.entity.Book;
import ca.hollandcollege.lms.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
 * This controller handles all web requests related to books.
 * It talks to the service layer instead of directly using the repository.
 */
@Controller
public class BookController {

    private final BookService bookService;

    // Constructor injection
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /*
     * Display all books.
     * URL: /books
     */
    @GetMapping("/books")
    public String listBooks(@RequestParam(required = false) String keyword, Model model) {
        // Send filtered or full book list to the page
        model.addAttribute("books", bookService.searchBooks(keyword));

        // Send the keyword back so it stays in the search box
        model.addAttribute("keyword", keyword);

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
        bookService.saveBook(book);
        return "redirect:/books";
    }

    /*
     * Show the Edit Book form.
     * URL example: /books/edit/1
     */
    @GetMapping("/books/edit/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "books/add";
    }

    /*
     * Delete a book by id.
     * URL example: /books/delete/1
     */
    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }
}