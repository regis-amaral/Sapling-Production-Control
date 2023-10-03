package dev.regis.rest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import dev.regis.rest.models.production.Batch;
import dev.regis.rest.models.production.dtos.BatchDTO;
import dev.regis.rest.models.production.dtos.ExpeditionPlanDTO;
import dev.regis.rest.models.production.dtos.SaplingSelectionDTO;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class SaplingSelectionServiceTest {
    
    @Autowired
    private SaplingSelectionService service;

    @Test
    public void listAll_ShouldReturnListOfSaplingSelecteds() {
        // Arrange

        // Act
        List<SaplingSelectionDTO> result = service.listAll();

        // Assert
        assertNotNull(result);
        assertNotEquals(0, result.size());
    }

    private SaplingSelectionDTO getNewSaplingSelectionDTO(){

        SaplingSelectionDTO saplingSelectionDTO = new SaplingSelectionDTO();

        saplingSelectionDTO.setSelectionDate(Date.valueOf("2023-10-3"));

        saplingSelectionDTO.setTotalRootedSaplings(100);

        Batch batchA = new Batch();
        batchA.setId(3L);
        Batch batchB = new Batch();
        batchB.setId(4L);
        List<Batch> batchs = List.of(batchA, batchB);
        saplingSelectionDTO.setListBatchs(batchs);

        return saplingSelectionDTO;
    }

    @Test
    public void findById_ShouldReturnExistentSaplingSelection(){
        // Arrange
        Long id = 1L;
        SaplingSelectionDTO saplingSelectionDTO = null;

        // Act
        try{
            saplingSelectionDTO = service.findById(id);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertNotNull(saplingSelectionDTO);
        assertEquals(id, saplingSelectionDTO.getId());
    }

    @Test
    public void findById_ShouldThrowExceptionOnFindSaplingSelectionWithNullId(){
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
    public void findById_ShouldThrowExceptionOnFindSaplingSelectionWithIdLessThanOne(){
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
    public void create_ShouldCreateSaplingSelection(){
        // Arrange
        SaplingSelectionDTO saplingSelectionDTO = this.getNewSaplingSelectionDTO();
        
        // Act
        Long id = null;
        try{
            id = service.create(saplingSelectionDTO);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertNotNull(id);
        assertTrue(id > 0);
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateSaplingSelectionWithNullSlectionDate(){
        // Arrange
        SaplingSelectionDTO saplingSelectionDTO = this.getNewSaplingSelectionDTO();
        saplingSelectionDTO.setSelectionDate(null);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(saplingSelectionDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("A data de seleção não pode ser nula", exception.getMessage()); 
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateSaplingSelectionWithSelectionDateGreaterThanNow(){
        // Arrange
        SaplingSelectionDTO saplingSelectionDTO = this.getNewSaplingSelectionDTO();

        // data atual mais 1 dia
        LocalDate nextDay = LocalDate.now().plusDays(1);

        // Converte de LocalDate para Date
        Date nextDate = java.sql.Date.valueOf(nextDay);

        saplingSelectionDTO.setSelectionDate(nextDate);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(saplingSelectionDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("A data de seleção não pode ser maior que a data atual", exception.getMessage()); 
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateSaplingSelectionWithTotalRootedSaplingsLessThanOne(){
        // Arrange
        SaplingSelectionDTO saplingSelectionDTO = this.getNewSaplingSelectionDTO();
        saplingSelectionDTO.setTotalRootedSaplings(-1);
        
        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(saplingSelectionDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("O total de mudas selecionadas deve ser um número maior que zero", exception.getMessage());  
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateSaplingSelectionWithNullListBatchs(){
        // Arrange
        SaplingSelectionDTO saplingSelectionDTO = this.getNewSaplingSelectionDTO();
        saplingSelectionDTO.setListBatchs(null);
        
        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(saplingSelectionDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Deve ser selecionado ao menos um lote utilizado na seleção", exception.getMessage());  
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateSaplingSelectionWithEmptyListBatchs(){
        // Arrange
        SaplingSelectionDTO saplingSelectionDTO = this.getNewSaplingSelectionDTO();
        List<Batch> emptyList = new ArrayList<>();
        saplingSelectionDTO.setListBatchs(emptyList);
        
        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(saplingSelectionDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Deve ser selecionado ao menos um lote utilizado na seleção", exception.getMessage());  
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateSaplingSelectionWithExistentSaplingSelectionInOtherBatch(){
        // Arrange
        SaplingSelectionDTO saplingSelectionDTO = this.getNewSaplingSelectionDTO();
        List<Batch> batchs = saplingSelectionDTO.getListBatchs();
        batchs.forEach(batch -> batch.setId(null));
        saplingSelectionDTO.setListBatchs(batchs);
        
        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(saplingSelectionDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("ID não informado para o lote selecionado.", exception.getMessage());  
    }
}
