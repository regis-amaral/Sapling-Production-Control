package dev.regis.rest.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import dev.regis.rest.models.production.Specie;
import dev.regis.rest.models.production.dtos.SpecieDTO;
import dev.regis.rest.repositories.SpecieRepository;

@RunWith(MockitoJUnitRunner.class)
public class SpecieServiceBTest {
    
    @Mock
    SpecieRepository repository;

    @Spy
    ModelMapper mapper = new ModelMapper();
    
    @InjectMocks
    SpecieService specieService = new SpecieService();

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void listAll_ShouldReturnAListOfSpecies(){

        List<Specie> specieList = this.getListSpecie();

        when(repository.findAll()).thenReturn(specieList);
        

        List<SpecieDTO> expectedList = specieList.stream()
        .map(specie -> mapper.map(specie, SpecieDTO.class))
        .collect(Collectors.toList());

        List<SpecieDTO> actualDtoList = specieService.listAll();
        assertEquals(expectedList, actualDtoList);

    }

    @Test
    public void findById_ShouldReturnASpecie(){
        Specie specie = new Specie();
        specie.setId(1L);
        specie.setName("Dog");

        Optional<Specie> optionalSpecie = Optional.of(specie);
        
        when(repository.findById(1L)).thenReturn(optionalSpecie);

        try{
            SpecieDTO result = specieService.findById(1L);
            assertNotNull(result);
            assertEquals(specie.getName(), result.getName());
        }catch(Exception e){
            fail("Não deveria lançar uma exceção aqui: " + e.getMessage());
        }

        when(repository.findById(100000L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            specieService.findById(100000L);
        });

        assertTrue(exception instanceof Exception);
        assertEquals("Não encontrado", exception.getMessage());
    }

    @Test
    public void create_MustReturnASpecieId(){
        Specie specie = new Specie();
        specie.setId(1L);
        specie.setName("Dog");
        
        SpecieDTO specieDto = new SpecieDTO();
        specieDto.setId(1L);
        specieDto.setName("Dog");

        try{
            when(mapper.map(specieDto, Specie.class)).thenReturn(specie);

            Specie specieCreated = specie;
            
            when(repository.save(specie)).thenReturn(specieCreated);

            Long actual = specieService.create(specieDto);

            assertEquals(specie.getId(), actual);

        }catch(Exception e){
            fail("Não deveria lançar uma exceção aqui: " + e.getMessage());
        }

        Exception exception = assertThrows(Exception.class, () -> {
            specieService.create(new SpecieDTO());
        });

        assertTrue(exception instanceof Exception);
        assertEquals("Um erro ocorreu!", exception.getMessage());
    }

    @Test
    public void deleteById_MustExecuteTheMethodOnce(){
        assertThrows(IllegalArgumentException.class, () -> specieService.deleteById(null));

        assertThrows(IllegalArgumentException.class, () -> specieService.deleteById(0L));

        assertThrows(IllegalArgumentException.class, () -> specieService.deleteById(-1L));

        specieService.deleteById(1L);

        verify(repository, times(1)).deleteById(1L);

    }

    @Test
    public void update_ValidObject(){
        SpecieDTO specieDtoNew = new SpecieDTO();
        specieDtoNew.setId(1L);
        specieDtoNew.setName("Cat");

        Specie specieOld = new Specie();
        specieOld.setId(1L);
        specieOld.setName("Dog");

        when(repository.findById(1L)).thenReturn(Optional.of(specieOld));

        when(repository.save(any())).thenReturn(any());

        try{
            Long id = specieService.update(specieDtoNew);
            
            assertEquals(specieOld.getId(), id);

            verify(repository, times(1)).save(any());
        }catch(Exception e){
            fail("Não deveria lançar uma exceção aqui: " + e.getMessage());
        }

    }

    @Test
    public void update_InvalidObject(){
        SpecieDTO specieDtoNew = new SpecieDTO();
        
        assertThrows(Exception.class, () -> specieService.update(specieDtoNew));

        specieDtoNew.setId(1L);

        Specie specieExpected = mapper.map(specieDtoNew, Specie.class);

        when(repository.findById(specieDtoNew.getId())).thenReturn(Optional.of(specieExpected));

        Exception exception = assertThrows(Exception.class, () -> specieService.update(specieDtoNew));

        assertEquals("Objeto inválido!", exception.getMessage());

        specieDtoNew.setName("Dog");

        assertDoesNotThrow(() -> specieService.update(specieDtoNew));

        verify(repository, times(1)).save(any());

        when(repository.findById(specieDtoNew.getId())).thenReturn(Optional.empty());

        exception = assertThrows(Exception.class, () -> specieService.update(specieDtoNew));

        assertEquals("Um erro ocorreu!", exception.getMessage());

    }

    

    private List<Specie> getListSpecie(){
        Specie specie1 = new Specie();
        specie1.setId(1L);
        specie1.setName("Dog");

        Specie specie2 = new Specie();
        specie2.setId(2L);
        specie2.setName("Cat");

        List<Specie> specieList = new ArrayList();
        specieList.add(specie1);
        specieList.add(specie2);    
        
        return specieList;
    }

    private List<SpecieDTO> getListSpecieDTO(){
        SpecieDTO specieDto1 = new SpecieDTO();
        specieDto1.setId(1L);
        specieDto1.setName("Dog");

        SpecieDTO specieDto2 = new SpecieDTO();
        specieDto2.setId(2L);
        specieDto2.setName("Cat");

        List<SpecieDTO> dtoList = new ArrayList();
        dtoList.add(specieDto1);
        dtoList.add(specieDto2);

        return dtoList;
    }

}
