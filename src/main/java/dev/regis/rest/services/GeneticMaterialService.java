package dev.regis.rest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.regis.rest.models.dtos.production.GeneticMaterialDTO;
import dev.regis.rest.repositories.GeneticMaterialRepository;

@Service
public class GeneticMaterialService {

	@Autowired
	GeneticMaterialRepository repository;

	@Autowired
	ModelMapper mapper;

	public List<GeneticMaterialDTO> listAll() {
		return repository.findAll().stream()
               .map(entity -> mapper.map(entity, GeneticMaterialDTO.class))
               .collect(Collectors.toList());
		// return super.listAllObjects(GeneticMaterial.class, GeneticMaterialDTO.class); 
	}

	// @Override
	// public GeneticMaterialDTO findById(Long id) throws Exception {
	// 	return super.findObjectById(id, GeneticMaterialDTO.class);
	// }

	// @Override
	// public Long create(GeneticMaterialInputDTO newGeneticMaterialDTO) throws Exception {
	// 	return super.createNewObject(newGeneticMaterialDTO, GeneticMaterial.class);
	// }

	// @Override
	// public void deleteById(Long id) {
	// 	super.deleteObjectById(id);
	// }

	// @Override
	// public Long update(GeneticMaterialInputDTO newGeneticMaterialDTO) throws Exception {

	// 	return super.updateObject(newGeneticMaterialDTO);
	// }

	/*
	 * Buscar um Material Genético por parte do nome
	 */
	// public List<GeneticMaterialDTO> search(String partName,
	// 		Integer page,
	// 		String orderBy,
	// 		Integer itensPerPage,
	// 		String direction) {
	// 	Pageable pageable = PageRequest.of(page, itensPerPage, Sort.Direction.fromString(direction), orderBy);
	// 	Page<GeneticMaterial> geneticMaterials = geneticMaterialRepository.search(partName, pageable);
	// 	return GeneticMaterialDTO.convert(geneticMaterials.getContent());
	// }

}
