package dev.regis.rest.models.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import dev.regis.rest.models.entities.Specie;

public class SpecieDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String name;


    public SpecieDTO(Specie specie) {
        this.name = specie.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
 
    public static List<SpecieDTO> convert(List<Specie> speciesList){
        return speciesList.stream().map(SpecieDTO::new).collect(Collectors.toList());
    }
}
