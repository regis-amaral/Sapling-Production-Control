package dev.regis.rest.services.interfaces;

import java.util.List;

public interface IService <ObjectORM, ObjectDTO>{

    public List<ObjectDTO> listAll();
    
    public ObjectDTO findById(Long id) throws Exception;

    public Long create(ObjectDTO objectDTO) throws Exception;

    public void deleteById(Long id);

    public Long update(ObjectDTO objectDTO) throws Exception;
}
