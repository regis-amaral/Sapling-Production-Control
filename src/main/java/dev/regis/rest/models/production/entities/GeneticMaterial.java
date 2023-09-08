package dev.regis.rest.models.production.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class GeneticMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = true)
    private String description;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "specie_id", nullable = false)
    private Specie specie;

    // @OneToMany(fetch = FetchType.LAZY, mappedBy = "geneticMaterial")
    // @JsonIgnore
    // private List<Batch> ListBatchs;

    // @OneToMany(fetch = FetchType.LAZY, mappedBy = "geneticMaterial")
    // private List<ExpeditionPlan> listExpeditionPlans;

    public GeneticMaterial() {
    }

    public GeneticMaterial(Long id, String name, String description, Specie specie, List<Batch> listBatchs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.specie = specie;
        // this.ListBatchs = listBatchs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    // public List<ExpeditionPlan> getListExpeditionPlans() {
    //     return listExpeditionPlans;
    // }

    // public void setListExpeditionPlans(List<ExpeditionPlan> listExpeditionPlans) {
    //     this.listExpeditionPlans = listExpeditionPlans;
    // }

    // public List<Batch> getListBatchs() {
    //     return ListBatchs;
    // }

    // public void setListBatchs(List<Batch> listBatchs) {
    //     ListBatchs = listBatchs;
    // }
    
    
}
