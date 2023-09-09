package dev.regis.rest.models.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.regis.rest.models.dtos.production.BatchDTO;
import dev.regis.rest.models.entities.production.Batch;

@Mapper
public interface MapBatch {

    BatchDTO toBatchDTO(Batch batch);

    Batch toBatch(BatchDTO batchDTO);

    @Mapping(target = "geneticMaterial.id", source = "batchDTO.geneticMaterialId")
    List<Batch> toBatchList(List<BatchDTO> batchDTOList);

    @Mapping(target = "geneticMaterialId", source = "batch.geneticMaterial.id")
    List<BatchDTO> toBatchDTOList(List<Batch> batchList);
}
