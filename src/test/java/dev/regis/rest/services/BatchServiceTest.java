package dev.regis.rest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import dev.regis.rest.models.production.Batch;
import dev.regis.rest.models.production.GeneticMaterial;
import dev.regis.rest.models.production.dtos.BatchDTO;
import dev.regis.rest.models.production.dtos.ExpeditionPlanDTO;
import dev.regis.rest.models.production.dtos.GeneticMaterialDTO;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class BatchServiceTest {
    
    @Autowired
    private BatchService service;

    @Test
    public void listAll_ShouldReturnListOfBatch() {
        // Arrange

        // Act
        List<BatchDTO> result = service.listAll();

        // Assert
        assertNotNull(result);
        assertNotEquals(0, result.size());
    }

    private BatchDTO getNewBatchDTO(){
        BatchDTO batchDTO = new BatchDTO();
        batchDTO.setAmount(1000);
        batchDTO.setCode("400/2023");
        batchDTO.setStakingDate(Date.valueOf("2023-09-30"));
        GeneticMaterial geneticMaterial = new GeneticMaterial();
        geneticMaterial.setId(1L);
        batchDTO.setGeneticMaterial(geneticMaterial);

        return batchDTO;
    }

    @Test
    public void findById_ShouldReturnExistentBatch(){
        // Arrange
        Long id = 1L;
        BatchDTO batchDTO = null;

        // Act
        try{
            batchDTO = service.findById(id);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertNotNull(batchDTO);
        assertEquals(id, batchDTO.getId());
    }

    @Test
    public void findById_ShouldThrowExceptionOnFindBatchWithNullId(){
        // Arrange
        Long id = null;

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.findById(id);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("ID inválido!", exception.getMessage()); 
    }

    @Test
    public void findById_ShouldThrowExceptionOnFindBatchWithIdLessThanOne(){
        // Arrange
        Long id = 0L;

        // Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            service.findById(id);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("ID inválido!", exception.getMessage()); 
    }

    @Test
    public void create_ShouldCreateBatch(){
        // Arrange
        BatchDTO batchDTO = this.getNewBatchDTO();
        
        // Act
        Long id = null;
        try{
            id = service.create(batchDTO);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertNotNull(id);
        assertTrue(id > 0);
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateBatchWithNullCode(){
        // Arrange
        BatchDTO batchDTO = this.getNewBatchDTO();
        batchDTO.setCode(null);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(batchDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Parâmetro código inválido", exception.getMessage()); 
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateBatchWithEmptyCode(){
        // Arrange
        BatchDTO batchDTO = this.getNewBatchDTO();
        batchDTO.setCode("    ");

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(batchDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Parâmetro código inválido", exception.getMessage()); 
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateBatchWithNullStakingDate(){
        // Arrange
        BatchDTO batchDTO = this.getNewBatchDTO();
        batchDTO.setStakingDate(null);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(batchDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("A data de estaquia não pode ser nula", exception.getMessage()); 
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateBatchWithStakingDateGreaterThanNow(){
        // Arrange
        BatchDTO batchDTO = this.getNewBatchDTO();

        // data atual mais 1 dia
        LocalDate nextDay = LocalDate.now().plusDays(1);

        // Converte de LocalDate para Date
        Date nextDate = java.sql.Date.valueOf(nextDay);

        batchDTO.setStakingDate(nextDate);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(batchDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("A data de estaquia não pode ser maior que a data atual", exception.getMessage()); 
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateBatchWithAmountLessThanOne(){
        // Arrange
        BatchDTO batchDTO = this.getNewBatchDTO();
        batchDTO.setAmount(0);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(batchDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("A quantidade deve ser um número maior que zero", exception.getMessage()); 

    }

    @Test
    public void create_ShouldThrowExceptionOnCreateBatchWithNullGeneticMaterial(){
        // Arrange
        BatchDTO batchDTO = this.getNewBatchDTO();
        batchDTO.setGeneticMaterial(null);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(batchDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Material genético inválido", exception.getMessage()); 

    }

    @Test
    public void create_ShouldThrowExceptionOnCreateBatchWithNullGeneticMaterialId(){
        // Arrange
        BatchDTO batchDTO = this.getNewBatchDTO();
        batchDTO.setGeneticMaterial(new GeneticMaterial());

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(batchDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Material genético inválido", exception.getMessage()); 

    }

    @Test
    public void create_ShouldThrowExceptionOnCreateBatchWithGeneticMaterialIdLessThanOne(){
        // Arrange
        BatchDTO batchDTO = this.getNewBatchDTO();
        GeneticMaterial geneticMaterial = new GeneticMaterial();
        geneticMaterial.setId(0L);
        batchDTO.setGeneticMaterial(geneticMaterial);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(batchDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Material genético inválido", exception.getMessage()); 
    }

    @Test
    public void delete_ShouldDeleteBatch(){
        // Arrange
        BatchDTO batchDTO = this.getNewBatchDTO();
        Long id = null;
        try{ 
            // Crio um objeto novo para poder deletar
            id = service.create(batchDTO);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }
        batchDTO.setId(id);

        // Act
        try{
            service.deleteById(batchDTO.getId());
        }catch(Exception e){
            fail("Falhou ao deletar um Lote existente.");
        }

        // Assert
        // Se o método chegou até aqui sem lançar uma exeção então ele foi bem sucedido
    }

    @Test
    public void delete_ShouldThrowExceptionOnDeleteNullBatchId(){
        // Arrange
        Long id = null;

        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            service.deleteById(id);
        });

        // Assert
        assertNotNull(exception);
    }

    @Test
    public void update_ShouldUpdateBatch(){
        // Arrange
        // Busca um lote existente
        BatchDTO oldBatchDTO = new BatchDTO();
        try{
            oldBatchDTO = service.findById(1L);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }
        // Cria um novo objeto com novos dados para o lote existente
        BatchDTO newBatchDTO = this.getNewBatchDTO();
        newBatchDTO.setId(oldBatchDTO.getId());
        newBatchDTO.setAmount(166);
        newBatchDTO.setCode("321/2024");
        GeneticMaterial geneticMaterial = new GeneticMaterial();
        geneticMaterial.setId(6L);
        newBatchDTO.setGeneticMaterial(geneticMaterial);
        newBatchDTO.setStakingDate(Date.valueOf("2023-10-2"));

        // Act
        Long returnedId = null;
        try{
            returnedId = service.update(newBatchDTO);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertEquals(returnedId, newBatchDTO.getId());

        // Arrange
        // verificação adicional dos dados persistidos em banco
        BatchDTO persistedBatchDTO = null;
        try{
            persistedBatchDTO = service.findById(oldBatchDTO.getId());
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertNotNull(persistedBatchDTO);
        assertEquals(newBatchDTO.getId(), persistedBatchDTO.getId());
        assertEquals(newBatchDTO.getAmount(), persistedBatchDTO.getAmount());
        assertEquals(newBatchDTO.getCode(), persistedBatchDTO.getCode());
        assertEquals(newBatchDTO.getGeneticMaterial().getId(), persistedBatchDTO.getGeneticMaterial().getId());
        assertEquals(newBatchDTO.getStakingDate(), persistedBatchDTO.getStakingDate());

    }

    @Test
    public void update_ShouldThrowExceptionOnUpdateBatchWithNullId(){
        // Arrange
        BatchDTO batchDTO = this.getNewBatchDTO();
        batchDTO.setId(null);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.update(batchDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("ID inválido!", exception.getMessage()); 
    }

    @Test
    public void update_ShouldThrowExceptionOnUpdateBatchWithIdLessThanOne(){
        // Arrange
        BatchDTO batchDTO = this.getNewBatchDTO();
        batchDTO.setId(0L);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.update(batchDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("ID inválido!", exception.getMessage()); 
    }
}
