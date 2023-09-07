// package dev.regis.rest.repositories;

// import static org.junit.Assert.*;

// import java.util.Optional;

// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit4.SpringRunner;

// import dev.regis.rest.models.entities.GeneticMaterial;

// @RunWith(SpringRunner.class)
// @SpringBootTest
// public class GeneticMaterialRepositoryTest {
//     @Autowired
//     private GeneticMaterialRepository geneticMaterialRepository;

//     @Test
//     public void findByName() {
//         String name = "Pinus 1";
//         Optional<GeneticMaterial> result = geneticMaterialRepository.findByName(name);
//         assertTrue(result.isPresent());
//         assertEquals(name, result.get().getName());
//     }

//     // @Test
//     // public void sumTwoNumbers(){
//     //     float numA = 10.0f;
//     //     float numB = 5.0f;
//     //     float expected = numA + numB + 1;
//     //     assertEquals(expected, numA + numB, 0);
//     // }
//     // @Test
//     // public void testSearch() {
//     //     String partName = "parteDoNome";
//     //     Pageable pageable = PageRequest.of(0, 10);
//     //     Page<GeneticMaterial> resultPage = geneticMaterialRepository.search(partName, pageable);

//     // }
// }
