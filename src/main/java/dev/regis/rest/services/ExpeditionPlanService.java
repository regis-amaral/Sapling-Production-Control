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

    public List<ExpeditionPlanDTO> listAll() {
        return super.listAll(ExpeditionPlanDTO.class);  
    }

    public ExpeditionPlanDTO findById(Long id) throws Exception {
        return super.findById(id, ExpeditionPlanDTO.class);
    }

    public Long create(ExpeditionPlanDTO objectDTO) throws Exception {
        return super.create(objectDTO, ExpeditionPlan.class);
    }

    public void deleteById(Long id) {
        super.deleteById(id);
    }

    public Long update(ExpeditionPlanDTO objectDTO) throws Exception {
        return super.update(objectDTO);
    }
    
}
