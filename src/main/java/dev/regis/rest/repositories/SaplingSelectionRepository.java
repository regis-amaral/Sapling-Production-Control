package dev.regis.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.regis.rest.models.production.entities.SaplingSelection;

public interface SaplingSelectionRepository extends JpaRepository<SaplingSelection, Long> {
    
}
