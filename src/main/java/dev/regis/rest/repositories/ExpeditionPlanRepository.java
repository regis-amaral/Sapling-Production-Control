package dev.regis.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.regis.rest.models.entities.production.ExpeditionPlan;

public interface ExpeditionPlanRepository extends JpaRepository<ExpeditionPlan, Long> {
    
}
