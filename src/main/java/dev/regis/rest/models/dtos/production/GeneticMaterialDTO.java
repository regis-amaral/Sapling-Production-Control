package dev.regis.rest.models.dtos.production;

import java.io.Serializable;
import java.util.List;

public class GeneticMaterialDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private SpecieDTO specie;

    private List<BatchDTO> listBatchs;

    
    public GeneticMaterialDTO(){

    }

    // public GeneticMaterialDTO(GeneticMaterial geneticMaterial) {
    //     id = geneticMaterial.getId();
    //     name = geneticMaterial.getName();
    //     description = geneticMaterial.getDescription();
    //     specie = new SpecieDTO(geneticMaterial.getSpecie());
    //     listBatchs = converter(geneticMaterial.getListBatchs());
    // }

    // private List<BatchDTO> converter(List<Batch> list){
    //     List<BatchDTO> listDTO = new ArrayList<>();
    //     for (Batch batch : list) {
    //         // TODO melhorar essa conversão

    //         BatchDTO batchDTO = new BatchDTO();

    //         batchDTO.setAmount(batch.getAmount());
    //         batchDTO.setCode(batch.getCode());
    //         batchDTO.setId(batch.getId());
    //         batchDTO.setSaplingSelection(batch.getSaplingSelection());
    //         batchDTO.setStakingDate(batch.getStakingDate());

    //         listDTO.add(batchDTO);
    //     }
    //     return listDTO;
    // }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SpecieDTO getSpecie() {
        return specie;
    }

    public void setSpecie(SpecieDTO specie) {
        this.specie = specie;
    }

    public List<BatchDTO> getListBatchs() {
        return listBatchs;
    }

    public void setListBatchs(List<BatchDTO> listBatchs) {
        this.listBatchs = listBatchs;
    }

    /**
     * Converte uma lista ORM para DTO 
     * @param speciesList
     * @return
     */
    // public static List<GeneticMaterialDTO> convert(List<GeneticMaterial> speciesList){
    //     return speciesList.stream().map(GeneticMaterialDTO::new).collect(Collectors.toList());
    // }
}
