package dev.regis.rest.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.regis.rest.models.production.Batch;
import dev.regis.rest.models.production.SaplingSelection;
import dev.regis.rest.models.production.dtos.SaplingSelectionDTO;
import dev.regis.rest.repositories.BatchRepository;
import dev.regis.rest.repositories.SaplingSelectionRepository;
import dev.regis.rest.services.interfaces.IService;
import jakarta.transaction.Transactional;

@Service
public class SaplingSelectionService extends AbstractService<SaplingSelection, SaplingSelectionDTO>
        implements IService<SaplingSelection, SaplingSelectionDTO> {

    @Autowired
    ModelMapper mapper;

    @Autowired
    SaplingSelectionRepository saplingSelectionRepository;

    @Autowired
    BatchRepository batchRepository;

    @Override
    public List<SaplingSelectionDTO> listAll() {
        return super.listAllObjects(SaplingSelectionDTO.class);
    }

    @Override
    public SaplingSelectionDTO findById(Long id) throws Exception {
        return super.findObjectById(id, SaplingSelectionDTO.class);
    }

    @Transactional
    public Long create(SaplingSelectionDTO objectDTO) throws Exception {
        try {
            Long id = super.createNewObject(objectDTO, SaplingSelection.class);
            SaplingSelection saplingSelection = mapper.map(objectDTO, SaplingSelection.class);
            saplingSelection.setId(id);
            if(objectDTO.getListBatchs() != null){
                for (Batch batchDTO : objectDTO.getListBatchs()) {
                    System.out.println("HEYYY " + batchDTO.getId());
                    Optional<Batch> optional = batchRepository.findById(batchDTO.getId());
                    if (optional.isPresent()) {
                        Batch batch = optional.get();
                        batch.setSaplingSelection(saplingSelection);
                        batchRepository.save(batch);
                    } else {
                        // TODO tratar exceção
                        // para abortar a transação tem que ser exeção não gerenciada
                        throw new RuntimeException("Não existe o lote com o ID informado.");
                    }
                }
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Um erro ocorreu ao salvar! Operação cancelada.");
    }

    @Override
    public void deleteById(Long id) {
        Optional<SaplingSelection> optional = saplingSelectionRepository.findById(id);
        if (optional.isPresent()) {

            SaplingSelection saplingSelection = optional.get();

            // removo os relacionamentos existentes em lotes
            for (Batch batch : saplingSelection.getListBatchs()) {
                System.out.println("removendo o lote " + batch.getId());
                batch.setSaplingSelection(null);
                batchRepository.save(batch);
            }
        }
        super.deleteObjectById(id);
    }

    @Transactional
    public Long update(SaplingSelectionDTO objectDTO) throws Exception {
        try {
            Optional<SaplingSelection> optional = saplingSelectionRepository.findById(objectDTO.getId());
            if (optional.isPresent()) {

                SaplingSelection saplingSelection = optional.get();

                // removo os relacionamentos existentes existentes em lotes
                if (saplingSelection.getListBatchs() != null) {
                    for (Batch batch : saplingSelection.getListBatchs()) {
                        System.out.println("removendo o lote " + batch.getId());
                        batch.setSaplingSelection(null);
                        batchRepository.save(batch);
                    }
                }

                // crio os novos relacionamentos
                if (objectDTO.getListBatchs() != null) {
                    for (Batch batch : objectDTO.getListBatchs()) {
                        Optional<Batch> opt = batchRepository.findById(batch.getId());
                        if (opt.isPresent()) {
                            Batch batch2 = opt.get();
                            batch2.setSaplingSelection(saplingSelection);
                            batchRepository.save(batch2);
                        } else {
                            // TODO tratar exceção
                            throw new RuntimeException("Não existe o lote com o ID informado.");
                        }
                    }
                }
                // salvo as alterações em SaplingSelection
                mapper.map(objectDTO, saplingSelection);
                saplingSelectionRepository.save(saplingSelection);

                return saplingSelection.getId();
            } else {
                throw new RuntimeException("Não existe o objeto com o ID informado.");
            }
        } catch (Exception e) { // TODO tratar exceções
            e.printStackTrace();
        }
        throw new RuntimeException("Um erro ocorreu ao salvar! Operação cancelada.");
    }

}
