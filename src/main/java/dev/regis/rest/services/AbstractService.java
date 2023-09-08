package dev.regis.rest.services;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

abstract class AbstractService<ORM, InputDTO, OutputDTO> {

    @Autowired
    private JpaRepository<ORM, Long> repository;

    @Autowired
    ModelMapper mapper;

    /**
     * Insira como parâmetro a classe DTO de saída. Ex.: UserDTO.class
     * @param DTOClass
     * @return
     */
    protected List<OutputDTO> listAllObjects(Class <OutputDTO> DTOClass) {
        List<ORM> entityList = repository.findAll();
        return convertListORMtoDTO(entityList, DTOClass);
    }

    protected List<OutputDTO> convertListORMtoDTO(List<ORM> entityList, Class <OutputDTO> DTOClass) {
        List<OutputDTO> listDTOs = new ArrayList<OutputDTO>();
        entityList.forEach(entity -> {
            OutputDTO geneticMaterialDTO = mapper.map(entity, DTOClass);
            listDTOs.add(geneticMaterialDTO);
        });
        return listDTOs;
    }

    /**
     * Informe como parâmetros o ID a ser pesquisado e classe DTO de saída. Ex.: UserDTO.class
     * @param id
     * @param DTOClass
     * @return
     * @throws Exception
     */
    protected OutputDTO findObjectById(Long id, Class <OutputDTO> DTOClass) throws Exception {
        Optional<ORM> optional = repository.findById(id);
        if (optional.isPresent()) {
            return mapper.map(optional.get(), DTOClass);
        } else {
            throw new Exception("Não encontrado");
        }
    }

    /**
     * Insira como parâmetro a classe da entidade. Ex.: User.class
     * @param newDTO
     * @param EntityClass
     * @return
     * @throws Exception
     */
    protected Long createNewObject(InputDTO newDTO, Class <ORM> EntityClass) throws Exception {
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

    protected Long updateObject(InputDTO newDTO) throws Exception {
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
