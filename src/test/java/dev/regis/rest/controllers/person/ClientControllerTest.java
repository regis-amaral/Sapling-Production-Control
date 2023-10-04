package dev.regis.rest.controllers.person;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import dev.regis.rest.models.person.dtos.ClientDTO;
import dev.regis.rest.services.ClientService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientControllerTest {

    @Autowired
    ClientController controller;

    @Autowired
    ClientService service;

    @Test
    public void listAll_ShouldReturnListOfClients() {
        // Arrange
        int expectedListSize = 4;

        // Act
        List<ClientDTO> clients = controller.listAll();

        // Assert
        assertEquals(expectedListSize, clients.size());
    }

    @Test
    public void findById_ShouldReturnResponseEntityWithStatusCode200AndObjectDTO() {
        // Arrange
        Long id = 1L;

        // Act
        ResponseEntity<Object> response = controller.findById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof ClientDTO);
    }

    @Test
    public void findById_ShouldReturnResponseEntityWithStatusCode404() {
        // Arrange
        Long id = -1L;

        // Act
        ResponseEntity<Object> response = controller.findById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void create_ShouldReturnResponseEntityWithStatusCode200AndCreatedObjectId() {
        // Arrange
        ClientDTO newClientDTO = new ClientDTO();
        newClientDTO.setName("Novo Cliente");

        // Act
        ResponseEntity<Object> response = controller.create(newClientDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof Long); // retorna o id do novo objeto
    }

    @Test
    public void create_ShouldReturnResponseEntityWithStatusCode400ForInvalidObjectDTO() {
        // Arrange
        ClientDTO invalidClientDTO = new ClientDTO();

        // Act
        ResponseEntity<Object> response = controller.create(invalidClientDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof String);
    }

    @Test
    public void update_ShouldReturnResponseEntityWithStatusCode200AndUpdatedObjectId() {
        try {
            // Arrange
            ClientDTO updatedClientDTO = service.findById(3L);
            updatedClientDTO.setName("Ciclano");

            // Act
            ResponseEntity<Object> response = controller.update(updatedClientDTO);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertTrue(response.getBody() instanceof Long);
            assertEquals(updatedClientDTO.getId(), response.getBody());
        } catch (Exception e) {
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    @Test
    public void update_ShouldReturnResponseEntityWithStatusCode400ForInvalidObjectDTO() {
        try {
            // Arrange
            ClientDTO updatedClientDTO = service.findById(3L);
            updatedClientDTO.setName("  ");

            // Act
            ResponseEntity<Object> response = controller.update(updatedClientDTO);

            // Assert
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            assertNotNull(response.getBody());
            assertTrue(response.getBody() instanceof String);
        } catch (Exception e) {
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    @Test
    public void delete_ShouldReturnResponseEntityWithStatusCode200() {
        // Arrange
        Long idToDelete = 4L; // usuário sem relacionamentos

        // Act
        ResponseEntity<Object> response = controller.delete(idToDelete);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody()); // Como está retornando ResponseEntity.ok().build(), o corpo deve ser null
    }

    @Test
    public void delete_ShouldReturnResponseEntityWithStatusCode200ForInvalidId() {
        // Arrange
        Long invalidId = 9999L; // Um ID que não existe

        // Act
        ResponseEntity<Object> response = controller.delete(invalidId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void delete_ShouldReturnResponseEntityWithStatusCode400ForNullId() {
        // Arrange
        Long invalidId = null;

        // Act
        ResponseEntity<Object> response = controller.delete(invalidId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody()); // Deve haver uma mensagem de erro no corpo
        assertTrue(response.getBody() instanceof String);
    }

    @Test
    public void search_ShouldReturnListOfClients() {
        // Arrange
        String name = "User";
        Integer page = 0;
        String orderBy = "name";
        Integer itemsPerPage = 10;
        String direction = "DESC";

        // Act
        ResponseEntity<List<ClientDTO>> response = controller.search(name, page, orderBy, itemsPerPage, direction);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof List);
        assertTrue(response.getBody().size() > 0);
    }

    @Test
    public void search_ShouldReturnEmptyListForUnknownName() {
        // Arrange
        String name = "Nonexistent Name";
        Integer page = 0;
        String orderBy = "name";
        Integer itemsPerPage = 10;
        String direction = "ASC";

        // Act
        ResponseEntity<List<ClientDTO>> response = controller.search(name, page, orderBy, itemsPerPage, direction);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof List);
        assertEquals(0, response.getBody().size()); // A lista deve estar vazia
    }

}
