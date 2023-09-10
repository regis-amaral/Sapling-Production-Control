package dev.regis.rest.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.regis.rest.models.person.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("FROM Client x WHERE LOWER(x.name) ILIKE %:partName%")
    Page<Client> search(@Param("partName") String partName, Pageable pageable);

}
