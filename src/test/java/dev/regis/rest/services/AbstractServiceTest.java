package dev.regis.rest.services;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import dev.regis.rest.models.production.Specie;
import dev.regis.rest.models.production.dtos.SpecieDTO;

public class AbstractServiceTest {
    
    @Mock
    JpaRepository<Object, Long> repository;

    @InjectMocks
    AbstractService<Specie, SpecieDTO> service = new AbstractService<Specie, SpecieDTO>() {};

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;

        service.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }
    
}
