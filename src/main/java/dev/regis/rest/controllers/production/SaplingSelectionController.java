package dev.regis.rest.controllers.production;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.regis.rest.controllers.AbstractController;
import dev.regis.rest.models.production.dtos.SaplingSelectionDTO;
import dev.regis.rest.models.production.dtos.SaplingSelectionInputDTO;
import dev.regis.rest.models.production.entities.SaplingSelection;
import dev.regis.rest.services.SaplingSelectionService;

@RestController
@RequestMapping(value = "/api/sapling-selection")
public class SaplingSelectionController extends AbstractController <SaplingSelection, SaplingSelectionInputDTO, SaplingSelectionDTO>{
    
    @Autowired
    SaplingSelectionService saplingSelectionService;

}
