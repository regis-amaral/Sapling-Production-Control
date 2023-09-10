package dev.regis.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.regis.rest.models.production.SaplingSelection;
import dev.regis.rest.models.production.dtos.SaplingSelectionDTO;
import dev.regis.rest.repositories.SaplingSelectionRepository;
import dev.regis.rest.services.interfaces.IService;

@Service
public class SaplingSelectionService extends AbstractService <SaplingSelection, SaplingSelectionDTO> implements IService <SaplingSelection, SaplingSelectionDTO>{
    
        @Autowired
        SaplingSelectionRepository saplingSelectionRepository;

        @Override
        public List<SaplingSelectionDTO> listAll() {
            return super.listAllObjects(SaplingSelectionDTO.class);
        }

        @Override
        public SaplingSelectionDTO findById(Long id) throws Exception {
            return super.findObjectById(id, SaplingSelectionDTO.class);
        }

        @Override
        public Long create(SaplingSelectionDTO objectDTO) throws Exception {
            return super.createNewObject(objectDTO, SaplingSelection.class);
        }

        @Override
        public void deleteById(Long id) {
            super.deleteObjectById(id);
        }

        @Override
        public Long update(SaplingSelectionDTO objectDTO) throws Exception {
            return super.updateObject(objectDTO);
        }

}
