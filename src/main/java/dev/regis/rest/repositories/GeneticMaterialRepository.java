package dev.regis.rest.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.regis.rest.models.entities.GeneticMaterial;

public interface GeneticMaterialRepository extends JpaRepository<GeneticMaterial, Long>  {
    Optional<GeneticMaterial> findByName(String name);
}
