package dev.regis.rest.controllers.production;

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
import org.springframework.transaction.annotation.Transactional;

import dev.regis.rest.models.production.Specie;
import dev.regis.rest.models.production.dtos.GeneticMaterialDTO;
import dev.regis.rest.models.production.dtos.SpecieDTO;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class SpecieControllerTest {
    
    @Autowired
    SpecieController controller;

    @Test
    public void listAll_ShouldReturnListOfBatches() {
        // Arrange

        // Act
        List<SpecieDTO> objects = controller.listAll();

        // Assert
        assertTrue(objects.size() > 0);
    }

    private SpecieDTO getNewSpecieDTO(String name){
        SpecieDTO specieDTO = new SpecieDTO();
        specieDTO.setName(name);
        return specieDTO;
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
        assertTrue(response.getBody() instanceof SpecieDTO);
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
        SpecieDTO specieDTO = this.getNewSpecieDTO("XYZ");

        // Act
        ResponseEntity<Object> response = controller.create(specieDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof Long); // retorna o id do novo objeto
    }

    @Test
    public void create_ShouldReturnResponseEntityWithStatusCode400ForInvalidObjectDTO() {
        // Arrange
        SpecieDTO invalidSpecieDTO = this.getNewSpecieDTO("    ");

        // Act
        ResponseEntity<Object> response = controller.create(invalidSpecieDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof String);
    }

    @Test
    public void update_ShouldReturnResponseEntityWithStatusCode200AndUpdatedObjectId() {
        try {
            // Arrange
            SpecieDTO specieDTO = this.getNewSpecieDTO("XYZ");
            specieDTO.setId(1L);
            
            // Act
            ResponseEntity<Object> response = controller.update(specieDTO);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertTrue(response.getBody() instanceof Long);
            assertEquals(specieDTO.getId(), response.getBody());
        } catch (Exception e) {
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    @Test
    public void update_ShouldReturnResponseEntityWithStatusCode400ForInvalidObjectDTO() {
        try {
            // Arrange
            SpecieDTO invalidSpecieDTO = this.getNewSpecieDTO("   ");
            invalidSpecieDTO.setId(1L);

            // Act
            ResponseEntity<Object> response = controller.update(invalidSpecieDTO);

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
        Long idToDelete = 5L; // Specie que nao pertence a nenhum GeneticMaterial

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
        String name = "E.";
        Integer page = 0;
        String orderBy = "name";
        Integer itemsPerPage = 10;
        String direction = "DESC";

        // Act
        ResponseEntity<List<SpecieDTO>> response = controller.search(name, page, orderBy, itemsPerPage, direction);

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
        ResponseEntity<List<SpecieDTO>> response = controller.search(name, page, orderBy, itemsPerPage, direction);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof List);

    }

}
