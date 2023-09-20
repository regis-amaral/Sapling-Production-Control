package dev.regis.rest.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        if (id == null || id < 1) {
            throw new IllegalArgumentException("ID inválido!");
        }
        return super.findById(id, BatchDTO.class);
    }

	public Long create(BatchDTO newObjectDTO) throws Exception {

        //
        if(newObjectDTO.getCode() == null ||
            newObjectDTO.getCode().trim().isEmpty()){
            throw new Exception("Parâmetro inválido"); 
        }

        //
        Date stakingDate = newObjectDTO.getStakingDate();
        if (stakingDate == null) {
            throw new Exception("A data de estaquia não pode ser nula");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            Date today = new Date();
            if (stakingDate.after(today)) {
                throw new Exception("A data de estaquia não pode ser maior que a data atual");
            }
            dateFormat.parse(dateFormat.format(stakingDate));
        } catch (Exception e) {
            throw new Exception("A data de estaquia não está no formato esperado (yyyy-MM-dd)");
        }

        //
        if (newObjectDTO.getAmount() <= 0) {
            throw new Exception("A quantidade deve ser um número inteiro positivo");
        }

        //
        if(newObjectDTO.getGeneticMaterial() == null || newObjectDTO.getGeneticMaterial().getId() < 1){
            throw new Exception("Material genético inválido");
        }

		try{
            return super.create(newObjectDTO, Batch.class);
        } catch(ConstraintViolationException | DataIntegrityViolationException e){
            throw new Exception("Dados informados violam restrições no BD.");
        } catch (Exception e) {
            throw new Exception("Um erro ocorreu!");
        }
	}

    public void deleteById(Long id) {
        super.deleteById(id);
    }

    public Long update(BatchDTO objectDTO) throws Exception {
        return super.update(objectDTO);
    }

}
