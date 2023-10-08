package dev.regis.rest.controllers.person;

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

import dev.regis.rest.models.person.dtos.ClientDTO;
import dev.regis.rest.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/client")
@Tag(name = "Clientes", description = "Endpoints para gerenciamento de Clientes")
public class ClientController {

    @Autowired
    ClientService service;

    @GetMapping
    @Operation(summary = "Retorna todas os clientes cadastrados", description = "Retorna uma lista com todos os clientes cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada")
        })
    public List<ClientDTO> listAll() {
        return service.listAll();
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca um cliente pelo ID", description = "Retorna o cliente com o ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Not found - Não foi possível encontrar o cliente")
    })
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            ClientDTO outputDTO = service.findById(id);
            return ResponseEntity.ok(outputDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Deleta um cliente pelo ID", description = "Deleta um cliente pelo ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comando recebido")
    })
    public ResponseEntity<Object> delete(@PathVariable Long id){
        try{
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/create")
    @Operation(summary = "Insere um novo cliente", description = "Insere um novo cliente com o nome especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente criado"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Os dados informados não foram aceitos")
    })
    public ResponseEntity<Object> create(@Valid @RequestBody ClientDTO newObjectDTO) {
        try {
            return ResponseEntity.ok(service.create(newObjectDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Atualiza um cliente", description = "Atualiza um cliente com o novo nome informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Os dados informados não foram aceitos")
    })
    public ResponseEntity<Object> update(@Valid @RequestBody ClientDTO newObjectDTO) {
        try {
            return ResponseEntity.ok(service.update(newObjectDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/search")
    @Operation(summary = "Busca clientes pelo nome", description = "Retorna uma lista de clientes conforme os parâmetros especificados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada")
    })
    public ResponseEntity<List<ClientDTO>> search(
            @RequestParam("name") String name,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "orderBy", defaultValue = "id", required = false) String orderBy,
            @RequestParam(value = "itensPerPage", defaultValue = "10", required = false) Integer itensPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction) {
        List<ClientDTO> listClientDTOs = service.search(name, page, orderBy, itensPerPage, direction);
        return ResponseEntity.ok(listClientDTOs);
    }
}
