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

import dev.regis.rest.models.production.dtos.SpecieDTO;
import dev.regis.rest.services.SpecieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/specie")
@Tag(name = "Espécies", description = "Endpoints para gerenciamento de Espécies")
public class SpecieController {

    @Autowired
    SpecieService service;

    @GetMapping
    @Operation(summary = "Retorna todas as espécies cadastradas", description = "Retorna uma lista com todas as espécies cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada")
        })
    public List<SpecieDTO> listAll() {
        return service.listAll();
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca uma espécie pelo ID", description = "Retorna a espécie com o ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Espécie encontrada"),
            @ApiResponse(responseCode = "404", description = "Not found - Não foi possível encontrar a espécie")
    })
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            SpecieDTO outputDTO = service.findById(id);
            return ResponseEntity.ok(outputDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Deleta uma espécie pelo ID", description = "Deleta uma espécie pelo ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comando recebido")
    })
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/create")
    @Operation(summary = "Insere uma nova espécie", description = "Insere uma nova espécie com o nome especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Espécie criada"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Os dados informados não foram aceitos")
    })
    public ResponseEntity<Object> create(@Valid @RequestBody SpecieDTO newObjectDTO) {
        try {
            return ResponseEntity.ok(service.create(newObjectDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Atualiza uma espécie", description = "Atualiza uma espécie com o novo nome informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Espécie atualizada"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Os dados informados não foram aceitos")
    })
    public ResponseEntity<Object> update(@Valid @RequestBody SpecieDTO newObjectDTO) {
        try {
            return ResponseEntity.ok(service.update(newObjectDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/search")
    @Operation(summary = "Busca espécies pelo nome", description = "Retorna uma lista de espécies conforme os parâmetros especificados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada")
    })
    public ResponseEntity<List<SpecieDTO>> search(
            @RequestParam("name") String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "orderBy", defaultValue = "id", required = false) String orderBy,
            @RequestParam(value = "itensPerPage", defaultValue = "10", required = false) Integer itensPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction) {
        List<SpecieDTO> specieDTOs = service.search(name, page, orderBy, itensPerPage, direction);
        return ResponseEntity.ok(specieDTOs);
    }
}
