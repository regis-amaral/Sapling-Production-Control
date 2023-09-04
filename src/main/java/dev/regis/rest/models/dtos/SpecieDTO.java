package dev.regis.rest.models.dtos;

import java.io.Serializable;

public class SpecieDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String name;
    private GeneticMaterialDTO geneticMaterial;

    public SpecieDTO() {
    }

    public SpecieDTO(String name, GeneticMaterialDTO geneticMaterial) {
        this.name = name;
        this.geneticMaterial = geneticMaterial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GeneticMaterialDTO getGeneticMaterial() {
        return geneticMaterial;
    }

    public void setGeneticMaterial(GeneticMaterialDTO geneticMaterial) {
        this.geneticMaterial = geneticMaterial;
    }
    
    
}
