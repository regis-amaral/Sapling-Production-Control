package dev.regis.rest.models.production.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import dev.regis.rest.models.production.entities.Batch;
import dev.regis.rest.models.production.entities.GeneticMaterial;

public class BatchDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private String code;
    private Date stakingDate;
    private int amount;
    private GeneticMaterialDTO geneticMaterial;

    public BatchDTO() {
    }

    public BatchDTO(Batch batch) {
        id = batch.getId();
        code = batch.getCode();
        stakingDate = batch.getStakingDate();
        amount = batch.getAmount();
        geneticMaterial = new GeneticMaterialDTO(batch.getGeneticMaterial());
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GeneticMaterialDTO getGeneticMaterial() {
        return geneticMaterial;
    }

    public void setGeneticMaterial(GeneticMaterial geneticMaterial) {
        this.geneticMaterial = new GeneticMaterialDTO(geneticMaterial);
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
    public static List<BatchDTO> convert(List<Batch> batchList){
        return batchList.stream().map(BatchDTO::new).collect(Collectors.toList());
    }
    
}
