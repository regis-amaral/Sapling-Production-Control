package dev.regis.rest.models.entities.production;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;

    @ManyToOne
    @JoinColumn(name = "genetic_material_id")
    @JsonIgnoreProperties("listBatchs")    
    private GeneticMaterial geneticMaterial;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date stakingDate;

    @Column(nullable = false)
    private int amount;

    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private SaplingSelection saplingSelection;

    public Batch() {
    }

    public Batch(Long id, String code, GeneticMaterial geneticMaterial, Date stakingDate, int amount,
            SaplingSelection saplingSelection) {
        this.id = id;
        this.code = code;
        this.geneticMaterial = geneticMaterial;
        this.stakingDate = stakingDate;
        this.amount = amount;
        this.saplingSelection = saplingSelection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public GeneticMaterial getGeneticMaterial() {
        return geneticMaterial;
    }

    public void setGeneticMaterial(GeneticMaterial geneticMaterial) {
        this.geneticMaterial = geneticMaterial;
    }

    public Date getStakingDate() {
        return stakingDate;
    }

    public void setStakingDate(Date stakingDate) {
        this.stakingDate = stakingDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public SaplingSelection getSaplingSelection() {
        return saplingSelection;
    }

    public void setSaplingSelection(SaplingSelection saplingSelection) {
        this.saplingSelection = saplingSelection;
    }

   
}
