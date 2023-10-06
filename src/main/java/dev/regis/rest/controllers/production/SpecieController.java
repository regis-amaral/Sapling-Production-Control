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
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/specie")
public class SpecieController {

  @Autowired
  SpecieService service;

  @GetMapping
    public List<SpecieDTO> listAll(){
        return service.listAll();
    }
    
    @GetMapping(value = "/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		try {
			SpecieDTO outputDTO = service.findById(id);
			return ResponseEntity.ok(outputDTO);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

    @DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		try{
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
	}

    @PostMapping(value = "/create")
	public ResponseEntity<Object> create(@Valid @RequestBody SpecieDTO newObjectDTO) {
		try {
			return ResponseEntity.ok(service.create(newObjectDTO));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

        @PutMapping(value = "/update")
	public ResponseEntity<Object> update(@Valid @RequestBody SpecieDTO newObjectDTO){
        try {
            return ResponseEntity.ok(service.update(newObjectDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
	}

  @GetMapping("/search")
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
