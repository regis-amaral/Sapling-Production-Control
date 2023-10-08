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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/expedition-plan")
@Tag(name = "Planos de Expedição", description = "Endpoints para gerenciamento dos Planos de Expedição")
public class ExpeditionPlanController{
    
    @Autowired
    ExpeditionPlanService service;

    @GetMapping
    @Operation(summary = "Retorna todos os planos de expedição cadastrados", description = "Retorna uma lista com todos os planos de expedição existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada")
        })
    public List<ExpeditionPlanDTO> listAll(){
        return service.listAll();
    }
    
    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca um plano de expedição pelo ID", description = "Retorna o plano de expedição com o ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plano de expedição encontrado"),
            @ApiResponse(responseCode = "404", description = "Not found - Não foi possível encontrar o plano de expedição")
    })
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		try {
			ExpeditionPlanDTO outputDTO = service.findById(id);
			return ResponseEntity.ok(outputDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Deleta um plano de expedição pelo ID", description = "Deleta um plano de expedição pelo ID especificado")
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
    @Operation(summary = "Insere um novo plano de expedição", description = "Insere um novo plano de expedição com os dados especificados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plano de expedição criado"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Os dados informados não foram aceitos")
    })
	public ResponseEntity<Object> create(@Valid @RequestBody ExpeditionPlanDTO newObjectDTO) {
		try {
			return ResponseEntity.ok(service.create(newObjectDTO));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Atualiza um plano de expedição existente", description = "Atualiza um plano de expedição com os dados especificados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plano de expedição atualizado"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Os dados informados não foram aceitos")
    })
	public ResponseEntity<Object> update(@Valid @RequestBody ExpeditionPlanDTO newObjectDTO){
        try {
            return ResponseEntity.ok(service.update(newObjectDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
	}

}
