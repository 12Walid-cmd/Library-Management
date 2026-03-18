package ca.hollandcollege.lms.controller;

import ca.hollandcollege.lms.entity.Client;
import ca.hollandcollege.lms.repository.ClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
 * This controller handles all web requests related to clients.
 */
@Controller
public class ClientController {

    // Gives access to the clients table
    private final ClientRepository clientRepository;

    // Constructor injection
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /*
     * Display all clients.
     * URL: /clients
     */
    @GetMapping("/clients")
    public String listClients(Model model) {
        model.addAttribute("clients", clientRepository.findAll());
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
        clientRepository.save(client);
        return "redirect:/clients";
    }

    /*
     * Show the Edit Client form.
     */
    @GetMapping("/clients/edit/{id}")
    public String showEditClientForm(@PathVariable Long id, Model model) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client id: " + id));

        model.addAttribute("client", client);
        return "clients/add";
    }

    /*
     * Delete a client by id.
     */
    @GetMapping("/clients/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
        return "redirect:/clients";
    }
}