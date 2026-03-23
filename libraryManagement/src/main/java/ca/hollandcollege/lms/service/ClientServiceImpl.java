package ca.hollandcollege.lms.service;

import ca.hollandcollege.lms.entity.Client;
import ca.hollandcollege.lms.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Implementation of the ClientService interface.
 */
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    // Constructor injection
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAllClients() {
        // Return all clients from the database
        return clientRepository.findAll();
    }

    @Override
    public void saveClient(Client client) {
        // Save a new client or update an existing one
        clientRepository.save(client);
    }

    @Override
    public Client getClientById(Long id) {
        // Find client by id or throw an error if not found
        return clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client id: " + id));
    }

    @Override
    public void deleteClientById(Long id) {
        // Delete client by id
        clientRepository.deleteById(id);
    }

    @Override
    public long countClients() {
        // Count total clients in database
        return clientRepository.count();
    }
}