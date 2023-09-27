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
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/client")
public class ClientController {

  @Autowired
  ClientService service;

  @GetMapping
    public List<ClientDTO> listAll(){
        return service.listAll();
    }
    
    @GetMapping(value = "/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		try {
			ClientDTO outputDTO = service.findById(id);
			return ResponseEntity.ok(outputDTO);
		} catch (Exception e) {
			// TODO tratar exceções
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

    @DeleteMapping(value = "/delete/{id}")
	public void delete(@PathVariable Long id) throws Exception {
		service.deleteById(id);
	}

    @PostMapping(value = "/create")
	public ResponseEntity<Object> create(@Valid @RequestBody ClientDTO newObjectDTO) {
		try {
			return ResponseEntity.ok(service.create(newObjectDTO));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

    @PutMapping(value = "/update")
	public ResponseEntity<Object> update(@Valid @RequestBody ClientDTO newObjectDTO){
        try {
            return ResponseEntity.ok(service.update(newObjectDTO));
        } catch (Exception e) {
			e.printStackTrace(); // TODO tratar exeções
            return ResponseEntity.badRequest().body(e.getMessage());
        }
	}
  @GetMapping("/search")
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
