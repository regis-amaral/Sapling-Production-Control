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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.regis.rest.models.production.dtos.GeneticMaterialDTO;
import dev.regis.rest.services.GeneticMaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/genetic-material")
@Tag(name = "Materiais Genéticos", description = "Endpoints para gerenciamento dos Materiais Genéticos")
public class GeneticMaterialController {

	@Autowired
	GeneticMaterialService service;

	@GetMapping
	@Operation(summary = "Retorna todos os materiais genéticos cadastrados", description = "Retorna uma lista com todos os materiais genéticos existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada")
        })
	public List<GeneticMaterialDTO> listAll() {
		return service.listAll();
	}

	@GetMapping(value = "/{id}")
	@Operation(summary = "Busca um material genético pelo ID", description = "Retorna um material genético com o ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Material genético encontrada"),
            @ApiResponse(responseCode = "404", description = "Not found - Não foi possível encontrar o material genético")
    })
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		try {
			GeneticMaterialDTO outputDTO = service.findById(id);
			return ResponseEntity.ok(outputDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(value = "/delete/{id}")
	@Operation(summary = "Deleta um material genético pelo ID", description = "Deleta um material genético pelo ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comando recebido")
    })
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		try{
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
	}

	@PostMapping(value = "/create")
	public ResponseEntity<Object> create(@Valid @RequestBody GeneticMaterialDTO newObjectDTO) {
		try {
			return ResponseEntity.ok(service.create(newObjectDTO));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping(value = "/update")
	public ResponseEntity<Object> update(@Valid @RequestBody GeneticMaterialDTO newObjectDTO) {
		try {
			return ResponseEntity.ok(service.update(newObjectDTO));
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
			@RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction) {
		List<GeneticMaterialDTO> geneticMaterialDTOs = service.search(name, page, orderBy, itensPerPage, direction);
		return ResponseEntity.ok(geneticMaterialDTOs);
	}
}
