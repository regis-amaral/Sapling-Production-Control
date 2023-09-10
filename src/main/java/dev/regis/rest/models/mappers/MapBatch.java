package dev.regis.rest.models.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.regis.rest.models.production.Batch;
import dev.regis.rest.models.production.dtos.BatchDTO;

@Mapper
public interface MapBatch {

    BatchDTO toBatchDTO(Batch batch);

    Batch toBatch(BatchDTO batchDTO);

    @Mapping(target = "geneticMaterial.id", source = "batchDTO.geneticMaterialId")
    List<Batch> toBatchList(List<BatchDTO> batchDTOList);

    @Mapping(target = "geneticMaterialId", source = "batch.geneticMaterial.id")
    List<BatchDTO> toBatchDTOList(List<Batch> batchList);
}
