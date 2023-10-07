package dev.regis.rest.models.production.dtos;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SpecieDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Schema(hidden = true)
    private Long id;

    @Schema(example = "E. dunnii", required = false)
    @NotBlank
    private String name;

}
