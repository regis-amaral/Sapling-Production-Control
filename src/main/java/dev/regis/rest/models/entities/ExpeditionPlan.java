package dev.regis.rest.models.entities;

import java.time.Month;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ExpeditionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int planned;
    
    @Column(nullable = true)
    private int realized;

    @Enumerated
    @Column(nullable = false, columnDefinition = "smallint")
    private Month month;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GeneticMaterial geneticMaterial;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Client client;

    public ExpeditionPlan() {
    }

    public ExpeditionPlan(long id, int planned, int realized, Month month, GeneticMaterial geneticMaterial) {
        this.id = id;
        this.planned = planned;
        this.realized = realized;
        this.month = month;
        this.geneticMaterial = geneticMaterial;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPlanned() {
        return planned;
    }

    public void setPlanned(int planned) {
        this.planned = planned;
    }

    public int getRealized() {
        return realized;
    }

    public void setRealized(int realized) {
        this.realized = realized;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public GeneticMaterial getGeneticMaterial() {
        return geneticMaterial;
    }

    public void setGeneticMaterial(GeneticMaterial geneticMaterial) {
        this.geneticMaterial = geneticMaterial;
    }

    
}
