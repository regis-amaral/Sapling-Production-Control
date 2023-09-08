package dev.regis.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.regis.rest.models.production.dtos.BatchDTO;
import dev.regis.rest.models.production.dtos.BatchInputDTO;
import dev.regis.rest.models.production.entities.Batch;
import dev.regis.rest.repositories.BatchRepository;
import dev.regis.rest.services.interfaces.IService;

@Service
public class BatchService 
    extends AbstractService<Batch, BatchInputDTO, BatchDTO> 
    implements IService <Batch, BatchInputDTO, BatchDTO> {

    @Autowired
    BatchRepository batchRepository;
    
    @Override
    public List<BatchDTO> listAll() {
        return super.listAllObjects(BatchDTO.class);
    }

    @Override
    public BatchDTO findById(Long id) throws Exception{
        return super.findObjectById(id, BatchDTO.class);
    }

	@Override
	public Long create(BatchInputDTO objectDTO) throws Exception {
		return super.createNewObject(objectDTO, Batch.class);
	}

    @Override
    public void deleteById(Long id) {
        super.deleteObjectById(id);
    }

    @Override
    public Long update(BatchInputDTO objectDTO) throws Exception {
        return super.updateObject(objectDTO);
    }

}
