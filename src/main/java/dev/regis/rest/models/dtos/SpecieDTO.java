package dev.regis.rest.models.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import dev.regis.rest.models.entities.Specie;

public class SpecieDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private long id;
    private String name;

    public SpecieDTO(){

    }

    public SpecieDTO(Specie specie) {
        id = specie.getId();
        name = specie.getName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
 
    /**
     * Converte uma lista ORM para DTO 
     * @param speciesList
     * @return
     */
    public static List<SpecieDTO> convertList(List<Specie> speciesList){
        return speciesList.stream().map(SpecieDTO::new).collect(Collectors.toList());
    }
}
