package dev.regis.rest.models.production.dtos;

import java.io.Serializable;
import java.util.List;

import dev.regis.rest.models.production.Batch;
import dev.regis.rest.models.production.Specie;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GeneticMaterialDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private Specie specie;

    private List<Batch> listBatchs;

}
