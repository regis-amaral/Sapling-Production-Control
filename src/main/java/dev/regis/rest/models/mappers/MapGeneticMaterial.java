package dev.regis.rest.models.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.regis.rest.models.dtos.production.GeneticMaterialDTO;
import dev.regis.rest.models.entities.production.GeneticMaterial;

@Mapper(uses = MapBatch.class)
public interface MapGeneticMaterial {
    
    GeneticMaterialDTO toGeneticMaterialDTO(GeneticMaterial geneticMaterial);

    List<GeneticMaterialDTO> toGeneticMaterialDTOList(List<GeneticMaterial> geneticMaterials);

    @Mapping(source = "listBatchs", target = "listBatchs")
    GeneticMaterial toGeneticMaterial(GeneticMaterialDTO geneticMaterialDTO);
}
