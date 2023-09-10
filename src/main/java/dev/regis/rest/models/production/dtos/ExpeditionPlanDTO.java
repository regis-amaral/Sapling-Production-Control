package dev.regis.rest.models.production.dtos;

import java.io.Serializable;
import java.time.Month;

import dev.regis.rest.models.person.dtos.ClientDTO;
import dev.regis.rest.models.production.GeneticMaterial;
import lombok.Data;

@Data
public class ExpeditionPlanDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Long id;

    private int planned;

    private int realized;

    private Month month;

    private GeneticMaterial geneticMaterial;
    
    private ClientDTO client;

}
