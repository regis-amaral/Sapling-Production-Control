package dev.regis.rest.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.regis.rest.models.entities.GeneticMaterial;

public interface GeneticMaterialRepository extends JpaRepository<GeneticMaterial, Long>  {

    @Query("FROM GeneticMaterial gm WHERE LOWER(gm.name) ILIKE %:partName%")
    Page<GeneticMaterial> search(@Param("partName") String partName, Pageable pageable);

}
