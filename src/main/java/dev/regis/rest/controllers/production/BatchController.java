package dev.regis.rest.controllers.production;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.regis.rest.controllers.AbstractController;
import dev.regis.rest.models.dtos.production.BatchDTO;
import dev.regis.rest.models.dtos.production.BatchInputDTO;
import dev.regis.rest.models.entities.production.Batch;
import dev.regis.rest.services.BatchService;

@RestController
@RequestMapping(value = "/api/batch")
public class BatchController extends AbstractController <Batch, BatchInputDTO, BatchDTO> {

    @Autowired
    BatchService batchService;
    
}
