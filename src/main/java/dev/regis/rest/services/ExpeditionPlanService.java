package dev.regis.rest.services;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import dev.regis.rest.models.production.ExpeditionPlan;
import dev.regis.rest.models.production.dtos.ExpeditionPlanDTO;
import dev.regis.rest.repositories.ExpeditionPlanRepository;
import dev.regis.rest.services.interfaces.IService;

@Service
public class ExpeditionPlanService extends AbstractService <ExpeditionPlan, ExpeditionPlanDTO> implements IService <ExpeditionPlan, ExpeditionPlanDTO>{

    @Autowired
    ExpeditionPlanRepository repository;

    @Autowired
    ModelMapper mapper;

    public List<ExpeditionPlanDTO> listAll() {
        return super.listAll(ExpeditionPlanDTO.class);  
    }

    public ExpeditionPlanDTO findById(Long id) throws Exception {
        if (id == null || id < 1) {
            throw new IllegalArgumentException("ID inválido!");
        }
        return super.findById(id, ExpeditionPlanDTO.class);
    }

    public Long create(ExpeditionPlanDTO objectDTO) throws Exception {
        //
        if(objectDTO.getPlanned() < 1){
            throw new Exception("A quantidade planejada é inválida");
        }
        //
        if(objectDTO.getRealized() < 0){
            throw new Exception("A quantidade realizada é inválida");
        }
        //
        if(objectDTO.getMonth() == null){
            throw new Exception("Mês de expedição inválido");
        }
        //
        if(objectDTO.getClient() == null){
            throw new Exception("Cliente não informado");
        }
        //
        if(objectDTO.getGeneticMaterial() == null){
            throw new Exception("Material genético não informado");
        }
        
        try{
            return super.create(objectDTO, ExpeditionPlan.class);
        }catch(ConstraintViolationException | DataIntegrityViolationException e){
            throw new Exception("Dados informados violam restrições no BD.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Um erro ocorreu!");
        } 
    }

    public void deleteById(Long id) throws Exception{
        super.deleteById(id);
    }

    public Long update(ExpeditionPlanDTO objectDTO) throws Exception {
        //
        if (objectDTO.getId() == null || objectDTO.getId() < 1) {
            throw new IllegalArgumentException("ID inválido!");
        }
        //
        if(objectDTO.getPlanned() < 1){
            throw new Exception("A quantidade planejada é inválida");
        }
        //
        if(objectDTO.getRealized() < 0){
            throw new Exception("A quantidade realizada é inválida");
        }
        //
        if(objectDTO.getMonth() == null){
            throw new Exception("Mês de expedição inválido");
        }
        //
        if(objectDTO.getClient() == null){
            throw new Exception("Cliente não informado");
        }
        //
        if(objectDTO.getGeneticMaterial() == null){
            throw new Exception("Material genético não informado");
        }
        
        try{
            return super.update(objectDTO);
        }catch(ConstraintViolationException | DataIntegrityViolationException e){
            throw new Exception("Dados informados violam restrições no BD.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Um erro ocorreu!");
        } 
    }
    
}
