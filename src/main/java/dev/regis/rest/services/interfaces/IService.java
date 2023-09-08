package dev.regis.rest.services.interfaces;

import java.util.List;

public interface IService <ORM, InDTO, OutDTO>{

    public List<OutDTO> listAll();
    
    public OutDTO findById(Long id) throws Exception;

    public Long create(InDTO objectDTO) throws Exception;

    public void deleteById(Long id);

    public Long update(InDTO objectDTO) throws Exception;
}
