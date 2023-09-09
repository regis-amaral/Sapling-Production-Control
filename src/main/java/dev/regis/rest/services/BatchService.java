package dev.regis.rest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.regis.rest.models.dtos.production.BatchDTO;
import dev.regis.rest.models.entities.production.Batch;
import dev.regis.rest.repositories.BatchRepository;

@Service
public class BatchService {

    @Autowired
    BatchRepository repository;

    @Autowired
    ModelMapper mapper;
    
    
    public List<BatchDTO> listAll() {
        List<BatchDTO> listBatchDTOs = new ArrayList<>();
        List<Batch> listBatch = repository.findAll();
        for (Batch batch : listBatch) {
            listBatchDTOs.add(mapper.map(batch, BatchDTO.class));
        }
        return listBatchDTOs;
        // return super.listAllObjects(Batch.class, BatchDTO.class);
    }

    // @Override
    // public BatchDTO findById(Long id) throws Exception{
    //     return super.findObjectById(id, BatchDTO.class);
    // }

	// @Override
	// public Long create(BatchInputDTO objectDTO) throws Exception {
	// 	return super.createNewObject(objectDTO, Batch.class);
	// }

    // @Override
    // public void deleteById(Long id) {
    //     super.deleteObjectById(id);
    // }

    // @Override
    // public Long update(BatchInputDTO objectDTO) throws Exception {
    //     return super.updateObject(objectDTO);
    // }

}
