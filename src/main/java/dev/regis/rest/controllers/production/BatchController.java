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
import dev.regis.rest.models.production.dtos.BatchInputDTO;
import dev.regis.rest.services.BatchService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/batch")
public class BatchController {

    @Autowired
    BatchService batchService;

    @GetMapping
    public List<BatchDTO> listAll(){
        return batchService.listAll();
    }
    
    @GetMapping(value = "/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		try {
			BatchDTO batch = batchService.findById(id);
			return ResponseEntity.ok(batch);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

    @DeleteMapping(value = "/delete/{id}")
	public void delete(@PathVariable Long id) {
		batchService.deleteById(id);
	}

    @PostMapping(value = "/create")
	public ResponseEntity<Object> create(@Valid @RequestBody BatchInputDTO batchDTO) {
		try {
			return ResponseEntity.ok(batchService.create(batchDTO));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

    @PutMapping(value = "/update")
	public ResponseEntity<Object> update(@Valid @RequestBody BatchInputDTO newBatchDTO){
        try {
            return ResponseEntity.ok(batchService.update(newBatchDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
	}
}
