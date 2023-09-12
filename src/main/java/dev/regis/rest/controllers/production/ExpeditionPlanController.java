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

import dev.regis.rest.models.production.dtos.ExpeditionPlanDTO;
import dev.regis.rest.services.ExpeditionPlanService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/expedition-plan")
public class ExpeditionPlanController{
    
    @Autowired
    ExpeditionPlanService service;

    @GetMapping
    public List<ExpeditionPlanDTO> listAll(){
        return service.listAll();
    }
    
    @GetMapping(value = "/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		try {
			ExpeditionPlanDTO outputDTO = service.findById(id);
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
	public ResponseEntity<Object> create(@Valid @RequestBody ExpeditionPlanDTO newObjectDTO) {
		try {
			return ResponseEntity.ok(service.create(newObjectDTO));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

    @PutMapping(value = "/update")
	public ResponseEntity<Object> update(@Valid @RequestBody ExpeditionPlanDTO newObjectDTO){
        try {
            return ResponseEntity.ok(service.update(newObjectDTO));
        } catch (Exception e) {
			e.printStackTrace(); // TODO tratar exeções
            return ResponseEntity.badRequest().body(e.getMessage());
        }
	}

}
