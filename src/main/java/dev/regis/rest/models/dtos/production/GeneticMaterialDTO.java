package dev.regis.rest.models.dtos.production;

import java.io.Serializable;
import java.util.List;

import dev.regis.rest.models.entities.production.Batch;

public class GeneticMaterialDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String description;

    private SpecieDTO specie;

    private List<Batch> listBatchs;

    public GeneticMaterialDTO(){
    }

    public GeneticMaterialDTO(Long id, String name, String description, SpecieDTO specie, List<Batch> listBatchs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.specie = specie;
        this.listBatchs = listBatchs;
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

    public List<Batch> getListBatchs() {
        return listBatchs;
    }

    public void setListBatchs(List<Batch> listBatchs) {
        this.listBatchs = listBatchs;
    }

    
}
