package dev.regis.rest.models.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.regis.rest.models.production.GeneticMaterial;
import dev.regis.rest.models.production.dtos.GeneticMaterialDTO;

@Mapper(uses = MapBatch.class)
public interface MapGeneticMaterial {
    
    GeneticMaterialDTO toGeneticMaterialDTO(GeneticMaterial geneticMaterial);

    List<GeneticMaterialDTO> toGeneticMaterialDTOList(List<GeneticMaterial> geneticMaterials);

    @Mapping(source = "listBatchs", target = "listBatchs")
    GeneticMaterial toGeneticMaterial(GeneticMaterialDTO geneticMaterialDTO);
}
