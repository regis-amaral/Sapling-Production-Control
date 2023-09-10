package dev.regis.rest.controllers.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.regis.rest.controllers.AbstractController;
import dev.regis.rest.models.person.Client;
import dev.regis.rest.models.person.dto.ClientDTO;
import dev.regis.rest.services.ClientService;

@RestController
@RequestMapping(value = "/api/client")
public class ClientController extends AbstractController<Client, ClientDTO> {

  @Autowired
  ClientService service;

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
