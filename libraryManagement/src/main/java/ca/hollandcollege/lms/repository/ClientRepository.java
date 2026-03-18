package ca.hollandcollege.lms.repository;

import ca.hollandcollege.lms.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * This repository gives us database operations for clients.
 * Spring automatically provides save, findAll, findById, deleteById, etc.
 */
public interface ClientRepository extends JpaRepository<Client, Long> {
}