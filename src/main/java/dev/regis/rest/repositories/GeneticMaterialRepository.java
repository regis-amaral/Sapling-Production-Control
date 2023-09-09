package dev.regis.rest.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.regis.rest.models.production.GeneticMaterial;

public interface GeneticMaterialRepository extends JpaRepository<GeneticMaterial, Long>  {

    @Query("FROM GeneticMaterial s WHERE LOWER(s.name) ILIKE %:partName%")
    Page<GeneticMaterial> search(@Param("partName") String partName, Pageable pageable);

}
