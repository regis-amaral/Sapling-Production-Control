package dev.regis.rest.models.production.dtos;

import java.io.Serializable;
import java.util.Date;

import dev.regis.rest.models.production.GeneticMaterial;
import dev.regis.rest.models.production.SaplingSelection;
import lombok.Data;

@Data
public class BatchDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long id;
    
    private String code;

    private Date stakingDate;
    
    private int amount;
    
    private SaplingSelection saplingSelection;

    private GeneticMaterial geneticMaterial;

}
