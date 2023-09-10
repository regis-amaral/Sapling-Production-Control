package dev.regis.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.regis.rest.models.mappers.MapBatch;
import dev.regis.rest.models.production.Batch;
import dev.regis.rest.models.production.dtos.BatchDTO;
import dev.regis.rest.models.production.dtos.BatchInputDTO;
import dev.regis.rest.repositories.BatchRepository;
import dev.regis.rest.services.interfaces.IService;

@Service
public class BatchService 
    extends AbstractService<Batch, BatchInputDTO, BatchDTO> 
    implements IService <Batch, BatchInputDTO, BatchDTO> {

    @Autowired
    BatchRepository batchRepository;

    @Autowired
	MapBatch mapBatch;
    
    @Override
    public List<BatchDTO> listAll() {
        List<Batch> list = batchRepository.findAll();
        return mapBatch.toBatchDTOList(list);
        // return super.listAllObjects(Batch.class, BatchDTO.class);
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
