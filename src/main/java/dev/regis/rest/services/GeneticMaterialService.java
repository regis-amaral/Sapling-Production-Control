package dev.regis.rest.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import dev.regis.rest.models.dtos.production.GeneticMaterialDTO;
import dev.regis.rest.models.dtos.production.GeneticMaterialInputDTO;
import dev.regis.rest.models.entities.production.GeneticMaterial;
import dev.regis.rest.repositories.GeneticMaterialRepository;
import dev.regis.rest.repositories.SpecieRepository;
import dev.regis.rest.services.interfaces.IService;

@Service
public class GeneticMaterialService
		extends AbstractService<GeneticMaterial, GeneticMaterialInputDTO, GeneticMaterialDTO>
		implements IService<GeneticMaterial, GeneticMaterialInputDTO, GeneticMaterialDTO> {

	@Autowired
	GeneticMaterialRepository geneticMaterialRepository;

	@Autowired
	SpecieRepository specieRepository;

	@Autowired
	ModelMapper mapper;

	@Override
	public List<GeneticMaterialDTO> listAll() {
		return super.listAllObjects(GeneticMaterialDTO.class);
	}

	@Override
	public GeneticMaterialDTO findById(Long id) throws Exception {
		return super.findObjectById(id, GeneticMaterialDTO.class);
	}

	@Override
	public Long create(GeneticMaterialInputDTO newGeneticMaterialDTO) throws Exception {
		return super.createNewObject(newGeneticMaterialDTO, GeneticMaterial.class);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteObjectById(id);
	}

	@Override
	public Long update(GeneticMaterialInputDTO newGeneticMaterialDTO) throws Exception {

		return super.updateObject(newGeneticMaterialDTO);
	}

	/*
	 * Buscar um Material Genético por parte do nome
	 */
	public List<GeneticMaterialDTO> search(String partName,
			Integer page,
			String orderBy,
			Integer itensPerPage,
			String direction) {
		Pageable pageable = PageRequest.of(page, itensPerPage, Sort.Direction.fromString(direction), orderBy);
		Page<GeneticMaterial> geneticMaterials = geneticMaterialRepository.search(partName, pageable);
		return GeneticMaterialDTO.convert(geneticMaterials.getContent());
	}

}
