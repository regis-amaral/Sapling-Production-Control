package dev.regis.rest.models.production.dtos;

import java.io.Serializable;

import lombok.Data;

@Data
public class SpecieDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

}
