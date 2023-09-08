package dev.regis.rest.services.interfaces;

import java.util.List;

public interface IService <ORM, DTO>{

    public List<DTO> listAll();
    
    public DTO findById(Long id) throws Exception;

    public Long create(DTO objectDTO) throws Exception;

    public void deleteById(Long id);

    public Long update(DTO objectDTO) throws Exception;
}
