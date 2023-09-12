package dev.regis.rest.services;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

abstract class AbstractService<ObjectORM, ObjectDTO> {

    @Autowired
    private JpaRepository<ObjectORM, Long> repository;

    @Autowired
    ModelMapper mapper;

    /**
     * Insira como parâmetro a classe DTO de retorno. Ex.: UserDTO.class
     * O tratamento de conversão ORM para DTO deve ser feito no construtor das entidades.
     */
    protected List<ObjectDTO> listAll(Class<ObjectDTO> ObjectDTOClass) {
        return repository.findAll().stream()
            .map(batch -> mapper.map(batch, ObjectDTOClass))
            .collect(Collectors.toList());
    }

    /**
     * Informe como parâmetros o ID a ser pesquisado e classe DTO de retorno. Ex.:
     * UserDTO.class
     */
    protected ObjectDTO findById(Long id, Class<ObjectDTO> ObjectDTOClass) throws Exception {
        Optional<ObjectORM> optional = repository.findById(id);
        if (optional.isPresent()) {
            return mapper.map(optional.get(), ObjectDTOClass);
        } else {
            throw new Exception("Não encontrado");
        }
    }

    /**
     * Insira como parâmetro a classe da entidade. Ex.: User.class
     */
    protected Long create(ObjectDTO newObjectDTO, Class<ObjectORM> EntityClass) throws Exception {
        // TODO validar métodos e tratar exceções (campos obrigatórios, campos únicos, ...)
        if (EntityClass == null) {
            throw new Exception("Informe a classe da entidade a ser persistida. Ex. Specie.class");
        }
        try {
            ObjectORM entity = mapper.map(newObjectDTO, EntityClass);
            ObjectORM created = repository.save(entity);
            Method getIdMethod = created.getClass().getMethod("getId");
            return (Long) getIdMethod.invoke(created);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Um erro ocorreu!");
        }
    }

    protected void deleteById(Long id) {
        repository.deleteById(id);
    }

    protected Long update(ObjectDTO newObjectDTO) throws Exception {
        // TODO validar métodos e tratar exceções (campos obrigatórios, campos únicos, ...)
        Method getIdMethod = newObjectDTO.getClass().getMethod("getId");
        Long id = (Long) getIdMethod.invoke(newObjectDTO);
        System.out.println("buscando pelo id " + id);
        Optional<ObjectORM> optional = repository.findById(id);
        if (optional.isPresent()) {
            ObjectORM entity = optional.get();
            mapper.map(newObjectDTO, entity);
            repository.save(entity);
            getIdMethod = entity.getClass().getMethod("getId");
            return (Long) getIdMethod.invoke(entity);
        } else {
            throw new Exception("Um erro ocorreu!");
        }
    }

}
