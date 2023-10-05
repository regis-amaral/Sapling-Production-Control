package dev.regis.rest.services;

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

    private void validateBachDTO(BatchDTO objectDTO)throws Exception {
        //
        if(objectDTO.getCode() == null ||
            objectDTO.getCode().trim().isEmpty()){
            throw new Exception("Parâmetro código inválido"); 
        }

        //
        if (objectDTO.getStakingDate() == null) {
            throw new Exception("A data de estaquia não pode ser nula");
        }

        //
        Date today = new Date();
        if (objectDTO.getStakingDate().after(today)) {
            throw new Exception("A data de estaquia não pode ser maior que a data atual");
        }

        //
        if (objectDTO.getAmount() < 1) {
            throw new Exception("A quantidade deve ser um número maior que zero");
        }

        //
        if(objectDTO.getGeneticMaterial() == null || 
            objectDTO.getGeneticMaterial().getId() == null ||
            objectDTO.getGeneticMaterial().getId() < 1){
            throw new Exception("Material genético inválido");
        }
    }

	public Long create(BatchDTO newObjectDTO) throws Exception {

        this.validateBachDTO(newObjectDTO);

		try{
            return super.create(newObjectDTO, Batch.class);
        } catch(ConstraintViolationException | DataIntegrityViolationException e){
            throw new Exception("Dados informados violam restrições no BD.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Um erro ocorreu!");
        }
	}

    public void deleteById(Long id) throws Exception{
        super.deleteById(id);
    }

    public Long update(BatchDTO newObjectDTO) throws Exception {

        if(newObjectDTO.getId() == null || 
            newObjectDTO.getId() < 1){
            throw new Exception("ID inválido!");
        }

        this.validateBachDTO(newObjectDTO);

        try{
            return super.update(newObjectDTO);
        } catch(ConstraintViolationException | DataIntegrityViolationException e){
            throw new Exception("Dados informados violam restrições no BD.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Um erro ocorreu!");
        }
    }

}
