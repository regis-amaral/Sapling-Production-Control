package dev.regis.rest.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<ExpeditionPlanDTO> listAll() {
        return super.listAllObjects(ExpeditionPlanDTO.class);  
    }

    @Override
    public ExpeditionPlanDTO findById(Long id) throws Exception {
        return super.findObjectById(id, ExpeditionPlanDTO.class);
    }

    @Override
    public Long create(ExpeditionPlanDTO objectDTO) throws Exception {
        return super.createNewObject(objectDTO, ExpeditionPlan.class);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteObjectById(id);
    }

    @Override
    public Long update(ExpeditionPlanDTO objectDTO) throws Exception {
        return super.updateObject(objectDTO);
    }
    
}
