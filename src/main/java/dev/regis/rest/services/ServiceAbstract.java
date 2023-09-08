package dev.regis.rest.services;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

abstract class ServiceAbstract<ORM, DTO> {

    @Autowired
    private JpaRepository<ORM, Long> repository;

    @Autowired
    ModelMapper mapper;

    protected List<DTO> listAllObjects(Class <DTO> DTOClass) {
        List<ORM> entityList = repository.findAll();
        return convertListORMtoDTO(entityList, DTOClass);
    }

    protected List<DTO> convertListORMtoDTO(List<ORM> entityList, Class <DTO> DTOClass) {
        List<DTO> listDTOs = new ArrayList<DTO>();
        entityList.forEach(entity -> {
            DTO geneticMaterialDTO = mapper.map(entity, DTOClass);
            listDTOs.add(geneticMaterialDTO);
        });
        return listDTOs;
    }

    protected DTO findObjectById(Long id, Class <DTO> DTOClass) throws Exception {
        Optional<ORM> optional = repository.findById(id);
        if (optional.isPresent()) {
            return mapper.map(optional.get(), DTOClass);
        } else {
            throw new Exception("Não encontrado");
        }
    }

    protected Long createNewObject(DTO newDTO, Class <ORM> EntityClass) throws Exception {
        if(EntityClass == null){
            throw new Exception("Informe a classe da entidade a ser persistida. Ex. Specie.class");
        }
        try {
            ORM entity = mapper.map(newDTO, EntityClass);
            ORM created = repository.save(entity);
            Method getIdMethod = created.getClass().getMethod("getId");
            return (Long) getIdMethod.invoke(created);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    protected void deleteObjectById(Long id) {
        repository.deleteById(id);
    }

    protected Long updateObject(DTO newDTO) throws Exception {
        Method getIdMethod = newDTO.getClass().getMethod("getId");
        Long id = (Long) getIdMethod.invoke(newDTO);
        Optional<ORM> optional = repository.findById(id);
        if (optional.isPresent()) {
            ORM entity = optional.get();
            mapper.map(newDTO, entity);
            repository.save(entity);
            getIdMethod = entity.getClass().getMethod("getId");
            return (Long) getIdMethod.invoke(entity);
        } else {
            throw new Exception("Um erro ocorreu");
        }
    }

}
