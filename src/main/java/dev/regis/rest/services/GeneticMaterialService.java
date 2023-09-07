package dev.regis.rest.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import dev.regis.rest.models.dtos.GeneticMaterialDTO;
import dev.regis.rest.models.entities.GeneticMaterial;
import dev.regis.rest.repositories.GeneticMaterialRepository;

@Service
public class GeneticMaterialService extends ServiceAbstract<GeneticMaterial, GeneticMaterialDTO> {

	@Autowired
	GeneticMaterialRepository geneticMaterialRepository;

	@Autowired
	ModelMapper mapper;

	@Autowired
	SpecieService specieService;

	/*
	 * Lista todos os Materiais Genéticos existentes
	 */
	public List<GeneticMaterialDTO> listAll() {
		return super.listAll(GeneticMaterialDTO.class);
	}

	/*
	 * Retorna um Material Genético pelo ID informado
	 */
	public GeneticMaterialDTO findById(Long id) throws Exception {
		return super.findById(id, GeneticMaterialDTO.class);
	}

	/*
	 * Insere um novo Material Genético
	 */
	public Long create(GeneticMaterialDTO newGeneticMaterialDTO) throws Exception {
		return super.create(newGeneticMaterialDTO, GeneticMaterial.class);
	}

	/*
	 * Remove um Material Genético existente
	 */
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	/*
	 * Atualiza um Material Genetico existente
	 */
	public Long update(GeneticMaterialDTO newGeneticMaterialDTO) throws Exception {
		return super.update(newGeneticMaterialDTO);
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
