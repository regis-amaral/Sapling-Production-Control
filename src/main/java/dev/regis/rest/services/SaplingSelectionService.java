package dev.regis.rest.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public List<SaplingSelectionDTO> listAll() {
        return super.listAll(SaplingSelectionDTO.class);
    }

    public SaplingSelectionDTO findById(Long id) throws Exception {
        if (id == null || id < 1) {
            throw new IllegalArgumentException("ID inválido!");
        }
        return super.findById(id, SaplingSelectionDTO.class);
    }

    @Transactional
    public Long create(SaplingSelectionDTO objectDTO) throws Exception {

        //
        if (objectDTO.getSelectionDate() == null) {
            throw new Exception("A data de seleção não pode ser nula");
        }
        Date today = new Date();
        if (objectDTO.getSelectionDate().after(today)) {
            throw new Exception("A data de seleção não pode ser maior que a data atual");
        }

        //
        if(objectDTO.getTotalRootedSaplings() <= 0){
            System.out.println(objectDTO.getTotalRootedSaplings());
            throw new Exception("O total de mudas selecionadas deve ser um número inteiro positivo");
        }

        //
        if(objectDTO.getListBatchs() == null || objectDTO.getListBatchs().size() == 0){
            throw new Exception("Deve ser selecionado ao menos um lote utilizado na seleção");
        }

        // Transação: salvar o SaplingSelection e atualizar os Batchs com o id retornado
        try {
            Long id = super.create(objectDTO, SaplingSelection.class);
            SaplingSelection saplingSelection = mapper.map(objectDTO, SaplingSelection.class);
            saplingSelection.setId(id);
            if(saplingSelection.getListBatchs() != null && saplingSelection.getListBatchs().size() > 0){
                for (Batch bt : saplingSelection.getListBatchs()) {
                    //
                    if(bt.getId() == null){
                        throw new RuntimeException("ID não informado para o lote selecionado.");
                    }
                    //
                    Optional<Batch> optional = batchRepository.findById(bt.getId());
                    if (optional.isPresent()) {
                        Batch batch = optional.get();
                        //verifica se o Batch já foi relacionado a outro SaplingSelection
                        if(batch.getSaplingSelection() != null){
                            throw new RuntimeException("Já foi cadastrada uma seleção para o lote " + batch.getCode());
                        }
                        batch.setSaplingSelection(saplingSelection);
                        batchRepository.save(batch);
                    } else {
                        throw new RuntimeException("Não existe o lote com o ID informado.");
                    }
                }
            }else{
                throw new RuntimeException("Nenhum lote informado na requisição");
            }
            return id;
        } catch(ConstraintViolationException | DataIntegrityViolationException e){
            throw new Exception("Dados informados violam restrições no BD.");
        } catch(RuntimeException e){
            // para abortar a transação...
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Um erro ocorreu ao salvar! Operação cancelada.");
        } 
        
    }

    @Transactional
    public void deleteById(Long id) throws Exception{
        
        Optional<SaplingSelection> optional = saplingSelectionRepository.findById(id);
        if (optional.isPresent()) {

            SaplingSelection saplingSelection = optional.get();

            // remove os relacionamentos existentes em lotes
            for (Batch batch : saplingSelection.getListBatchs()) {
                batch.setSaplingSelection(null);
                batchRepository.save(batch);
            }
        }
        try{
            super.deleteById(id);
        } catch (Exception e) {
            // para abortar a transação...
            throw new RuntimeException(e.getMessage());
        } 
    }

    @Transactional
    public Long update(SaplingSelectionDTO objectDTO) throws Exception {

        //
        if (objectDTO.getId() == null || objectDTO.getId() < 1) {
            throw new IllegalArgumentException("ID inválido!");
        }

        //
        if (objectDTO.getSelectionDate() == null) {
            throw new Exception("A data de seleção não pode ser nula");
        }
        Date today = new Date();
        if (objectDTO.getSelectionDate().after(today)) {
            throw new Exception("A data de seleção não pode ser maior que a data atual");
        }

        //
        if(objectDTO.getTotalRootedSaplings() <= 0){
            System.out.println(objectDTO.getTotalRootedSaplings());
            throw new Exception("O total de mudas selecionadas deve ser um número inteiro positivo");
        }

        //
        if(objectDTO.getListBatchs() == null || objectDTO.getListBatchs().size() == 0){
            throw new Exception("Deve ser selecionado ao menos um lote utilizado na seleção");
        }

        // Transação: salvar o SaplingSelection e atualizar os Batchs com o id retornado
        try {
            Optional<SaplingSelection> optional = saplingSelectionRepository.findById(objectDTO.getId());
            if (optional.isPresent()) {

                SaplingSelection saplingSelection = optional.get();

                // removo os relacionamentos existentes existentes em lotes
                if (saplingSelection.getListBatchs() != null && saplingSelection.getListBatchs().size() > 0) {
                    for (Batch batch : saplingSelection.getListBatchs()) {
                        batch.setSaplingSelection(null);
                        batchRepository.save(batch);
                    }
                }

                // crio os novos relacionamentos
                if (objectDTO.getListBatchs() != null && objectDTO.getListBatchs().size() > 0) {
                    for (Batch batch : objectDTO.getListBatchs()) {
                        //
                        if(batch.getId() == null){
                            throw new RuntimeException("ID não informado para o lote selecionado.");
                        }

                        Optional<Batch> opt = batchRepository.findById(batch.getId());
                        if (opt.isPresent()) {
                            Batch batch2 = opt.get();
                            //verifica se o Batch já foi relacionado a outro SaplingSelection
                            if(batch2.getSaplingSelection() != null){
                                throw new RuntimeException("Já foi cadastrada uma seleção para o lote " + batch.getCode());
                            }
                            batch2.setSaplingSelection(saplingSelection);
                            batchRepository.save(batch2);
                        } else {
                            // TODO tratar exceção
                            throw new RuntimeException("Não existe o lote com o ID informado.");
                        }
                    }
                }else{
                    throw new RuntimeException("Nenhum lote informado na requisição");
                }
                // salvo as alterações em SaplingSelection
                mapper.map(objectDTO, saplingSelection);
                
                super.update(objectDTO);
                // saplingSelectionRepository.save(saplingSelection);

                return saplingSelection.getId();
            } else {
                throw new RuntimeException("Não foi encontrada uma seleção com o ID informado.");
            }
        } catch(ConstraintViolationException | DataIntegrityViolationException e){
            throw new Exception("Dados informados violam restrições no BD.");
        } catch(RuntimeException e){
            // para abortar a transação...
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Um erro ocorreu ao salvar! Operação cancelada.");
        } 
    }

}
