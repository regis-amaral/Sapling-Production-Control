package dev.regis.rest.models.dtos;

import java.io.Serializable;

public class GeneticMaterialDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    public GeneticMaterialDTO() {
    }

    public GeneticMaterialDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
