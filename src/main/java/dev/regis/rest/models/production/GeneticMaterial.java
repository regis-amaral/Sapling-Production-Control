package dev.regis.rest.models.production;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class GeneticMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = true)
    private String description;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "specie_id", nullable = false)
    private Specie specie;

    @OneToMany(mappedBy = "geneticMaterial")
    @JsonIgnoreProperties("geneticMaterial")
    private List<Batch> listBatchs;

    @OneToMany(mappedBy = "geneticMaterial", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("geneticMaterial")
    private List<ExpeditionPlan> listExpeditionPlans;

}
