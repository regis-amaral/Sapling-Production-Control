package dev.regis.rest.controllers.production;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Month;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import dev.regis.rest.models.person.Client;
import dev.regis.rest.models.production.GeneticMaterial;
import dev.regis.rest.models.production.dtos.ExpeditionPlanDTO;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ExpeditionPlanControllerTest {
 
    @Autowired
    ExpeditionPlanController controller;

    @Test
    public void listAll_ShouldReturnListOfBatches() {
        // Arrange

        // Act
        List<ExpeditionPlanDTO> objects = controller.listAll();

        // Assert
        assertTrue(objects.size() > 0);
    }

    private ExpeditionPlanDTO getNewExpeditionPlanDTO(
                                            int planned, 
                                            int realized, 
                                            Month month, 
                                            GeneticMaterial geneticMaterial,
                                            Client client){
        ExpeditionPlanDTO expeditionPlanDTO = new ExpeditionPlanDTO();

        expeditionPlanDTO.setPlanned(planned);
        expeditionPlanDTO.setRealized(realized);
        expeditionPlanDTO.setMonth(month);

        expeditionPlanDTO.setGeneticMaterial(geneticMaterial);

        expeditionPlanDTO.setClient(client);

        return expeditionPlanDTO;
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
        assertTrue(response.getBody() instanceof ExpeditionPlanDTO);
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
        GeneticMaterial geneticMaterial = new GeneticMaterial();
        geneticMaterial.setId(1L);
        Client client = new Client();
        client.setId(1L);
        ExpeditionPlanDTO expeditionPlanDTO = this.getNewExpeditionPlanDTO(100, 0, Month.MAY, geneticMaterial, client);

        // Act
        ResponseEntity<Object> response = controller.create(expeditionPlanDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof Long); // retorna o id do novo objeto
    }

    @Test
    public void create_ShouldReturnResponseEntityWithStatusCode400ForInvalidObjectDTO() {
        // Arrange
        GeneticMaterial geneticMaterial = new GeneticMaterial();
        Client client = new Client();
        client.setId(1L);
        ExpeditionPlanDTO invalidExpeditionPlanDTO = this.getNewExpeditionPlanDTO(100, 0, Month.MAY, geneticMaterial, client);

        // Act
        ResponseEntity<Object> response = controller.create(invalidExpeditionPlanDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof String);
    }

    @Test
    public void update_ShouldReturnResponseEntityWithStatusCode200AndUpdatedObjectId() {
        try {
            // Arrange
            GeneticMaterial geneticMaterial = new GeneticMaterial();
            geneticMaterial.setId(1L);
            Client client = new Client();
            client.setId(1L);
            ExpeditionPlanDTO expeditionPlanDTO = this.getNewExpeditionPlanDTO(100, 0, Month.MAY, geneticMaterial, client);
            expeditionPlanDTO.setId(1L);
            
            // Act
            ResponseEntity<Object> response = controller.update(expeditionPlanDTO);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertTrue(response.getBody() instanceof Long);
            assertEquals(expeditionPlanDTO.getId(), response.getBody());
        } catch (Exception e) {
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    @Test
    public void update_ShouldReturnResponseEntityWithStatusCode400ForInvalidObjectDTO() {
        try {
            // Arrange
            GeneticMaterial geneticMaterial = new GeneticMaterial();
            Client client = new Client();
            ExpeditionPlanDTO invalidExpeditionPlanDTO = this.getNewExpeditionPlanDTO(100, 0, Month.MAY, geneticMaterial, client);
            invalidExpeditionPlanDTO.setId(1L);

            // Act
            ResponseEntity<Object> response = controller.update(invalidExpeditionPlanDTO);

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
        Long idToDelete = 5L;

        // Act
        ResponseEntity<Object> response = controller.delete(idToDelete);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
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

}
