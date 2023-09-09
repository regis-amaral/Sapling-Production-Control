package dev.regis.rest.models.dtos.production;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import dev.regis.rest.models.entities.production.Batch;
import dev.regis.rest.models.entities.production.GeneticMaterial;
import dev.regis.rest.models.entities.production.SaplingSelection;

public class BatchDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private String code;
    private Date stakingDate;
    private int amount;
    private GeneticMaterialDTO geneticMaterial;
    private SaplingSelection saplingSelection;

    @Autowired
    ModelMapper mapper;

    public BatchDTO() {
    }

    public BatchDTO(Batch batch) {
        id = batch.getId();
        code = batch.getCode();
        stakingDate = batch.getStakingDate();
        amount = batch.getAmount();
        geneticMaterial = this.converter(batch.getGeneticMaterial());
    }

    /**
     * Remove a recursão para a própria classe
     */
    private GeneticMaterialDTO converter(GeneticMaterial geneticMaterial){
        GeneticMaterialDTO geneticMaterialDTO = new GeneticMaterialDTO(geneticMaterial);
        geneticMaterialDTO.setListBatchs(null);
        return geneticMaterialDTO;
    }
    
    public static long getSerialversionuid() {
        return serialVersionUID;
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

    public GeneticMaterialDTO getGeneticMaterial() {
        return geneticMaterial;
    }

    public void setGeneticMaterial(GeneticMaterialDTO geneticMaterial) {
        this.geneticMaterial = geneticMaterial;
    }

    public ModelMapper getMapper() {
        return mapper;
    }

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }


    
    /**
     * Converte uma lista ORM para DTO 
     * @param list
     * @return
     */
    public static List<BatchDTO> convert(List<Batch> list){
        // List<BatchDTO> listBatchDTOs = new ArrayList<>();
        // for (Batch batch : list) {
        //     BatchDTO batchDTO = new BatchDTO(batch);
        //     batchDTO.setGeneticMaterial(new GeneticMaterial());
        //     listBatchDTOs.add(batchDTO);
        // }
        // return listBatchDTOs;
        return list.stream().map(BatchDTO::new).collect(Collectors.toList());
    }

    public SaplingSelection getSaplingSelection() {
        return saplingSelection;
    }

    public void setSaplingSelection(SaplingSelection saplingSelection) {
        this.saplingSelection = saplingSelection;
    }
    
}
