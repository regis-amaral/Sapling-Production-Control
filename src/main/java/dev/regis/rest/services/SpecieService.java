package dev.regis.rest.services;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import dev.regis.rest.models.production.Specie;
import dev.regis.rest.models.production.dtos.SpecieDTO;
import dev.regis.rest.repositories.SpecieRepository;

@Service
public class SpecieService{

    @Autowired
    SpecieRepository repository;

    @Autowired
    ModelMapper mapper;
  
    public List<SpecieDTO> listAll() {
        return repository.findAll().stream()
            .map(specie -> mapper.map(specie, SpecieDTO.class))
            .collect(Collectors.toList());        
    }

    public SpecieDTO findById(Long id) throws Exception {
        Optional<Specie> optional = repository.findById(id);
        if (optional.isPresent()) {
            return mapper.map(optional.get(), SpecieDTO.class);
        } else {
            throw new Exception("Não encontrado");
        }
    }

    public Long create(SpecieDTO newObjectDTO) throws Exception {
        // TODO validar métodos e tratar exceções (campos obrigatórios, campos únicos, ...)
        try {
            Specie entity = mapper.map(newObjectDTO, Specie.class);
            Specie created = repository.save(entity);
            Method getIdMethod = created.getClass().getMethod("getId");
            return (Long) getIdMethod.invoke(created);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Um erro ocorreu!");
        }
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Long update(SpecieDTO newObjectDTO) throws Exception {
        // TODO validar métodos e tratar exceções (campos obrigatórios, campos únicos, ...)
        Method getIdMethod = newObjectDTO.getClass().getMethod("getId");
        Long id = (Long) getIdMethod.invoke(newObjectDTO);
        System.out.println("buscando pelo id " + id);
        Optional<Specie> optional = repository.findById(id);
        if (optional.isPresent()) {
            Specie entity = optional.get();
            mapper.map(newObjectDTO, entity);
            repository.save(entity);
            getIdMethod = entity.getClass().getMethod("getId");
            return (Long) getIdMethod.invoke(entity);
        } else {
            throw new Exception("Um erro ocorreu!");
        }
    }

    public List<SpecieDTO> search(String partName,
			Integer page,
			String orderBy,
			Integer itensPerPage,
			String direction) {
		Pageable pageable = PageRequest.of(page, itensPerPage, Sort.Direction.fromString(direction), orderBy);
		Page<Specie> species = repository.search(partName, pageable);

        return species.getContent().stream()
            .map(specie -> mapper.map(specie, SpecieDTO.class))
            .collect(Collectors.toList());
            
	}
}
