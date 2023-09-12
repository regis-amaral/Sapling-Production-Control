package dev.regis.rest.services;

import java.util.List;
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
import dev.regis.rest.services.interfaces.IService;

@Service
public class SpecieService extends AbstractService <Specie, SpecieDTO> implements IService <Specie, SpecieDTO>{

    @Autowired
    SpecieRepository repository;

    @Autowired
    ModelMapper mapper;
  
    public List<SpecieDTO> listAll() {
        return super.listAll(SpecieDTO.class);          
    }

    public SpecieDTO findById(Long id) throws Exception {
        return super.findById(id, SpecieDTO.class);
    }

    public Long create(SpecieDTO objectDTO) throws Exception {
        return super.create(objectDTO, Specie.class);
    }

    public void deleteById(Long id) {
        super.deleteById(id);
    }

    public Long update(SpecieDTO newSpecieDTO) throws Exception {
        return super.update(newSpecieDTO);
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
