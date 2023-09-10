package dev.regis.rest.controllers.production;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.regis.rest.controllers.AbstractController;
import dev.regis.rest.models.production.GeneticMaterial;
import dev.regis.rest.models.production.dtos.GeneticMaterialDTO;
import dev.regis.rest.models.production.dtos.GeneticMaterialInputDTO;
import dev.regis.rest.services.GeneticMaterialService;

@RestController
@RequestMapping(value = "/api/genetic-material")
public class GeneticMaterialController extends AbstractController <GeneticMaterial, GeneticMaterialInputDTO, GeneticMaterialDTO>{

    @Autowired
	GeneticMaterialService geneticMaterialService;
    
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
