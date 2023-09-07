package dev.regis.rest.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.regis.rest.models.dtos.SpecieDTO;
import dev.regis.rest.models.entities.Specie;
import dev.regis.rest.repositories.SpecieRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class SpecieService {

    @Autowired
    SpecieRepository specieRepository;

    @Autowired
    ModelMapper mapper;

    public List<SpecieDTO> listAll() {
        List<Specie> specieList = specieRepository.findAll();
        return SpecieDTO.convertList(specieList);
    }

    public SpecieDTO findById(Long id) throws Exception {
		Optional<Specie> optional = specieRepository.findById(id);
		if (optional.isPresent()) {
			return new SpecieDTO(optional.get());
		} else {
			throw new Exception("Não encontrado");
		}
	}

    public Long create(SpecieDTO specieDTO) throws Exception {
        try {
            Specie specie = mapper.map(specieDTO, Specie.class);
            Specie created = specieRepository.save(specie);
            return created.getId();
        } catch (Exception e) {
            throw new Exception("um erro ocorreu");
        }
    }

    public void deleteById(Long id) {
        specieRepository.deleteById(id);
    }

    public Long update(long id, SpecieDTO newSpecieDTO) throws Exception {
        Optional<Specie> optional = specieRepository.findById(id);
        if (optional.isPresent()) {
            Specie specie = optional.get();
            mapper.map(newSpecieDTO, specie);
            specieRepository.save(specie);
            return specie.getId();
        } else {
            throw new Exception("Um erro ocorreu");
        }
    }

    public List<SpecieDTO> search(String partName,
			Integer page,
			String orderBy,
			Integer itensPerPage,
			String direction) {
		Pageable pageable = PageRequest.of(page, itensPerPage, Sort.Direction.fromString(direction), orderBy);
		Page<Specie> species = specieRepository.search(partName, pageable);
        return SpecieDTO.convertList(species.getContent());
	}
}
