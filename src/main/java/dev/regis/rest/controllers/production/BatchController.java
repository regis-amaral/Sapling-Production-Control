package dev.regis.rest.controllers.production;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.regis.rest.models.production.dtos.BatchDTO;
import dev.regis.rest.services.BatchService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/batch")
public class BatchController {

    @Autowired
    BatchService service;

    @GetMapping
    public List<BatchDTO> listAll(){
        return service.listAll();
    }
    
    @GetMapping(value = "/{id}")
	public ResponseEntity<BatchDTO> findById(@PathVariable Long id) {
		try {
			BatchDTO outputDTO = service.findById(id);
			return ResponseEntity.ok(outputDTO);
		} catch (Exception e) {
			// TODO tratar exceções
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

    @DeleteMapping(value = "/delete/{id}")
	public void delete(@PathVariable Long id) {
		try{
			service.deleteById(id);
		} catch (Exception e) {
			//
		}
	}

    @PostMapping(value = "/create")
	public ResponseEntity<Object> create(@Valid @RequestBody BatchDTO newObjectDTO) {
		try {
			return ResponseEntity.ok(service.create(newObjectDTO));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

    @PutMapping(value = "/update")
	public ResponseEntity<Object> update(@Valid @RequestBody BatchDTO newObjectDTO){
        try {
            return ResponseEntity.ok(service.update(newObjectDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
	}
}
