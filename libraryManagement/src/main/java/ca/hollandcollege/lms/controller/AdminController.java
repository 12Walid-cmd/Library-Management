package ca.hollandcollege.lms.controller;

import ca.hollandcollege.lms.service.BookService;
import ca.hollandcollege.lms.service.ClientService;
import ca.hollandcollege.lms.service.LoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * This controller handles the homepage and admin dashboard.
 */
@Controller
public class AdminController {

    private final BookService bookService;
    private final ClientService clientService;
    private final LoanService loanService;

    public AdminController(BookService bookService,
                           ClientService clientService,
                           LoanService loanService) {
        this.bookService = bookService;
        this.clientService = clientService;
        this.loanService = loanService;
    }

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/admin")
    public String adminDashboard(Model model) {

        model.addAttribute("totalBooks", bookService.countBooks());
        model.addAttribute("totalClients", clientService.countClients());
        model.addAttribute("activeLoans", loanService.countActiveLoans());
        model.addAttribute("overdueLoans", loanService.countOverdueLoans());

        return "admin/dashboard";
    }
}