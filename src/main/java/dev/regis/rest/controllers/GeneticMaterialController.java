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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.regis.rest.models.dtos.GeneticMaterialDTO;
import dev.regis.rest.services.GeneticMaterialService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/genetic-material")
public class GeneticMaterialController {

    @Autowired
	GeneticMaterialService geneticMaterialService;



    @GetMapping
	public List<GeneticMaterialDTO> listAll() {
		return geneticMaterialService.listAll();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		try {
			GeneticMaterialDTO geneticMaterial = geneticMaterialService.findById(id);
			return ResponseEntity.ok(geneticMaterial);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(value = "/delete/{id}")
	public void delete(@PathVariable Long id) {
		geneticMaterialService.deleteById(id);
	}

	@PostMapping(value = "/create")
	public ResponseEntity<Object> create(@Valid @RequestBody GeneticMaterialDTO geneticMaterialDTO) {
		System.out.println("controller create material genetico");
		try {
			return ResponseEntity.ok(geneticMaterialService.create(geneticMaterialDTO));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Object> update(@Valid @RequestBody GeneticMaterialDTO newGeneticMaterialDTO, @PathVariable Long id){
        try {
            return ResponseEntity.ok(geneticMaterialService.update(id, newGeneticMaterialDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
	}

    
	@GetMapping("/search")
    public ResponseEntity<List<GeneticMaterialDTO>> search(
            @RequestParam("name") String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "orderBy", defaultValue = "id", required = false) String orderBy,
            @RequestParam(value = "itensPerPage", defaultValue = "10", required = false) Integer itensPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction
    ) {
		List<GeneticMaterialDTO> geneticMaterialDTOs =  geneticMaterialService.search(name, page, orderBy, itensPerPage, direction);
		return ResponseEntity.ok(geneticMaterialDTOs);
    }
}
