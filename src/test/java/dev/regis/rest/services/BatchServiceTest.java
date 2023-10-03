// package dev.regis.rest.services;

// import static org.junit.jupiter.api.Assertions.assertNotEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;

// import java.util.List;

// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit4.SpringRunner;
// import org.springframework.transaction.annotation.Transactional;

// import dev.regis.rest.models.production.dtos.BatchDTO;
// import dev.regis.rest.models.production.dtos.GeneticMaterialDTO;

// @SpringBootTest
// @RunWith(SpringRunner.class)
// @Transactional
// public class BatchServiceTest {
    
//     @Autowired
//     private BatchService service;

//     @Test
//     public void listAll_ShouldReturnListOfBatch() {
//         // Arrange

//         // Act
//         List<BatchDTO> result = service.listAll();

//         // Assert
//         assertNotNull(result);
//         assertNotEquals(0, result.size());
//     }
// }
