package ca.hollandcollege.lms.service;

import ca.hollandcollege.lms.entity.Client;

import java.util.List;

/*
 * Service interface for client operations.
 */
public interface ClientService {

    // Return all clients
    List<Client> getAllClients();

    // Save a new client or update an existing one
    void saveClient(Client client);

    // Find one client by id
    Client getClientById(Long id);

    // Delete one client by id
    void deleteClientById(Long id);

    // Count all clients for dashboard statistics
    long countClients();
}