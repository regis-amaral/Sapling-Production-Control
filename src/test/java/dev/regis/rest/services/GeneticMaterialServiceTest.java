package dev.regis.rest.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
    public void test_listAll_returnsListOfGeneticMaterialDTO() {
        // Arrange

        // Act
        List<GeneticMaterialDTO> result = service.listAll();

        // Assert
        assertNotNull(result);
        assertNotEquals(0, result.size());
    }

    private GeneticMaterialDTO getNewGeneticMaterial(){
        GeneticMaterialDTO geneticMaterialDTO = new GeneticMaterialDTO();
        geneticMaterialDTO.setName("XYZ");
        Specie specie = new Specie();
        specie.setId(1L);
        geneticMaterialDTO.setSpecie(specie);

        return geneticMaterialDTO;
    }
    
    @Test
    public void test_find_existent_genetic_material(){
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
    public void test_throws_exception_on_find_genetic_material_with_null_id(){
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
    public void test_throws_exception_on_find_genetic_material_with_id_less_than_one(){
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
    public void test_findById_genetic_material(){
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
    public void test_create_genetic_material(){
        // Arrange
        GeneticMaterialDTO geneticMaterialDTO = this.getNewGeneticMaterial();
        
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
    public void test_throws_exception_on_create_genetic_material_with_null_name() {
        // Arrange
        GeneticMaterialDTO geneticMaterialDTO = this.getNewGeneticMaterial();
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
    public void test_throws_exception_on_create_genetic_material_with_empty_name() {
        // Arrange
        GeneticMaterialDTO geneticMaterialDTO = this.getNewGeneticMaterial();
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
    public void test_throws_exception_on_create_genetic_material_with_existent_name() {
        // Arrange
        GeneticMaterialDTO geneticMaterialDTO = this.getNewGeneticMaterial();
        geneticMaterialDTO.setName("WRK 6201");

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(geneticMaterialDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Dados informados violam restrições no BD.", exception.getMessage());
    }

    @Test
    public void test_throws_exception_on_create_genetic_material_with_null_specie() {
        // Arrange
        GeneticMaterialDTO geneticMaterialDTO = this.getNewGeneticMaterial();
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
    public void test_throws_exception_on_create_genetic_material_with_specie_without_id() {
        // Arrange
        GeneticMaterialDTO geneticMaterialDTO = this.getNewGeneticMaterial();
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
    public void test_throws_exception_on_create_genetic_material_nonexistent_specie() {
        // Arrange
        GeneticMaterialDTO geneticMaterialDTO = this.getNewGeneticMaterial();
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
    public void test_delete_genetic_material(){
        // Arrange
        GeneticMaterialDTO geneticMaterialDTO = this.getNewGeneticMaterial();
        Long id = null;
        try{
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
        // Se o método chegou até aqui sem lançar uma exeção então ele foi bem sucedido
    }

    @Test
    public void test_throws_exception_on_delete_null_genetic_material(){
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
    public void test_update_genetic_material(){
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

        GeneticMaterialDTO persistedGeneticMaterialDTO = new GeneticMaterialDTO();
        try{
            persistedGeneticMaterialDTO = service.findById(1L);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertEquals(newGeneticMaterialDTO.getId(), persistedGeneticMaterialDTO.getId());
        assertEquals(newGeneticMaterialDTO.getName(), persistedGeneticMaterialDTO.getName());
        assertEquals(newGeneticMaterialDTO.getDescription(), persistedGeneticMaterialDTO.getDescription());
        assertEquals(newGeneticMaterialDTO.getSpecie().getId(), persistedGeneticMaterialDTO.getSpecie().getId());
    }
}