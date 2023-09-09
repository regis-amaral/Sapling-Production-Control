package dev.regis.rest.models.dtos.production;

import java.io.Serializable;
import java.util.List;

import dev.regis.rest.models.entities.production.GeneticMaterial;
import dev.regis.rest.models.entities.production.Specie;

public class GeneticMaterialInputDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private String description;
    private Specie specie;
    
    public GeneticMaterialInputDTO(){

    }

    public GeneticMaterialInputDTO(GeneticMaterial geneticMaterial) {
        id = geneticMaterial.getId();
        name = geneticMaterial.getName();
        description = geneticMaterial.getDescription();
        specie = geneticMaterial.getSpecie();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    /**
     * Converte uma lista ORM para DTO 
     * @param speciesList
     * @return
     */
    public static List<GeneticMaterialDTO> convert(List<GeneticMaterial> speciesList){
        return null;//speciesList.stream().map(GeneticMaterialDTO::new).collect(Collectors.toList());
    }
}
