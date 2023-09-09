package dev.regis.rest.models.dtos.production;

import java.io.Serializable;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import dev.regis.rest.models.entities.production.GeneticMaterial;
import dev.regis.rest.models.entities.production.SaplingSelection;

public class BatchDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long id;
    
    private String code;
    
    private GeneticMaterial geneticMaterial;

    private Date stakingDate;
    
    private int amount;
    
    private SaplingSelection saplingSelection;

    @Autowired
    ModelMapper mapper;

    public BatchDTO() {
    }


    public BatchDTO(Long id, String code, GeneticMaterial geneticMaterial, Date stakingDate, int amount,
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
