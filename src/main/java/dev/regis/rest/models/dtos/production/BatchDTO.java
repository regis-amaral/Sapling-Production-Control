package dev.regis.rest.models.dtos.production;

import java.io.Serializable;
import java.util.Date;
import dev.regis.rest.models.entities.production.SaplingSelection;

public class BatchDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private String code;
    private Date stakingDate;
    private int amount;
    private Long geneticMaterialId;
    private SaplingSelection saplingSelection;

    public BatchDTO() {
    }

    // public BatchDTO(Batch batch) {
    //     id = batch.getId();
    //     code = batch.getCode();
    //     stakingDate = batch.getStakingDate();
    //     amount = batch.getAmount();
    //     geneticMaterial = this.converter(batch.getGeneticMaterial());
    // }

    /**
     * Remove a recursão para a própria classe
     */
    // private GeneticMaterialDTO converter(GeneticMaterial geneticMaterial){
    //     GeneticMaterialDTO geneticMaterialDTO = new GeneticMaterialDTO(geneticMaterial);
    //     geneticMaterialDTO.setListBatchs(null);
    //     return geneticMaterialDTO;
    // }
    
    
    public Long getId() {
        return id;
    }

    public Long getGeneticMaterialId() {
        return geneticMaterialId;
    }

    public void setGeneticMaterialId(Long geneticMaterialId) {
        this.geneticMaterialId = geneticMaterialId;
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
    
    // /**
    //  * Converte uma lista ORM para DTO 
    //  * @param list
    //  * @return
    //  */
    // public static List<BatchDTO> convert(List<Batch> list){
    //     // List<BatchDTO> listBatchDTOs = new ArrayList<>();
    //     // for (Batch batch : list) {
    //     //     BatchDTO batchDTO = new BatchDTO(batch);
    //     //     batchDTO.setGeneticMaterial(new GeneticMaterial());
    //     //     listBatchDTOs.add(batchDTO);
    //     // }
    //     // return listBatchDTOs;
    //     return list.stream().map(BatchDTO::new).collect(Collectors.toList());
    // }

    public SaplingSelection getSaplingSelection() {
        return saplingSelection;
    }

    public void setSaplingSelection(SaplingSelection saplingSelection) {
        this.saplingSelection = saplingSelection;
    }
    
}
