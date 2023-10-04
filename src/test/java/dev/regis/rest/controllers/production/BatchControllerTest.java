package dev.regis.rest.controllers.production;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import dev.regis.rest.models.person.dtos.ClientDTO;
import dev.regis.rest.models.production.GeneticMaterial;
import dev.regis.rest.models.production.dtos.BatchDTO;
import dev.regis.rest.services.BatchService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BatchControllerTest {
    
    @Autowired
    BatchController controller;

    @Autowired
    BatchService service;

    @Test
    public void listAll_ShouldReturnListOfBatches() {
        // Arrange
        List<BatchDTO> batches = service.listAll();

        // Act
        List<BatchDTO> clients = controller.listAll();

        // Assert
        assertEquals(batches.size(), clients.size());
    }

    private BatchDTO getNewBatchDTO(int amount, String code, String stakingDate, Long materialGeneticId){
        BatchDTO batchDTO = new BatchDTO();
        batchDTO.setAmount(amount);
        batchDTO.setCode(code);
        batchDTO.setStakingDate(Date.valueOf(stakingDate));
        GeneticMaterial geneticMaterial = new GeneticMaterial();
        geneticMaterial.setId(materialGeneticId);
        batchDTO.setGeneticMaterial(geneticMaterial);

        return batchDTO;
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
        assertTrue(response.getBody() instanceof BatchDTO);
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
        BatchDTO newBatchDTO = this.getNewBatchDTO(1200, "100/2023", "2023-02-02", 2L);

        // Act
        ResponseEntity<Object> response = controller.create(newBatchDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof Long); // retorna o id do novo objeto
    }

    @Test
    public void create_ShouldReturnResponseEntityWithStatusCode400ForInvalidObjectDTO() {
        // Arrange
        BatchDTO invalidBatchDTO = new BatchDTO();

        // Act
        ResponseEntity<Object> response = controller.create(invalidBatchDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof String);
    }

    @Test
    public void update_ShouldReturnResponseEntityWithStatusCode200AndUpdatedObjectId() {
        try {
            // Arrange
            BatchDTO updatedBatchDTO = this.getNewBatchDTO(1300, "209/2023", "2023-02-15", 3L);
            updatedBatchDTO.setId(1L);

            // Act
            ResponseEntity<Object> response = controller.update(updatedBatchDTO);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertTrue(response.getBody() instanceof Long);
            assertEquals(updatedBatchDTO.getId(), response.getBody());
        } catch (Exception e) {
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    @Test
    public void update_ShouldReturnResponseEntityWithStatusCode400ForInvalidObjectDTO() {
        try {
            // Arrange
            BatchDTO batchDTO = service.findById(3L);
            batchDTO.setCode("  ");

            // Act
            ResponseEntity<Object> response = controller.update(batchDTO);

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
        Long idToDelete = 5L; // batch sem SaplingSelection

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
}
