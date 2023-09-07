package dev.regis.rest.models.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String code;

    @OneToOne(cascade = CascadeType.ALL)
    private GeneticMaterial geneticMaterial;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date stakingDate;

    @Column(nullable = false)
    private int amount;

    @ManyToMany(mappedBy = "batchList")
    private List<SaplingSelection> saplingSelection;

    public Batch() {
    }

    public Batch(int id, String code, GeneticMaterial geneticMaterial, Date stakingDate, int amount,
            List<SaplingSelection> saplingSelection) {
        this.id = id;
        this.code = code;
        this.geneticMaterial = geneticMaterial;
        this.stakingDate = stakingDate;
        this.amount = amount;
        this.saplingSelection = saplingSelection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<SaplingSelection> getSaplingSelection() {
        return saplingSelection;
    }

    public void setSaplingSelection(List<SaplingSelection> saplingSelection) {
        this.saplingSelection = saplingSelection;
    }

    
}
