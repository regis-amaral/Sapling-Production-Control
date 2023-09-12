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

import dev.regis.rest.models.production.dtos.SaplingSelectionDTO;
import dev.regis.rest.services.SaplingSelectionService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/sapling-selection")
public class SaplingSelectionController{
    
    @Autowired
    SaplingSelectionService service;

    @GetMapping
    public List<SaplingSelectionDTO> listAll(){
        return service.listAll();
    }
    
    @GetMapping(value = "/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		try {
			SaplingSelectionDTO outputDTO = service.findById(id);
			return ResponseEntity.ok(outputDTO);
		} catch (Exception e) {
			// TODO tratar exceções
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

    @DeleteMapping(value = "/delete/{id}")
	public void delete(@PathVariable Long id) {
		service.deleteById(id);
	}

    @PostMapping(value = "/create")
	public ResponseEntity<Object> create(@Valid @RequestBody SaplingSelectionDTO newObjectDTO) {
		try {
			return ResponseEntity.ok(service.create(newObjectDTO));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

    @PutMapping(value = "/update")
	public ResponseEntity<Object> update(@Valid @RequestBody SaplingSelectionDTO newObjectDTO){
        try {
            return ResponseEntity.ok(service.update(newObjectDTO));
        } catch (Exception e) {
			e.printStackTrace(); // TODO tratar exeções
            return ResponseEntity.badRequest().body(e.getMessage());
        }
	}

}
