package dev.regis.rest.controllers.production;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.regis.rest.controllers.AbstractController;
import dev.regis.rest.models.dtos.production.ExpeditionPlanDTO;
import dev.regis.rest.models.dtos.production.ExpeditionPlanInputDTO;
import dev.regis.rest.models.entities.production.ExpeditionPlan;
import dev.regis.rest.services.ExpeditionPlanService;

@RestController
@RequestMapping(value = "/api/expedition-plan")
public class ExpeditionPlanController extends AbstractController <ExpeditionPlan, ExpeditionPlanInputDTO, ExpeditionPlanDTO> {
    
    @Autowired
    ExpeditionPlanService expeditionPlanService;
}
