package ca.hollandcollege.lms.controller;

import ca.hollandcollege.lms.entity.Client;
import ca.hollandcollege.lms.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
 * This controller handles all web requests related to clients.
 * It talks to the service layer instead of directly using the repository.
 */
@Controller
public class ClientController {

    private final ClientService clientService;

    // Constructor injection
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /*
     * Display all clients.
     * URL: /clients
     */
    @GetMapping("/clients")
    public String listClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "clients/list";
    }

    /*
     * Show the Add Client form.
     * URL: /clients/add
     */
    @GetMapping("/clients/add")
    public String showAddClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "clients/add";
    }

    /*
     * Save a new client or update an existing one.
     */
    @PostMapping("/clients/save")
    public String saveClient(@ModelAttribute Client client) {
        clientService.saveClient(client);
        return "redirect:/clients";
    }

    /*
     * Show the Edit Client form.
     */
    @GetMapping("/clients/edit/{id}")
    public String showEditClientForm(@PathVariable Long id, Model model) {
        Client client = clientService.getClientById(id);
        model.addAttribute("client", client);
        return "clients/add";
    }

    /*
     * Delete a client by id.
     */
    @GetMapping("/clients/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);
        return "redirect:/clients";
    }
}