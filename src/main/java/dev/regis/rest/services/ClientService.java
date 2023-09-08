package dev.regis.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import dev.regis.rest.models.dtos.person.ClientDTO;
import dev.regis.rest.models.dtos.person.ClientInputDTO;
import dev.regis.rest.models.entities.person.Client;
import dev.regis.rest.repositories.ClientRepository;
import dev.regis.rest.services.interfaces.IService;

@Service
public class ClientService 
    extends AbstractService<Client, ClientInputDTO, ClientDTO> 
    implements IService <Client, ClientInputDTO, ClientDTO> {

        @Autowired
        ClientRepository clientRepository;

        @Override
        public List<ClientDTO> listAll() {
            return super.listAllObjects(ClientDTO.class);
        }

        @Override
        public ClientDTO findById(Long id) throws Exception {
            return super.findObjectById(id, ClientDTO.class);
        }

        @Override
        public Long create(ClientInputDTO objectDTO) throws Exception {
            return createNewObject(objectDTO, Client.class);
        }

        @Override
        public void deleteById(Long id) {
            super.deleteObjectById(id);
        }

        @Override
        public Long update(ClientInputDTO objectDTO) throws Exception {
            return super.updateObject(objectDTO);
        }

        public List<ClientDTO> search(String partName,
			Integer page,
			String orderBy,
			Integer itensPerPage,
			String direction) {
		Pageable pageable = PageRequest.of(page, itensPerPage, Sort.Direction.fromString(direction), orderBy);
		Page<Client> client = clientRepository.search(partName, pageable);
        return ClientDTO.convertList(client.getContent());
	}
}
