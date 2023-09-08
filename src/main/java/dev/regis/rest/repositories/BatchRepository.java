package dev.regis.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.regis.rest.models.entities.production.Batch;

public interface BatchRepository extends JpaRepository<Batch, Long>{

}
