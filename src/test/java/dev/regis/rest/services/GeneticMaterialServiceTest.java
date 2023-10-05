package dev.regis.rest.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import dev.regis.rest.models.production.Specie;
import dev.regis.rest.models.production.dtos.GeneticMaterialDTO;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class GeneticMaterialServiceTest {
    
    @Autowired
    private GeneticMaterialService service;

    @Test
    public void listAll_ShouldReturnListOfGeneticMaterials() {
        // Arrange

        // Act
        List<GeneticMaterialDTO> result = service.listAll();

        // Assert
        assertNotNull(result);
        assertNotEquals(0, result.size());
    }

    private GeneticMaterialDTO getNewGeneticMaterialDTO(){
        GeneticMaterialDTO geneticMaterialDTO = new GeneticMaterialDTO();
        geneticMaterialDTO.setName("XYZ");
        Specie specie = new Specie();
        specie.setId(1L);
        geneticMaterialDTO.setSpecie(specie);

        return geneticMaterialDTO;
    }
    
    @Test
    public void findById_ShouldReturnExistentGeneticMaterial(){
        // Arrange
        Long id = 1L;
        GeneticMaterialDTO geneticMaterialDTO = null;

        // Act
        try{
            geneticMaterialDTO = service.findById(id);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertNotNull(geneticMaterialDTO);
        assertEquals(id, geneticMaterialDTO.getId());
        assertEquals("WRK 6201", geneticMaterialDTO.getName());
    }

    @Test
    public void findById_ShouldThrowExceptionOnFindGeneticMaterialWithNullId(){
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
    public void findById_ShouldThrowExceptionOnFindGeneticMaterialWithIdLessThanOne(){
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
    public void create_ShouldCreateGeneticMaterial(){
        // Arrange
        GeneticMaterialDTO geneticMaterialDTO = this.getNewGeneticMaterialDTO();
        
        // Act
        Long id = null;
        try{
            id = service.create(geneticMaterialDTO);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertNotNull(id);
        assertTrue(id > 0);
    }
    
    @Test
    public void create_ShouldThrowExceptionOnCreateGeneticMaterialWithNullName() {
        // Arrange
        GeneticMaterialDTO geneticMaterialDTO = this.getNewGeneticMaterialDTO();
        geneticMaterialDTO.setName(null);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(geneticMaterialDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Parâmetro nome inválido", exception.getMessage());  
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateGeneticMaterialWithEmptyName() {
        // Arrange
        GeneticMaterialDTO geneticMaterialDTO = this.getNewGeneticMaterialDTO();
        geneticMaterialDTO.setName("    ");

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(geneticMaterialDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Parâmetro nome inválido", exception.getMessage()); 
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateGeneticMaterialWithExistentName() {
        // Arrange
        GeneticMaterialDTO geneticMaterialDTO = this.getNewGeneticMaterialDTO();

        GeneticMaterialDTO existentGeneticMaterialDTO = new GeneticMaterialDTO();
        try{
            existentGeneticMaterialDTO = service.findById(1L);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }
        geneticMaterialDTO.setName(existentGeneticMaterialDTO.getName());

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(geneticMaterialDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Dados informados violam restrições no BD.", exception.getMessage());
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateGeneticMaterialWithNullSpecie() {
        // Arrange
        GeneticMaterialDTO geneticMaterialDTO = this.getNewGeneticMaterialDTO();
        geneticMaterialDTO.setSpecie(null);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(geneticMaterialDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Deve ser selecionada uma espécie", exception.getMessage());
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateGeneticMaterialWithSpecieWithoutId() {
        // Arrange
        GeneticMaterialDTO geneticMaterialDTO = this.getNewGeneticMaterialDTO();
        geneticMaterialDTO.setSpecie(new Specie());

        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            service.create(geneticMaterialDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Deve ser selecionada uma espécie", exception.getMessage());
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateGeneticMaterialWithNonexistentSpecie() {
        // Arrange
        GeneticMaterialDTO geneticMaterialDTO = this.getNewGeneticMaterialDTO();
        Specie specie = new Specie();
        specie.setId(1000000L);
        geneticMaterialDTO.setSpecie(specie);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            service.create(geneticMaterialDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Dados informados violam restrições no BD.", exception.getMessage());
    }

    @Test
    public void delete_ShouldDeleteGeneticMaterial(){
        // Arrange
        GeneticMaterialDTO geneticMaterialDTO = this.getNewGeneticMaterialDTO();
        Long id = null;
        try{ 
            // Crio um objeto novo para poder deletar
            id = service.create(geneticMaterialDTO);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }
        geneticMaterialDTO.setId(id);

        // Act
        try{
            service.deleteById(geneticMaterialDTO.getId());
        }catch(Exception e){
            fail("Falhou ao deletar um Material Genético existente.");
        }

        // Assert
        // Se o método chegou até aqui sem lançar uma exceção então ele foi bem sucedido
    }

    @Test
    public void delete_ShouldThrowExceptionOnDeleteNullGeneticMaterialId(){
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
    public void update_ShouldUpdateGeneticMaterial(){
        // Arrange

        // Busca um material genético existente
        GeneticMaterialDTO oldGeneticMaterialDTO = new GeneticMaterialDTO();
        try{
            oldGeneticMaterialDTO = service.findById(1L);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Cria um novo objeto dto com novos dados para o material genético existente
        GeneticMaterialDTO newGeneticMaterialDTO = new GeneticMaterialDTO();
        newGeneticMaterialDTO.setId(oldGeneticMaterialDTO.getId());
        newGeneticMaterialDTO.setName("XYZ");
        newGeneticMaterialDTO.setDescription("descrição XYZ");
        Specie specie = new Specie();
        specie.setId(1L);
        newGeneticMaterialDTO.setSpecie(specie);

        // Act
        Long returnedId = null;
        try{
            returnedId = service.update(newGeneticMaterialDTO);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertEquals(returnedId, oldGeneticMaterialDTO.getId());

        // Arrange
        // verificação adicional dos dados persistidos em banco

        GeneticMaterialDTO persistedGeneticMaterialDTO = null;
        try{
            persistedGeneticMaterialDTO = service.findById(oldGeneticMaterialDTO.getId());
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertNotNull(persistedGeneticMaterialDTO);
        assertEquals(newGeneticMaterialDTO.getId(), persistedGeneticMaterialDTO.getId());
        assertEquals(newGeneticMaterialDTO.getName(), persistedGeneticMaterialDTO.getName());
        assertEquals(newGeneticMaterialDTO.getDescription(), persistedGeneticMaterialDTO.getDescription());
        assertEquals(newGeneticMaterialDTO.getSpecie().getId(), persistedGeneticMaterialDTO.getSpecie().getId());
    }

    @Test
    public void update_ShouldThrowExceptionOnUpdateGeneticMaterialWithNullId(){
        // Arrange
        GeneticMaterialDTO newGeneticMaterialDTO = this.getNewGeneticMaterialDTO();
        newGeneticMaterialDTO.setId(null);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.update(newGeneticMaterialDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("ID inválido!", exception.getMessage()); 
    }

    @Test
    public void update_ShouldThrowExceptionOnUpdateGeneticMaterialWithIdLessThanOne(){
        // Arrange
        GeneticMaterialDTO newGeneticMaterialDTO = this.getNewGeneticMaterialDTO();
        newGeneticMaterialDTO.setId(0L);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.update(newGeneticMaterialDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("ID inválido!", exception.getMessage()); 
    }

    @Test
    public void update_ShouldThrowExceptionOnUpdateGeneticMaterialWithNullName(){
        // Arrange
        GeneticMaterialDTO newGeneticMaterialDTO = this.getNewGeneticMaterialDTO();
        newGeneticMaterialDTO.setId(1L);
        newGeneticMaterialDTO.setName(null);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.update(newGeneticMaterialDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Parâmetro nome inválido", exception.getMessage()); 
    }

    @Test
    public void update_ShouldThrowExceptionOnUpdateGeneticMaterialWithEmptyName(){
        // Arrange
        GeneticMaterialDTO newGeneticMaterialDTO = this.getNewGeneticMaterialDTO();
        newGeneticMaterialDTO.setId(1L);
        newGeneticMaterialDTO.setName("    ");

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.update(newGeneticMaterialDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Parâmetro nome inválido", exception.getMessage()); 
    }

    @Test
    public void update_ShouldThrowExceptionOnUpdateGeneticMaterialWithNullSpecie(){
        // Arrange
        GeneticMaterialDTO newGeneticMaterialDTO = this.getNewGeneticMaterialDTO();
        newGeneticMaterialDTO.setId(1L);
        newGeneticMaterialDTO.setSpecie(null);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.update(newGeneticMaterialDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Deve ser selecionada uma espécie", exception.getMessage()); 
    }

    @Test
    public void update_ShouldThrowExceptionOnUpdateGeneticMaterialWithNullSpecieId(){
        // Arrange
        GeneticMaterialDTO newGeneticMaterialDTO = this.getNewGeneticMaterialDTO();
        newGeneticMaterialDTO.setId(1L);
        newGeneticMaterialDTO.setSpecie(new Specie());

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.update(newGeneticMaterialDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Deve ser selecionada uma espécie", exception.getMessage()); 
    }

   


}
