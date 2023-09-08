package dev.regis.rest.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.regis.rest.models.production.dtos.SpecieDTO;
import dev.regis.rest.models.production.entities.Specie;
import dev.regis.rest.repositories.SpecieRepository;
import dev.regis.rest.services.interfaces.IService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class SpecieService
    extends AbstractService<Specie, SpecieDTO, SpecieDTO> 
    implements IService <Specie, SpecieDTO, SpecieDTO> {

    @Autowired
    SpecieRepository specieRepository;

    @Autowired
    ModelMapper mapper;

    /*
	 * Lista todas as Espécies existentes
	 */
    public List<SpecieDTO> listAll() {
        return super.listAllObjects(SpecieDTO.class);
    }

    /*
	 * Retorna uma Espécies pelo ID informado
	 */
    public SpecieDTO findById(Long id) throws Exception {
		return super.findObjectById(id, SpecieDTO.class);
	}

    /*
	 * Insere uma nova Espécie
	 */
    public Long create(SpecieDTO newSpecieDTO) throws Exception {
        return super.createNewObject(newSpecieDTO, Specie.class);
    }

    /*
	 * Remove uma Espécie existente
	 */
    public void deleteById(Long id) {
        super.deleteObjectById(id);
    }

    /*
	 * Atualiza uma Espécie existente
	 */
    public Long update(SpecieDTO newSpecieDTO) throws Exception {
        return super.updateObject(newSpecieDTO);
    }

    /*
     * Buscar uma Espécie por parte do nome
     */
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
