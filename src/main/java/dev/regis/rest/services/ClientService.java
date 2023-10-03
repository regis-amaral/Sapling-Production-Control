package dev.regis.rest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import dev.regis.rest.models.person.Client;
import dev.regis.rest.models.person.dtos.ClientDTO;
import dev.regis.rest.models.production.GeneticMaterial;
import dev.regis.rest.repositories.ClientRepository;
import dev.regis.rest.services.interfaces.IService;

@Service
public class ClientService extends AbstractService <Client, ClientDTO> implements IService <Client, ClientDTO>{

        @Autowired
        ClientRepository repository;

        @Autowired
        ModelMapper mapper;
        
        public List<ClientDTO> listAll() {
            return super.listAll(ClientDTO.class);
        }

        public ClientDTO findById(Long id) throws Exception {
            if (id == null || id < 1) {
                throw new IllegalArgumentException("ID inválido!");
            }
            return super.findById(id, ClientDTO.class);
        }

        public Long create(ClientDTO objectDTO) throws Exception {
            //
            if(objectDTO.getName() == null || 
                objectDTO.getName().trim().isEmpty()){
                throw new Exception("Parâmetro nome inválido"); 
            }

            try{
                return super.create(objectDTO, Client.class);
            }catch(ConstraintViolationException | DataIntegrityViolationException e){
                throw new Exception("Dados informados violam restrições no BD.");
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("Um erro ocorreu!");
            } 
        }

        public void deleteById(Long id) throws Exception{
            super.deleteById(id);
        }
        
        public Long update(ClientDTO objectDTO) throws Exception {
            //
            if (objectDTO.getId() == null || objectDTO.getId() < 1) {
                throw new IllegalArgumentException("ID inválido!");
            }
            //
            if(objectDTO.getName() == null || 
                objectDTO.getName().trim().isEmpty()){
                throw new Exception("Parâmetro nome inválido"); 
            }
            return super.update(objectDTO);
        }

        public List<ClientDTO> search(String partName,
			Integer page,
			String orderBy,
			Integer itensPerPage,
			String direction) {
		Pageable pageable = PageRequest.of(page, itensPerPage, Sort.Direction.fromString(direction), orderBy);
		Page<Client> clients = repository.search(partName, pageable);
        
        return clients.getContent().stream()
            .map(client -> mapper.map(client, ClientDTO.class))
            .collect(Collectors.toList());
	}
}
