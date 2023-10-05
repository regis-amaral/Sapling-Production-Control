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

import dev.regis.rest.models.person.dtos.ClientDTO;
import dev.regis.rest.models.production.Batch;
import dev.regis.rest.models.production.Specie;
import dev.regis.rest.models.production.dtos.GeneticMaterialDTO;
import dev.regis.rest.models.production.dtos.SaplingSelectionDTO;
import dev.regis.rest.services.GeneticMaterialService;
import dev.regis.rest.services.SaplingSelectionService;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class GeneticMaterialControllerTest {

    @Autowired
    GeneticMaterialController controller;


    @Test
    public void listAll_ShouldReturnListOfBatches() {
        // Arrange

        // Act
        List<GeneticMaterialDTO> objects = controller.listAll();

        // Assert
        assertTrue(objects.size() > 0);
    }

     private GeneticMaterialDTO getNewGeneticMaterialDTO(String name, Specie specie){

        GeneticMaterialDTO geneticMaterialDTO = new GeneticMaterialDTO();

        geneticMaterialDTO.setName(name);

        geneticMaterialDTO.setSpecie(specie);

        return geneticMaterialDTO;
    }

    @Test
    public void findById_ShouldReturnResponseEntityWithStatusCode200AndObjectDTO() {
        // Arrange
        Long id = 5L;

        // Act
        ResponseEntity<Object> response = controller.findById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof GeneticMaterialDTO);
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
        Specie specie = new Specie();
        specie.setId(1L);
        GeneticMaterialDTO geneticMaterialDTO = this.getNewGeneticMaterialDTO("XYZ", specie);

        // Act
        ResponseEntity<Object> response = controller.create(geneticMaterialDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof Long); // retorna o id do novo objeto
    }

    @Test
    public void create_ShouldReturnResponseEntityWithStatusCode400ForInvalidObjectDTO() {
        // Arrange
        Specie specie = new Specie();
        GeneticMaterialDTO invalidGeneticMaterialDTO = this.getNewGeneticMaterialDTO("XYZ", specie);

        // Act
        ResponseEntity<Object> response = controller.create(invalidGeneticMaterialDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof String);
    }

    @Test
    public void update_ShouldReturnResponseEntityWithStatusCode200AndUpdatedObjectId() {
        try {
            // Arrange
            // Busca um material genético existente
            Specie specie = new Specie();
            specie.setId(3L);
            GeneticMaterialDTO newGeneticMaterialDTO = this.getNewGeneticMaterialDTO("ABC", specie);
            newGeneticMaterialDTO.setId(1L);
            
            // Act
            ResponseEntity<Object> response = controller.update(newGeneticMaterialDTO);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertTrue(response.getBody() instanceof Long);
            assertEquals(newGeneticMaterialDTO.getId(), response.getBody());
        } catch (Exception e) {
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    @Test
    public void update_ShouldReturnResponseEntityWithStatusCode400ForInvalidObjectDTO() {
        try {
            // Arrange
            GeneticMaterialDTO invalidGeneticMaterialDTO = this.getNewGeneticMaterialDTO("ABC", new Specie());
            invalidGeneticMaterialDTO.setId(1L);

            // Act
            ResponseEntity<Object> response = controller.update(invalidGeneticMaterialDTO);

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
        Long idToDelete = 12L; // GeneticMaterial que nao pertence a nenhum lote

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
        String name = "k";
        Integer page = 0;
        String orderBy = "name";
        Integer itemsPerPage = 10;
        String direction = "DESC";

        // Act
        ResponseEntity<List<GeneticMaterialDTO>> response = controller.search(name, page, orderBy, itemsPerPage, direction);

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
        ResponseEntity<List<GeneticMaterialDTO>> response = controller.search(name, page, orderBy, itemsPerPage, direction);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof List);

    }

}
