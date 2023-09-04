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
    private Specie specie;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date stakingDate;

    @Column(nullable = false)
    private int amount;

    @ManyToMany(mappedBy = "batchList")
    private List<SaplingSelection> saplingSelection;

    public Batch() {
    }

    public Batch(int id, String code, Specie specie, Date stakingDate, int amount) {
        this.id = id;
        this.code = code;
        this.specie = specie;
        this.stakingDate = stakingDate;
        this.amount = amount;
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

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
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

}
