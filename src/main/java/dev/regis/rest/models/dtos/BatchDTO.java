package dev.regis.rest.models.dtos;

import java.io.Serializable;
import java.util.Date;

public class BatchDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private String code;
    private GeneticMaterialDTO geneticMaterial;
    private Date stakingDate;
    private int amount;
    
    public BatchDTO() {
    }

    public BatchDTO(String code, GeneticMaterialDTO geneticMaterial, Date stakingDate, int amount) {
        this.code = code;
        this.geneticMaterial = geneticMaterial;
        this.stakingDate = stakingDate;
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public GeneticMaterialDTO getGeneticMaterial() {
        return geneticMaterial;
    }

    public void setGeneticMaterial(GeneticMaterialDTO geneticMaterial) {
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


    
}
