package dev.regis.rest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.regis.rest.models.dtos.GeneticMaterialDTO;
import dev.regis.rest.models.entities.GeneticMaterial;
import dev.regis.rest.repositories.GeneticMaterialRepository;

@Service
public class GeneticMaterialService {
    
    @Autowired
	GeneticMaterialRepository geneticMaterialRepository;

	@Autowired
	ModelMapper mapper;

	/*
	 * Lista todos os Materiais Genéticos existentes
	 */
    public List<GeneticMaterialDTO> listAll() {
		List<GeneticMaterialDTO> geneticMaterialDTO = geneticMaterialRepository
			.findAll()
			.stream()
			.map(geneticMaterial -> mapper.map(geneticMaterial, GeneticMaterialDTO.class))
			.toList();

		return geneticMaterialDTO;
	}

	/*
	 * Retorna um Material Genético pelo ID informado
	 */
	public GeneticMaterialDTO findById(Long id) throws Exception {
		Optional<GeneticMaterial> optional = geneticMaterialRepository.findById(id);
		if (optional.isPresent()) {
			return mapper.map(optional.get(), GeneticMaterialDTO.class);
		}else{
			throw new Exception("Não encontrado");
		}
	}

	/*
	 * Insere um novo Material Genético
	 */
	public Long create(GeneticMaterialDTO geneticMaterialDTO) throws Exception {
		try {
			GeneticMaterial geneticMaterial = mapper.map(geneticMaterialDTO, GeneticMaterial.class);
			GeneticMaterial created = geneticMaterialRepository.save(geneticMaterial);
			return created.getId();
		} catch (Exception e) {
			throw new Exception("um erro ocorreu");
		}
	}

	/*
	 * Remove um Material Genético existente
	 */
	public void deleteById(Long id) {
		geneticMaterialRepository.deleteById(id);
	}

	/*
	 * Atualiza um Material Genetico existente
	 */
	public Long update(long id, GeneticMaterialDTO newGeneticMaterialDTO) throws Exception {
		Optional<GeneticMaterial> optional = geneticMaterialRepository.findById(id);
		if(optional.isPresent()) {
			GeneticMaterial geneticMaterial = optional.get();
			mapper.map(newGeneticMaterialDTO, geneticMaterial);
			geneticMaterialRepository.save(geneticMaterial);
			return geneticMaterial.getId();
		} else {
			throw new Exception("Um erro ocorreu");
		}
	}

	public List<GeneticMaterialDTO> search(String partName,
                                   Integer page,
                                   String orderBy,
                                   Integer itensPerPage,
                                   String direction
    ) {
        Pageable pageable = PageRequest.of(page, itensPerPage, Sort.Direction.fromString(direction), orderBy);
        Page<GeneticMaterial> geneticMaterials = geneticMaterialRepository.search(partName, pageable);
        List<GeneticMaterialDTO> geneticMaterialDTOs = new ArrayList<>();
        
        geneticMaterials.forEach(geneticMaterial -> {
            GeneticMaterialDTO geneticMaterialDTO = mapper.map(geneticMaterial, GeneticMaterialDTO.class);
            geneticMaterialDTOs.add(geneticMaterialDTO);
        });

        return geneticMaterialDTOs;
    }

}
