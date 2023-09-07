package dev.regis.rest.models.dtos;

import java.io.Serializable;

public class GeneticMaterialDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private long specieId;
    
    public GeneticMaterialDTO() {
    }

    public GeneticMaterialDTO(String name, String description, long specieId) {
        this.name = name;
        this.description = description;
        this.specieId = specieId;
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

    public long getSpecieId() {
        return specieId;
    }

    public void setSpecieId(long specieId) {
        this.specieId = specieId;
    }
    

}
