package dev.regis.rest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.regis.rest.models.production.Batch;
import dev.regis.rest.models.production.dtos.BatchDTO;
import dev.regis.rest.repositories.BatchRepository;

@Service
public class BatchService {

    @Autowired
    BatchRepository repository;

    @Autowired
    ModelMapper mapper;
    
    
    public List<BatchDTO> listAll() {

        // List<BatchDTO> listBatchDTOs = new ArrayList<>();
        // repository.findAll().forEach(batch -> listBatchDTOs.add(mapper.map(batch, BatchDTO.class)));
        // return listBatchDTOs;

        return repository.findAll().stream()
            .map(batch -> mapper.map(batch, BatchDTO.class))
            .collect(Collectors.toList());
            
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
