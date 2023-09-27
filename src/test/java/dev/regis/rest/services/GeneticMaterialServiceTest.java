package dev.regis.rest.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import dev.regis.rest.models.production.dtos.GeneticMaterialDTO;
import dev.regis.rest.repositories.GeneticMaterialRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
public class GeneticMaterialServiceTest {

    @Autowired(required = true)
    GeneticMaterialService service;

    // @Before
    // public void setUp() {
    //     MockitoAnnotations.openMocks(this);
    // }

    // listAll method returns a list of GeneticMaterialDTO objects
    @Test
    public void test_listAll_returnsListOfGeneticMaterialDTO() {
        // Arrange
        // GeneticMaterialService service = new GeneticMaterialService();

        // Act
        List<GeneticMaterialDTO> result = service.listAll();

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

}
