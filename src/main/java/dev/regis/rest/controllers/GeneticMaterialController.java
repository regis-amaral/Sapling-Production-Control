package dev.regis.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.regis.rest.controllers.interfaces.GeneticMaterialControllerInterface;
import dev.regis.rest.models.dtos.GeneticMaterialDTO;
import dev.regis.rest.services.GeneticMaterialService;

@RestController
@RequestMapping(value = "/api/genetic-material")
public class GeneticMaterialController implements GeneticMaterialControllerInterface {

    @Autowired
	GeneticMaterialService service;

    @GetMapping
    @Override
	public List<GeneticMaterialDTO> listAll() {
		return service.listAll();
	}

    @Override
    public void save(Object entity) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @DeleteMapping(value = "/{id}")
    @Override
	public void deleteById(@PathVariable Long id) {
		service.deleteById(id);
	}


    @GetMapping(value = "/{id}")
    @Override
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		GeneticMaterialDTO geneticMaterial = service.findById(id);

		if (geneticMaterial == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(geneticMaterial);
	}

}
