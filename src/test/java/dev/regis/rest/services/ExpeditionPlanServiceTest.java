package dev.regis.rest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Month;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import dev.regis.rest.models.person.Client;
import dev.regis.rest.models.production.GeneticMaterial;
import dev.regis.rest.models.production.Specie;
import dev.regis.rest.models.production.dtos.ExpeditionPlanDTO;
import dev.regis.rest.models.production.dtos.GeneticMaterialDTO;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ExpeditionPlanServiceTest {
    
    @Autowired
    private ExpeditionPlanService service;

    @Test
    public void listAll_ShouldReturnListOfExpeditionPlans() {
        // Arrange

        // Act
        List<ExpeditionPlanDTO> result = service.listAll();

        // Assert
        assertNotNull(result);
        assertNotEquals(0, result.size());
    }

    private ExpeditionPlanDTO getNewExpeditionPlanDTO(){
        ExpeditionPlanDTO expeditionPlanDTO = new ExpeditionPlanDTO();

        expeditionPlanDTO.setPlanned(123);
        expeditionPlanDTO.setRealized(0);
        expeditionPlanDTO.setMonth(Month.MAY);

        GeneticMaterial geneticMaterial = new GeneticMaterial();
        geneticMaterial.setId(1L);
        expeditionPlanDTO.setGeneticMaterial(geneticMaterial);

        Client client = new Client();
        client.setId(1L);
        expeditionPlanDTO.setClient(client);

        return expeditionPlanDTO;
    }

    @Test
    public void findById_ShouldReturnExistentExpeditionPlan(){
        // Arrange
        Long id = 1L;
        ExpeditionPlanDTO expeditionPlanDTO = null;

        // Act
        try{
            expeditionPlanDTO = service.findById(id);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertNotNull(expeditionPlanDTO);
        assertEquals(id, expeditionPlanDTO.getId());
    }

    @Test
    public void findById_ShouldThrowExceptionOnFindExpeditionPlanWithNullId(){
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
    public void findById_ShouldThrowExceptionOnFindExpeditionPlanWithIdLessThanOne(){
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
    public void create_ShouldCreateExpeditionPlan(){
        // Arrange
        ExpeditionPlanDTO expeditionPlanDTO = this.getNewExpeditionPlanDTO();
        
        // Act
        Long id = null;
        try{
            id = service.create(expeditionPlanDTO);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertNotNull(id);
        assertTrue(id > 0);
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateExpeditionPlanWithPlanedLessThanOne(){
        // Arrange
        ExpeditionPlanDTO expeditionPlanDTO = this.getNewExpeditionPlanDTO();
        expeditionPlanDTO.setPlanned(0);
        
        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(expeditionPlanDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("A quantidade planejada é inválida", exception.getMessage());  
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateExpeditionPlanWithRealizedLessThanZero(){
        // Arrange
        ExpeditionPlanDTO expeditionPlanDTO = this.getNewExpeditionPlanDTO();
        expeditionPlanDTO.setRealized(-1);
        
        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(expeditionPlanDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("A quantidade realizada é inválida", exception.getMessage());  
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateExpeditionPlanWithNullMonth(){
        // Arrange
        ExpeditionPlanDTO expeditionPlanDTO = this.getNewExpeditionPlanDTO();
        expeditionPlanDTO.setMonth(null);
        
        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(expeditionPlanDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Mês de expedição inválido", exception.getMessage());  
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateExpeditionPlanWithNullClient(){
        // Arrange
        ExpeditionPlanDTO expeditionPlanDTO = this.getNewExpeditionPlanDTO();
        expeditionPlanDTO.setClient(null);
        
        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(expeditionPlanDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Cliente não informado", exception.getMessage());  
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateExpeditionPlanWithNullClientId(){
        // Arrange
        ExpeditionPlanDTO expeditionPlanDTO = this.getNewExpeditionPlanDTO();
        expeditionPlanDTO.setClient(new Client());
        
        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(expeditionPlanDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Cliente não informado", exception.getMessage());  
    }

     @Test
    public void create_ShouldThrowExceptionOnCreateExpeditionPlanWithNonexistentClient(){
        // Arrange
        ExpeditionPlanDTO expeditionPlanDTO = this.getNewExpeditionPlanDTO();
        Client client = new Client();
        client.setId(1000L);
        expeditionPlanDTO.setClient(client);
        
        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(expeditionPlanDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Dados informados violam restrições no BD.", exception.getMessage());  
    }
    
    @Test
    public void create_ShouldThrowExceptionOnCreateExpeditionPlanWithNullGeneticMaterial(){
        // Arrange
        ExpeditionPlanDTO expeditionPlanDTO = this.getNewExpeditionPlanDTO();
        expeditionPlanDTO.setGeneticMaterial(null);
        
        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(expeditionPlanDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Material genético não informado", exception.getMessage());  
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateExpeditionPlanWithNullGeneticMaterialId(){
        // Arrange
        ExpeditionPlanDTO expeditionPlanDTO = this.getNewExpeditionPlanDTO();
        expeditionPlanDTO.setGeneticMaterial(new GeneticMaterial());
        
        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(expeditionPlanDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Material genético não informado", exception.getMessage());  
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateExpeditionPlanWithNonexistentGeneticMaterial(){
        // Arrange
        ExpeditionPlanDTO expeditionPlanDTO = this.getNewExpeditionPlanDTO();
        GeneticMaterial geneticMaterial = new GeneticMaterial();
        geneticMaterial.setId(1000L);
        expeditionPlanDTO.setGeneticMaterial(geneticMaterial);
        
        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(expeditionPlanDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Dados informados violam restrições no BD.", exception.getMessage());  
    }

    @Test
    public void delete_ShouldDeleteExpeditionPlan(){
        // Arrange
        ExpeditionPlanDTO expeditionPlanDTO = this.getNewExpeditionPlanDTO();
        Long id = null;
        try{ 
            // Crio um objeto novo para poder deletar
            id = service.create(expeditionPlanDTO);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }
        expeditionPlanDTO.setId(id);

        // Act
        try{
            service.deleteById(expeditionPlanDTO.getId());
        }catch(Exception e){
            fail("Falhou ao deletar um Plano de Expedição existente.");
        }

        // Assert
        // Se o método chegou até aqui sem lançar uma exeção então ele foi bem sucedido
    }

    @Test
    public void delete_ShouldThrowExceptionOnDeleteNullExpeditionPlanId(){
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
    public void update_ShouldUpdateExpeditionPlan(){
        // Arrange
        // Busca um plano de expedição existente
        ExpeditionPlanDTO oldExpeditionPlanDTO = new ExpeditionPlanDTO();
        try{
            oldExpeditionPlanDTO = service.findById(1L);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }
        // Cria um novo objeto com novos dados para o plano de expedição existente
        ExpeditionPlanDTO newExpeditionPlanDTO = this.getNewExpeditionPlanDTO();
        newExpeditionPlanDTO.setId(oldExpeditionPlanDTO.getId());
        newExpeditionPlanDTO.setPlanned(123);
        newExpeditionPlanDTO.setRealized(0);
        newExpeditionPlanDTO.setMonth(Month.MAY);

        GeneticMaterial geneticMaterial = new GeneticMaterial();
        geneticMaterial.setId(3L);
        newExpeditionPlanDTO.setGeneticMaterial(geneticMaterial);

        Client client = new Client();
        client.setId(2L);
        newExpeditionPlanDTO.setClient(client);

        // Act
        Long returnedId = null;
        try{
            returnedId = service.update(newExpeditionPlanDTO);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertEquals(returnedId, newExpeditionPlanDTO.getId());


        // Arrange
        // verificação adicional dos dados persistidos em banco
        ExpeditionPlanDTO persistedExpeditionPlanDTO = null;
        try{
            persistedExpeditionPlanDTO = service.findById(oldExpeditionPlanDTO.getId());
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertNotNull(persistedExpeditionPlanDTO);
        assertEquals(newExpeditionPlanDTO.getId(), persistedExpeditionPlanDTO.getId());
        assertEquals(newExpeditionPlanDTO.getPlanned(), persistedExpeditionPlanDTO.getPlanned());
        assertEquals(newExpeditionPlanDTO.getRealized(), persistedExpeditionPlanDTO.getRealized());
        assertEquals(newExpeditionPlanDTO.getMonth(), persistedExpeditionPlanDTO.getMonth());
        assertEquals(newExpeditionPlanDTO.getGeneticMaterial().getId(), persistedExpeditionPlanDTO.getGeneticMaterial().getId());
        assertEquals(newExpeditionPlanDTO.getClient().getId(), persistedExpeditionPlanDTO.getClient().getId());
    }

    @Test
    public void update_ShouldThrowExceptionOnUpdateExpeditionPlanWithNullId(){
        // Arrange
        ExpeditionPlanDTO newExpeditionPlanDTO = this.getNewExpeditionPlanDTO();
        newExpeditionPlanDTO.setId(null);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.update(newExpeditionPlanDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("ID inválido!", exception.getMessage()); 
    }

    @Test
    public void update_ShouldThrowExceptionOnUpdateExpeditionPlanWithIdLessThanOne(){
        // Arrange
        ExpeditionPlanDTO newExpeditionPlanDTO = this.getNewExpeditionPlanDTO();
        newExpeditionPlanDTO.setId(0L);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.update(newExpeditionPlanDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("ID inválido!", exception.getMessage()); 
    }

    
}
