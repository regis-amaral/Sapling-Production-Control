package dev.regis.rest.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.regis.rest.models.production.Batch;
import dev.regis.rest.models.production.dtos.BatchDTO;
import dev.regis.rest.repositories.BatchRepository;

@Service
public class BatchService extends AbstractService <Batch, BatchDTO>{

    @Autowired
    BatchRepository repository;

    @Autowired
    ModelMapper mapper;
    
    
    public List<BatchDTO> listAll() {
        return super.listAll(BatchDTO.class);  
    }

    public BatchDTO findById(Long id) throws Exception{
        return super.findById(id, BatchDTO.class);
    }

	public Long create(BatchDTO objectDTO) throws Exception {
		return super.create(objectDTO, Batch.class);
	}

    public void deleteById(Long id) {
        super.deleteById(id);
    }

    public Long update(BatchDTO objectDTO) throws Exception {
        return super.update(objectDTO);
    }

}
