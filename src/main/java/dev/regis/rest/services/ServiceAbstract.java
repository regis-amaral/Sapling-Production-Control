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

    public List<DTO> listAll(Class <DTO> DTOClass) {
        List<ORM> entityList = repository.findAll();
        return convertList(entityList, DTOClass);
    }

    private List<DTO> convertList(List<ORM> entityList, Class <DTO> DTOClass) {
        List<DTO> listDTOs = new ArrayList<DTO>();
        entityList.forEach(entity -> {
            DTO geneticMaterialDTO = mapper.map(entity, DTOClass);
            listDTOs.add(geneticMaterialDTO);
        });
        return listDTOs;
    }

    public DTO findById(Long id, Class <DTO> DTOClass) throws Exception {
        Optional<ORM> optional = repository.findById(id);
        if (optional.isPresent()) {
            return mapper.map(optional.get(), DTOClass);
        } else {
            throw new Exception("Não encontrado");
        }
    }

    public Long create(DTO newDTO, Class <ORM> EntityClass) throws Exception {
        try {
            ORM entity = mapper.map(newDTO, EntityClass);
            ORM created = repository.save(entity);
            Method getIdMethod = created.getClass().getMethod("getId");
            return (Long) getIdMethod.invoke(created);
        } catch (Exception e) {
            throw new Exception("um erro ocorreu");
        }
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Long update(DTO newDTO) throws Exception {
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
