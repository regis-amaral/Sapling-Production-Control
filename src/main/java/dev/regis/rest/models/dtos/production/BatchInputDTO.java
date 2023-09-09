package dev.regis.rest.models.dtos.production;

import java.io.Serializable;
import java.util.Date;

import dev.regis.rest.models.entities.production.Batch;
import dev.regis.rest.models.entities.production.GeneticMaterial;

public class BatchInputDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private String code;
    private Date stakingDate;
    private int amount;
    private GeneticMaterial geneticMaterial;

    public BatchInputDTO() {
    }

    public BatchInputDTO(Batch batch) {
        id = batch.getId();
        code = batch.getCode();
        stakingDate = batch.getStakingDate();
        amount = batch.getAmount();
        geneticMaterial = batch.getGeneticMaterial();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GeneticMaterial getGeneticMaterial() {
        return geneticMaterial;
    }

    public void setGeneticMaterial(GeneticMaterial geneticMaterial) {
        this.geneticMaterial = geneticMaterial;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    /**
     * Converte uma lista ORM para DTO 
     * @param speciesList
     * @return
     */
    // public static List<BatchDTO> convert(List<Batch> batchList){
    //     return batchList.stream().map(BatchDTO::new).collect(Collectors.toList());
    // }
    
}
