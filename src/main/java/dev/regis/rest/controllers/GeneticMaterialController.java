package dev.regis.rest.controllers;

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

import dev.regis.rest.models.dtos.GeneticMaterialDTO;
import dev.regis.rest.services.GeneticMaterialService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/genetic-material")
public class GeneticMaterialController {

    @Autowired
	GeneticMaterialService service;

    @GetMapping
	public List<GeneticMaterialDTO> listAll() {
		return service.listAll();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		GeneticMaterialDTO geneticMaterial = service.findById(id);

		if (geneticMaterial == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(geneticMaterial);
	}

	@DeleteMapping(value = "/delete/{id}")
	public void delete(@PathVariable Long id) {
		service.deleteById(id);
	}
	
	@PostMapping(value = "/insert")
    public void insert(@Valid @RequestBody GeneticMaterialDTO geneticMaterialDTO){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

	@PutMapping(value = "/update/{id}")
	public void update(@Valid @RequestBody GeneticMaterialDTO geneticMaterialDTO, @PathVariable Integer id){
		// TODO update
	}

    


    
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<Object> findByName(@PathVariable String name){
		GeneticMaterialDTO geneticMaterial = service.findByName(name);
		if (geneticMaterial == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(geneticMaterial);
	}
}
