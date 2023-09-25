package dev.regis.rest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import dev.regis.rest.models.production.GeneticMaterial;
import dev.regis.rest.models.production.dtos.GeneticMaterialDTO;
import dev.regis.rest.repositories.GeneticMaterialRepository;
import dev.regis.rest.services.interfaces.IService;

@Service
public class GeneticMaterialService extends AbstractService <GeneticMaterial, GeneticMaterialDTO> implements IService <GeneticMaterial, GeneticMaterialDTO>{

	@Autowired
	GeneticMaterialRepository repository;

	@Autowired
	ModelMapper mapper;

	public List<GeneticMaterialDTO> listAll() {
		return super.listAll(GeneticMaterialDTO.class);
	}

	public GeneticMaterialDTO findById(Long id) throws Exception {
		if (id == null || id < 1) {
            throw new IllegalArgumentException("ID inválido!");
        }
		return super.findById(id, GeneticMaterialDTO.class);
	}

	public Long create(GeneticMaterialDTO newGeneticMaterialDTO) throws Exception {
		//
		if(newGeneticMaterialDTO.getName() == null || 
			newGeneticMaterialDTO.getName().trim().isEmpty()){
			throw new Exception("Parâmetro nome inválido"); 
		}
		//
        if(newGeneticMaterialDTO.getSpecie() == null || newGeneticMaterialDTO.getSpecie().getId() == null){
            throw new Exception("Deve ser selecionada uma espécie");
        }

		try{
			return super.create(newGeneticMaterialDTO, GeneticMaterial.class);
		}catch(ConstraintViolationException | DataIntegrityViolationException e){
            throw new Exception("Dados informados violam restrições no BD.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Um erro ocorreu!");
        } 
	}

	public void deleteById(Long id) throws Exception{
		super.deleteById(id);
	}

	public Long update(GeneticMaterialDTO newGeneticMaterialDTO) throws Exception {
		//
        if (newGeneticMaterialDTO.getId() == null || newGeneticMaterialDTO.getId() < 1) {
            throw new IllegalArgumentException("ID inválido!");
        }
		//
		if(newGeneticMaterialDTO.getName() == null || 
			newGeneticMaterialDTO.getName().trim().isEmpty()){
			throw new Exception("Parâmetro nome inválido"); 
		}
		//
        if(newGeneticMaterialDTO.getSpecie() == null || newGeneticMaterialDTO.getSpecie().getId() == null){
            throw new Exception("Deve ser selecionada uma espécie");
        }
		try{
			return super.update(newGeneticMaterialDTO);
		}catch(ConstraintViolationException | DataIntegrityViolationException e){
            throw new Exception("Dados informados violam restrições no BD.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Um erro ocorreu!");
        } 
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
		Page<GeneticMaterial> geneticMaterials = repository.search(partName, pageable);
		return geneticMaterials.getContent().stream()
            .map(client -> mapper.map(client, GeneticMaterialDTO.class))
            .collect(Collectors.toList());
	}

}
