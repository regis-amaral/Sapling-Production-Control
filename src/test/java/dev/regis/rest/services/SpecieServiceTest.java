package dev.regis.rest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import dev.regis.rest.models.production.dtos.SpecieDTO;


@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class SpecieServiceTest {
    
    @Autowired
    private SpecieService service;

    @Test
    public void listAll_ShouldReturnListOfSpecies() {
        // Arrange

        // Act
        List<SpecieDTO> result = service.listAll();

        // Assert
        assertNotNull(result);
        assertNotEquals(0, result.size());
    }

    private SpecieDTO getNewSpecieDTO(){
        SpecieDTO specieDTO = new SpecieDTO();
        specieDTO.setName("XYZ");
        return specieDTO;
    }

    @Test
    public void findById_ShouldReturnExistentSpecie(){
        // Arrange
        Long id = 1L;
        SpecieDTO specieDTO = null;

        // Act
        try{
            specieDTO = service.findById(id);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertNotNull(specieDTO);
        assertEquals(id, specieDTO.getId());
        assertEquals("E. urograndis", specieDTO.getName());
    }

    @Test
    public void findById_ShouldThrowExceptionOnFindSpecieWithNullId(){
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
    public void findById_ShouldThrowExceptionOnFindSpecieWithIdLessThanOne(){
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
    public void create_ShouldCreateSpecie(){
        // Arrange
        SpecieDTO specieDTO = this.getNewSpecieDTO();
        
        // Act
        Long id = null;
        try{
            id = service.create(specieDTO);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertNotNull(id);
        assertTrue(id > 0);
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateSpecieWithNullName() {
        // Arrange
        SpecieDTO specieDTO = this.getNewSpecieDTO();
        specieDTO.setName(null);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(specieDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Parâmetro nome inválido", exception.getMessage());  
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateSpecieWithEmptyName() {
        // Arrange
        SpecieDTO specieDTO = this.getNewSpecieDTO();
        specieDTO.setName("    ");

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(specieDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Parâmetro nome inválido", exception.getMessage()); 
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateSpecieWithExistentName() {
        // Arrange
        SpecieDTO specieDTO = this.getNewSpecieDTO();

        SpecieDTO existentSpecieDTO = new SpecieDTO();
        try{
            existentSpecieDTO = service.findById(1L);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }
        specieDTO.setName(existentSpecieDTO.getName());

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(specieDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Dados informados violam restrições no BD.", exception.getMessage());
    }

    @Test
    public void delete_ShouldDeleteSpecie(){
        // Arrange
        SpecieDTO specieDTO = this.getNewSpecieDTO();
        Long id = null;
        try{
            // Crio um objeto novo para poder deletar
            id = service.create(specieDTO);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }
        specieDTO.setId(id);

        // Act
        try{
            service.deleteById(specieDTO.getId());
        }catch(Exception e){
            fail("Falhou ao deletar uma Espécie existente.");
        }

        // Assert
        // Se o método chegou até aqui sem lançar uma exceção então ele foi bem sucedido
    }

    @Test
    public void delete_ShouldThrowExceptionOnDeleteNullSpecieId(){
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
    public void update_ShouldUpdateSpecie(){
        // Arrange

        // Busca uma espécie existente
        SpecieDTO oldSpecieDTO = new SpecieDTO();
        try{
            oldSpecieDTO = service.findById(1L);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Cria um novo objeto dto com novos dados para a espécie existente
        SpecieDTO newSpecieDTO = new SpecieDTO();
        newSpecieDTO.setId(oldSpecieDTO.getId());
        newSpecieDTO.setName("XYZ");


        // Act
        Long returnedId = null;
        try{
            returnedId = service.update(newSpecieDTO);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertEquals(returnedId, oldSpecieDTO.getId());

        // Arrange

        // verificação adicional dos dados persistidos em banco

        SpecieDTO persistedSpecieDTO = new SpecieDTO();
        try{
            persistedSpecieDTO = service.findById(1L);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertEquals(newSpecieDTO.getId(), persistedSpecieDTO.getId());
        assertEquals(newSpecieDTO.getName(), persistedSpecieDTO.getName());
    }

    @Test
    public void update_ShouldThrowExceptionOnUpdateSpecieWithIdLessThanOne(){
        // Arrange
        SpecieDTO newSpecieDTO = this.getNewSpecieDTO();
        newSpecieDTO.setId(0L);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.update(newSpecieDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("ID inválido!", exception.getMessage()); 
    }

    @Test
    public void update_ShouldThrowExceptionOnUpdateSpecieWithNullId(){
        // Arrange
        SpecieDTO newSpecieDTO = this.getNewSpecieDTO();
        newSpecieDTO.setId(null);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.update(newSpecieDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("ID inválido!", exception.getMessage()); 
    }

    @Test
    public void update_ShouldThrowExceptionOnUpdateSpecieWithNullName(){
        // Arrange
        SpecieDTO newSpecieDTO = this.getNewSpecieDTO();
        newSpecieDTO.setId(1L);
        newSpecieDTO.setName(null);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.update(newSpecieDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Parâmetro nome inválido", exception.getMessage()); 
    }

    @Test
    public void update_ShouldThrowExceptionOnUpdateSpecieWithEmptyName(){
        // Arrange
        SpecieDTO newSpecieDTO = this.getNewSpecieDTO();
        newSpecieDTO.setId(1L);
        newSpecieDTO.setName("    ");

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.update(newSpecieDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Parâmetro nome inválido", exception.getMessage()); 
    }

}
