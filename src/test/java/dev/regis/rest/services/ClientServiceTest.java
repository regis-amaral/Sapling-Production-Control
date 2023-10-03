package dev.regis.rest.services;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

import dev.regis.rest.models.person.dtos.ClientDTO;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ClientServiceTest {
 
    @Autowired
    private ClientService service;

    @Test
    public void listAll_ShouldReturnListOfClients() {
        // Arrange

        // Act
        List<ClientDTO> result = service.listAll();

        // Assert
        assertNotNull(result);
        assertNotEquals(0, result.size());
    }

    private ClientDTO getNewClientDTO(){
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("XYZ");
        return clientDTO;
    }

    @Test
    public void findById_ShouldReturnExistentClient(){
        // Arrange
        Long id = 1L;
        ClientDTO clientDTO = null;

        // Act
        try{
            clientDTO = service.findById(id);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertNotNull(clientDTO);
        assertEquals(id, clientDTO.getId());
        assertEquals("regis", clientDTO.getName());
    }

    @Test
    public void findById_ShouldThrowExceptionOnFindClientWithNullId(){
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
    public void findById_ShouldThrowExceptionOnFindClientWithIdLessThanOne(){
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
    public void create_ShouldCreateClient(){
        // Arrange
        ClientDTO clientDTO = this.getNewClientDTO();
        
        // Act
        Long id = null;
        try{
            id = service.create(clientDTO);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertNotNull(id);
        assertTrue(id > 0);
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateClientWithNullName() {
        // Arrange
        ClientDTO clientDTO = this.getNewClientDTO();
        clientDTO.setName(null);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(clientDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Parâmetro nome inválido", exception.getMessage());  
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateClientWithEmptyName() {
        // Arrange
        ClientDTO clientDTO = this.getNewClientDTO();
        clientDTO.setName("    ");

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(clientDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Parâmetro nome inválido", exception.getMessage()); 
    }

    @Test
    public void create_ShouldThrowExceptionOnCreateClientWithExistentName() {
        // Arrange
        ClientDTO clientDTO = this.getNewClientDTO();

        ClientDTO existentClientDTO = new ClientDTO();
        try{
            existentClientDTO = service.findById(1L);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }
        clientDTO.setName(existentClientDTO.getName());

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.create(clientDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Dados informados violam restrições no BD.", exception.getMessage());
    }

    @Test
    public void delete_ShouldDeleteClient(){
        // Arrange
        ClientDTO clientDTO = this.getNewClientDTO();
        Long id = null;
        try{
            // Crio um objeto novo para poder deletar
            id = service.create(clientDTO);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }
        clientDTO.setId(id);

        // Act
        try{
            service.deleteById(clientDTO.getId());
        }catch(Exception e){
            fail("Falhou ao deletar um cliente existente.");
        }

        // Assert
        // Se o método chegou até aqui sem lançar uma exceção então ele foi bem sucedido
    }

    @Test
    public void delete_ShouldThrowExceptionOnDeleteNullClientId(){
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
    public void update_ShouldUpdateClient(){
        // Arrange

        // Busca uma espécie existente
        ClientDTO oldClientDTO  = new ClientDTO();
        try{
            oldClientDTO = service.findById(1L);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Cria um novo objeto dto com novos dados para o cliente existente
        ClientDTO newClientDTO = new ClientDTO();
        newClientDTO.setId(oldClientDTO.getId());
        newClientDTO.setName("XYZ");


        // Act
        Long returnedId = null;
        try{
            returnedId = service.update(newClientDTO);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertEquals(returnedId, oldClientDTO.getId());

        // Arrange

        // verificação adicional dos dados persistidos em banco

        ClientDTO persistedClientDTO = new ClientDTO();
        try{
            persistedClientDTO = service.findById(1L);
        }catch(Exception e){
            fail("Ocorreu um erro inesperado: " + e.getMessage());
        }

        // Assert
        assertEquals(newClientDTO.getId(), persistedClientDTO.getId());
        assertEquals(newClientDTO.getName(), persistedClientDTO.getName());
    }

    @Test
    public void update_ShouldThrowExceptionOnUpdateClientWithIdLessThanOne(){
        // Arrange
        ClientDTO newClientDTO = this.getNewClientDTO();
        newClientDTO.setId(0L);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.update(newClientDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("ID inválido!", exception.getMessage()); 
    }

    @Test
    public void update_ShouldThrowExceptionOnUpdateClientWithNullId(){
        // Arrange
        ClientDTO newClientDTO = this.getNewClientDTO();
        newClientDTO.setId(null);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.update(newClientDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("ID inválido!", exception.getMessage()); 
    }

    @Test
    public void update_ShouldThrowExceptionOnUpdateClientWithNullName(){
        // Arrange
        ClientDTO newClientDTO = this.getNewClientDTO();
        newClientDTO.setId(1L);
        newClientDTO.setName(null);

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.update(newClientDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Parâmetro nome inválido", exception.getMessage()); 
    }

    @Test
    public void update_ShouldThrowExceptionOnUpdateClientWithEmptyName(){
        // Arrange
        ClientDTO newClientDTO = this.getNewClientDTO();
        newClientDTO.setId(1L);
        newClientDTO.setName("    ");

        // Act
        Throwable exception = assertThrows(Exception.class, () -> {
            service.update(newClientDTO);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Parâmetro nome inválido", exception.getMessage()); 
    }
}
