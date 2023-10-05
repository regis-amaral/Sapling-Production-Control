package dev.regis.rest.models.production;

import java.time.Month;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import dev.regis.rest.models.person.Client;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ExpeditionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int planned;
    
    @Column(nullable = true)
    private int realized;

    @Enumerated
    @Column(name = "expedition_month", nullable = false, columnDefinition = "smallint")
    private Month month;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("listExpeditionPlans")
    private GeneticMaterial geneticMaterial;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("listExpeditionPlans")  
    private Client client;
    
}
