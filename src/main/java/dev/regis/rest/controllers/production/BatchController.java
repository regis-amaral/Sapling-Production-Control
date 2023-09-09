package dev.regis.rest.controllers.production;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.regis.rest.models.production.dtos.BatchDTO;
import dev.regis.rest.services.BatchService;

@RestController
@RequestMapping(value = "/api/batch")
public class BatchController{

    @Autowired
    BatchService service;


    @GetMapping
    public List<BatchDTO> listAll(){
        return service.listAll();
    }
    
}
