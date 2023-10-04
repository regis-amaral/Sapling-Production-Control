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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import dev.regis.rest.models.production.Batch;
import dev.regis.rest.models.production.dtos.BatchDTO;
import dev.regis.rest.models.production.dtos.SaplingSelectionDTO;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class SaplingSelectionServiceTest {
    
    @Autowired
    private SaplingSelectionService service;

    @Autowired
    private BatchService batchService;

    @Autowired
    ModelMapper mapper;

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
        List<Batch> batches = List.of(batchA, batchB);
        saplingSelectionDTO.setListBatchs(batches);

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
    public void create_ShouldThrowExceptionOnCreateSaplingSelectionWithNullBatchId(){
        // Arrange
        SaplingSelectionDTO saplingSelectionDTO = this.getNewSaplingSelectionDTO();
        saplingSelectionDTO.getListBatchs().get(0).setId(null);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(saplingSelectionDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("ID não informado para o lote selecionado.", exception.getMessage());  
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateSaplingSelectionWithExistentSaplingSelectionInOtherBatch(){
        // Arrange
        SaplingSelectionDTO saplingSelectionDTO = this.getNewSaplingSelectionDTO();

        BatchDTO batchA = null;
        try{
            batchA = batchService.findById(1L);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }
        

        List<Batch> batchs = List.of(mapper.map(batchA, Batch.class));
        saplingSelectionDTO.setListBatchs(batchs);
        
        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(saplingSelectionDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Já foi cadastrada uma seleção para o lote " + batchA.getCode(), exception.getMessage());  
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateSaplingSelectionWithNonexistentBatch(){
        // Arrange
        SaplingSelectionDTO saplingSelectionDTO = this.getNewSaplingSelectionDTO();
        saplingSelectionDTO.getListBatchs().get(0).setId(1000L);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(saplingSelectionDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Não existe o lote com o ID informado.", exception.getMessage());  
    }

    @Test
    public void delete_ShouldDeleteSaplingSelection(){
        // Arrange
        Long id = 5L;
       
        // Act
        try{
            service.deleteById(id);
        }catch(Exception e){
            fail("Falhou ao deletar uma Seleção de Mudas existente.");
        }
        // Assert
        // Se o método chegou até aqui sem lançar uma exceção então ele foi bem sucedido
    }

    @Test
    public void update_ShouldUpdateSaplingSelection(){
        // Arrange
        // Busca um SaplingSelection existente
        SaplingSelectionDTO oldSaplingSelectionDTO = new SaplingSelectionDTO();
        try{
            oldSaplingSelectionDTO = service.findById(1L);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }
        // Cria um novo objeto com novos dados para a seleção de mudas existente
        SaplingSelectionDTO newSaplingSelectionDTO = new SaplingSelectionDTO();
        newSaplingSelectionDTO.setId(oldSaplingSelectionDTO.getId());
        newSaplingSelectionDTO.setSelectionDate(Date.valueOf("2023-10-4"));
        newSaplingSelectionDTO.setTotalRootedSaplings(100);
        // O Sapling Selection migrado possui os lotes 1 e 2, logo trocarei para o 3 e 4
        Batch batchA = new Batch(); 
        batchA.setId(3L);
        Batch batchB = new Batch();
        batchB.setId(4L);
        List<Batch> batchs = List.of(batchA, batchB);
        newSaplingSelectionDTO.setListBatchs(batchs);

        // Act
        Long returnedId = null;
        try{
            returnedId = service.update(newSaplingSelectionDTO);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertEquals(returnedId, newSaplingSelectionDTO.getId());

        // Arrange
        // verificação adicional dos dados persistidos em banco
        SaplingSelectionDTO persistedSaplingSelectionDTO = null;

        // Act
        try{
            persistedSaplingSelectionDTO = service.findById(oldSaplingSelectionDTO.getId());
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertNotNull(persistedSaplingSelectionDTO);
        assertEquals(newSaplingSelectionDTO.getId(), persistedSaplingSelectionDTO.getId());
        assertEquals(newSaplingSelectionDTO.getListBatchs().size(), persistedSaplingSelectionDTO.getListBatchs().size());
        assertNotEquals(oldSaplingSelectionDTO.getListBatchs().get(0).getId(), persistedSaplingSelectionDTO.getListBatchs().get(0).getId());
        assertEquals(newSaplingSelectionDTO.getSelectionDate(), persistedSaplingSelectionDTO.getSelectionDate());
        assertEquals(newSaplingSelectionDTO.getTotalRootedSaplings(), persistedSaplingSelectionDTO.getTotalRootedSaplings());
    }

    @Test
    public void update_ShouldThrowExceptionOnUpdateSaplingSelectionWithNullId(){
        // Arrange
        SaplingSelectionDTO saplingSelection = this.getNewSaplingSelectionDTO();
        saplingSelection.setId(null);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.update(saplingSelection);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("ID inválido!", exception.getMessage());
    }

    @Test
    public void update_ShouldThrowExceptionOnUpdateSaplingSelectionWithIdLessThanOne(){
        // Arrange
        SaplingSelectionDTO saplingSelection = this.getNewSaplingSelectionDTO();
        saplingSelection.setId(0L);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.update(saplingSelection);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("ID inválido!", exception.getMessage());
    }

    @Test
    public void update_ShouldThrowExceptionOnUpdateSaplingSelectionWithNullBatchId(){
        try{
            // Arrange
            // Busca um SaplingSelection existente
            SaplingSelectionDTO saplingSelectionDTO = service.findById(1L);
            
            if(saplingSelectionDTO.getListBatchs().size() == 0){
                fail("Não foi possível recuperar os lotes para o teste");
            }
            saplingSelectionDTO.getListBatchs().get(0).setId(null);

            // Act
            Throwable exception = assertThrows(Exception.class, () -> {
                service.update(saplingSelectionDTO);
            });

            // Assert
            assertNotNull(exception);
            assertEquals("ID não informado para o lote selecionado.", exception.getMessage());  
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    @Test
    public void update_ShouldThrowExceptionOnUpdateSaplingSelectionWithExistentSaplingSelectionInOtherBatch(){
        try{

            // Arrange

            // Busca um SaplingSelection existente
            SaplingSelectionDTO saplingSelectionDTO = service.findById(1L);
            // Crio outro SaplingSelection com um lote ainda não selecionado
            SaplingSelectionDTO newSaplingSelectionDTO = this.getNewSaplingSelectionDTO();
            BatchDTO batchDto = batchService.findById(4L);
            Batch batch = mapper.map(batchDto, Batch.class);
            List<Batch> batchs = List.of(batch);
            newSaplingSelectionDTO.setListBatchs(batchs);
            service.create(newSaplingSelectionDTO);
            // Atualizo o SaplingSelection atual com o mesmo lote utilizado no objeto newSaplingSelectionDTO
            saplingSelectionDTO.setListBatchs(batchs);
            
            // Act
            Throwable exception = assertThrows(Exception.class, () -> {
                service.update(saplingSelectionDTO);
            });

            // Assert
            assertNotNull(exception);
            assertEquals("Já foi cadastrada uma seleção para o lote " + batch.getCode(), exception.getMessage());  

        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    @Test
    public void update_ShouldThrowExceptionOnUpdateSaplingSelectionWithNonexistentBatch(){
        try{
            // Arrange
            // Busca um SaplingSelection existente
            SaplingSelectionDTO saplingSelectionDTO = service.findById(1L);
            saplingSelectionDTO.getListBatchs().get(0).setId(1000L);

            // Act
            Throwable exception = assertThrows(Exception.class, () -> {
                service.update(saplingSelectionDTO);
            });

            // Assert
            assertNotNull(exception);
            assertEquals("Não existe o lote com o ID informado.", exception.getMessage());  
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    @Test
    public void update_ShouldThrowExceptionOnUpdateSaplingSelectionWithNonexistentId(){
        try{
            // Arrange
            // Busca um SaplingSelection existente
            SaplingSelectionDTO saplingSelectionDTO = service.findById(1L);
            saplingSelectionDTO.setId(1000L);

            // Act
            Throwable exception = assertThrows(Exception.class, () -> {
                service.update(saplingSelectionDTO);
            });

            // Assert
            assertNotNull(exception);
            assertEquals("Não foi encontrada uma seleção com o ID informado.", exception.getMessage());  
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }
}
