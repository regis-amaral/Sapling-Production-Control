package dev.regis.rest.TRASH;
// package dev.regis.rest.controllers.person;

// import java.util.ArrayList;
// import java.util.List;

// import static org.junit.Assert.assertEquals;
// import static org.mockito.Mockito.when;

// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.MockitoJUnitRunner;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// import dev.regis.rest.models.person.dtos.ClientDTO;
// import dev.regis.rest.services.ClientService;

// @RunWith(MockitoJUnitRunner.class)
// public class ClientControllerTest {
    
//     @Mock
//     ClientService service;

//     @InjectMocks
//     ClientController controller;

//     @Test
//     public void test_listAll() {
//         List<ClientDTO> expected = new ArrayList<>();

//         // Add expected client objects to the list
    
//         when(service.listAll()).thenReturn(expected);
    
//         List<ClientDTO> result = controller.listAll();
    
//         assertEquals(expected, result);
//     }
    
//     @Test
//     public void test_findById_validId() {
//         Long id = 1L;
//         ClientDTO expected = new ClientDTO();
//         expected.setId(id);
//         expected.setName("Joel");
//         // Set expected values for the client object
    
//         when(service.findById(id)).thenReturn(expected);
    
//         ResponseEntity<Object> result = controller.findById(id);
    
//         assertEquals(HttpStatus.OK, result.getStatusCode());
//         assertEquals(expected, result.getBody());
//     }
// }
