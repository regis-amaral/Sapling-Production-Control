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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/sapling-selection")
@Tag(name = "Seleções de Mudas", description = "Endpoints para gerenciamento das Seleções de Mudas")
public class SaplingSelectionController{
    
    @Autowired
    SaplingSelectionService service;

    @GetMapping
    @Operation(summary = "Retorna todas as seleções de mudas cadastradas", description = "Retorna uma lista com todas as seleções de mudas existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada")
        })
    public List<SaplingSelectionDTO> listAll(){
        return service.listAll();
    }
    
    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca uma seleção de muda pelo ID", description = "Retorna a seleção de muda com o ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seleção de muda encontrada"),
            @ApiResponse(responseCode = "404", description = "Not found - Não foi possível encontrar a seleção de muda")
    })
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		try {
			SaplingSelectionDTO outputDTO = service.findById(id);
			return ResponseEntity.ok(outputDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Deleta uma seleção de muda pelo ID", description = "Deleta uma seleção de muda pelo ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comando recebido")
    })
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		try{
            service.deleteById(id);
        } catch (Exception e) {
            //
        }
        return ResponseEntity.ok().build();
	}

    @PostMapping(value = "/create")
    @Operation(summary = "Insere uma nova seleção de muda", description = "Insere uma nova seleção de muda com os dados especificados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seleção de muda criada"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Os dados informados não foram aceitos")
    })
	public ResponseEntity<Object> create(@Valid @RequestBody SaplingSelectionDTO newObjectDTO) {
		try {
			return ResponseEntity.ok(service.create(newObjectDTO));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Atualiza uma seleção de muda existente", description = "Atualiza uma seleção de muda com os dados especificados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seleção de muda atualizada"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Os dados informados não foram aceitos")
    })
	public ResponseEntity<Object> update(@Valid @RequestBody SaplingSelectionDTO newObjectDTO){
        try {
            return ResponseEntity.ok(service.update(newObjectDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
	}

}
