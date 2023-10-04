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
import org.springframework.transaction.annotation.Transactional;

import dev.regis.rest.models.production.Batch;
import dev.regis.rest.models.production.dtos.BatchDTO;
import dev.regis.rest.models.production.dtos.SaplingSelectionDTO;
import dev.regis.rest.services.SaplingSelectionService;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class SaplingSelectionControllerTest {
    
    @Autowired
    SaplingSelectionController controller;

    @Autowired
    SaplingSelectionService service;

    @Test
    public void listAll_ShouldReturnListOfBatches() {
        // Arrange

        // Act
        List<SaplingSelectionDTO> objects = controller.listAll();

        // Assert
        assertTrue(objects.size() > 0);
    }

    private SaplingSelectionDTO getNewSaplingSelectionDTO(String selectionDate, int totalRootedSaplings, List <Batch> batches){

        SaplingSelectionDTO saplingSelectionDTO = new SaplingSelectionDTO();

        saplingSelectionDTO.setSelectionDate(Date.valueOf(selectionDate));

        saplingSelectionDTO.setTotalRootedSaplings(totalRootedSaplings);

        saplingSelectionDTO.setListBatchs(batches);

        return saplingSelectionDTO;
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
        assertTrue(response.getBody() instanceof SaplingSelectionDTO);
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
        Batch batchA = new Batch();
        batchA.setId(3L);
        Batch batchB = new Batch();
        batchB.setId(4L);
        List<Batch> batches = List.of(batchA, batchB);
        SaplingSelectionDTO newSaplingSelectionDTO = this.getNewSaplingSelectionDTO("2023-10-03", 100, batches);

        // Act
        ResponseEntity<Object> response = controller.create(newSaplingSelectionDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof Long); // retorna o id do novo objeto
    }

    @Test
    public void create_ShouldReturnResponseEntityWithStatusCode400ForInvalidObjectDTO() {
        // Arrange
        SaplingSelectionDTO invalidSaplingSelectionDTO = new SaplingSelectionDTO();

        // Act
        ResponseEntity<Object> response = controller.create(invalidSaplingSelectionDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof String);
    }

    @Test
    public void update_ShouldReturnResponseEntityWithStatusCode200AndUpdatedObjectId() {
        try {
            // Arrange
            Batch batchA = new Batch();
            batchA.setId(3L);
            Batch batchB = new Batch();
            batchB.setId(4L);
            List<Batch> batches = List.of(batchA, batchB);
            SaplingSelectionDTO saplingSelectionDTO = this.getNewSaplingSelectionDTO("2023-10-03", 100, batches);
            saplingSelectionDTO.setId(1L);

            // Act
            ResponseEntity<Object> response = controller.update(saplingSelectionDTO);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertTrue(response.getBody() instanceof Long);
            assertEquals(saplingSelectionDTO.getId(), response.getBody());
        } catch (Exception e) {
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    @Test
    public void update_ShouldReturnResponseEntityWithStatusCode400ForInvalidObjectDTO() {
        try {
            // Arrange
            SaplingSelectionDTO saplingSelectionDTO = service.findById(3L);
            saplingSelectionDTO.setListBatchs(null);

            // Act
            ResponseEntity<Object> response = controller.update(saplingSelectionDTO);

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
        Long idToDelete = 5L; // SaplingSelection sem relacionamentos

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
