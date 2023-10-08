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
import dev.regis.rest.services.BatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/batch")
@Tag(name = "Lotes", description = "Endpoints para gerenciamento de Lotes")
public class BatchController {

    @Autowired
    BatchService service;

    @GetMapping
    @Operation(summary = "Retorna todos os lotes cadastrados", description = "Retorna uma lista com todos os lotes existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada")
        })
    public List<BatchDTO> listAll(){
        return service.listAll();
    }
    
    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca um lote pelo ID", description = "Retorna um lote com o ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lote encontrado"),
            @ApiResponse(responseCode = "404", description = "Not found - Não foi possível encontrar o lote")
    })
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		try {
			BatchDTO outputDTO = service.findById(id);
			return ResponseEntity.ok(outputDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Deleta um lote pelo ID", description = "Deleta um lote pelo ID especificado")
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
    @Operation(summary = "Insere um novo lote", description = "Insere um novo lote com os dados especificados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lote criado"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Os dados informados não foram aceitos")
    })
	public ResponseEntity<Object> create(@Valid @RequestBody BatchDTO newObjectDTO) {
		try {
			return ResponseEntity.ok(service.create(newObjectDTO));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

    @PutMapping(value = "/update/{id}")
	@Operation(summary = "Atualiza um lote existente", description = "Atualiza um lote com os dados especificados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lote atualizado"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Os dados informados não foram aceitos")
    })
	public ResponseEntity<Object> update(@Valid @RequestBody BatchDTO newObjectDTO){
        try {
            return ResponseEntity.ok(service.update(newObjectDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
	}
}
