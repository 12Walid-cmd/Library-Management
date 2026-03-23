package ca.hollandcollege.lms.controller;

import ca.hollandcollege.lms.service.BookService;
import ca.hollandcollege.lms.service.ClientService;
import ca.hollandcollege.lms.service.LoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;
    private final ClientService clientService;
    private final BookService bookService;

    public LoanController(LoanService loanService,
                          ClientService clientService,
                          BookService bookService) {
        this.loanService = loanService;
        this.clientService = clientService;
        this.bookService = bookService;
    }

    @GetMapping
    public String listLoans(Model model) {
        model.addAttribute("loans", loanService.getAllLoans());
        return "loans/list";
    }

    @GetMapping("/borrow")
    public String showBorrowForm(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("books", bookService.getAvailableBooks());
        return "loans/borrow";
    }

    @PostMapping("/borrow")
    public String borrowBook(@RequestParam Long clientId,
                             @RequestParam Long bookId,
                             Model model) {
        try {
            loanService.borrowBook(clientId, bookId);
            return "redirect:/loans";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("clients", clientService.getAllClients());
            model.addAttribute("books", bookService.getAvailableBooks());
            return "loans/borrow";
        }
    }

    @GetMapping("/return/{id}")
    public String returnBook(@PathVariable Long id, Model model) {
        try {
            loanService.returnBook(id);
            return "redirect:/loans";
        } catch (Exception e) {
            model.addAttribute("loans", loanService.getAllLoans());
            model.addAttribute("error", e.getMessage());
            return "loans/list";
        }
    }
}