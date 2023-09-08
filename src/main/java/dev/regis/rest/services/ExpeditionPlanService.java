package dev.regis.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.regis.rest.models.dtos.production.ExpeditionPlanDTO;
import dev.regis.rest.models.dtos.production.ExpeditionPlanInputDTO;
import dev.regis.rest.models.entities.production.ExpeditionPlan;
import dev.regis.rest.repositories.ExpeditionPlanRepository;
import dev.regis.rest.services.interfaces.IService;

@Service
public class ExpeditionPlanService
        extends AbstractService<ExpeditionPlan, ExpeditionPlanInputDTO, ExpeditionPlanDTO>
        implements IService<ExpeditionPlan, ExpeditionPlanInputDTO, ExpeditionPlanDTO> {

    @Autowired
    ExpeditionPlanRepository expeditionPlanRepository;

    @Override
    public List<ExpeditionPlanDTO> listAll() {
        return super.listAllObjects(ExpeditionPlanDTO.class);
    }

    @Override
    public ExpeditionPlanDTO findById(Long id) throws Exception {
        return super.findObjectById(id, ExpeditionPlanDTO.class);
    }

    @Override
    public Long create(ExpeditionPlanInputDTO objectDTO) throws Exception {
        return super.createNewObject(objectDTO, ExpeditionPlan.class);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteObjectById(id);
    }

    @Override
    public Long update(ExpeditionPlanInputDTO objectDTO) throws Exception {
        return super.updateObject(objectDTO);
    }

}
