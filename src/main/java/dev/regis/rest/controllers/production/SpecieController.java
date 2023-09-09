package dev.regis.rest.controllers.production;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.regis.rest.controllers.AbstractController;
import dev.regis.rest.models.production.Specie;
import dev.regis.rest.models.production.dtos.SpecieDTO;
import dev.regis.rest.services.SpecieService;

@RestController
@RequestMapping(value = "/api/specie")
public class SpecieController extends AbstractController <Specie, SpecieDTO> {

  @Autowired
  SpecieService service;

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
