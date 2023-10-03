// package dev.regis.rest.services;

// import static org.junit.Assert.assertEquals;
// import static org.mockito.Mockito.when;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.stream.Collectors;

// import org.junit.Before;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.MockitoAnnotations;
// import org.mockito.Spy;
// import org.mockito.junit.MockitoJUnitRunner;
// import org.modelmapper.ModelMapper;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.jdbc.Sql;
// import org.springframework.test.context.junit4.SpringRunner;

// import dev.regis.rest.models.production.Specie;
// import dev.regis.rest.models.production.dtos.SpecieDTO;
// import dev.regis.rest.repositories.SpecieRepository;

// @DataJpaTest
// @Sql("/h2/LibraryRepositorySpecie.sql")
// @RunWith(MockitoJUnitRunner.class)
// public class SpecieServiceTestOld {
    
//     @Mock
//     SpecieRepository repository;

//     @Spy
//     ModelMapper mapper = new ModelMapper();
    
//     @InjectMocks
//     SpecieService specieService = new SpecieService();

//     @Before
//     public void setUp() {
//         MockitoAnnotations.openMocks(this);
//     }

//     @Test
//     public void testListAll(){
//         List<Specie> specieList = this.getListSpecie();

//         Mockito.when(repository.findAll()).thenReturn(specieList);
        
//         List<SpecieDTO> expectedList = specieList.stream()
//         .map(specie -> mapper.map(specie, SpecieDTO.class))
//         .collect(Collectors.toList());

//         List<SpecieDTO> actualDtoList = specieService.listAll();
//         assertEquals(expectedList, actualDtoList);
//     }




//     private List<Specie> getListSpecie(){
//         Specie specie1 = new Specie();
//         specie1.setId(1L);
//         specie1.setName("Dog");

//         Specie specie2 = new Specie();
//         specie2.setId(2L);
//         specie2.setName("Cat");

//         List<Specie> specieList = new ArrayList();
//         specieList.add(specie1);
//         specieList.add(specie2);    
        
//         return specieList;
//     }

//     private List<SpecieDTO> getListSpecieDTO(){
//         SpecieDTO specieDto1 = new SpecieDTO();
//         specieDto1.setId(1L);
//         specieDto1.setName("Dog");

//         SpecieDTO specieDto2 = new SpecieDTO();
//         specieDto2.setId(2L);
//         specieDto2.setName("Cat");

//         List<SpecieDTO> dtoList = new ArrayList<>();
//         dtoList.add(specieDto1);
//         dtoList.add(specieDto2);

//         return dtoList;
//     }

// }
