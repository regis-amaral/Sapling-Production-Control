package dev.regis.rest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import dev.regis.rest.models.person.Client;
import dev.regis.rest.models.person.dto.ClientDTO;
import dev.regis.rest.repositories.ClientRepository;
import dev.regis.rest.services.interfaces.IService;

@Service
public class ClientService extends AbstractService <Client, ClientDTO> implements IService <Client, ClientDTO>{

        @Autowired
        ClientRepository repository;

        @Autowired
        ModelMapper mapper;
        
        @Override
        public List<ClientDTO> listAll() {
            return super.listAllObjects(ClientDTO.class);
        }

        @Override
        public ClientDTO findById(Long id) throws Exception {
            return super.findObjectById(id, ClientDTO.class);
        }

        @Override
        public Long create(ClientDTO objectDTO) throws Exception {
            return createNewObject(objectDTO, Client.class);
        }

        @Override
        public void deleteById(Long id) {
            super.deleteObjectById(id);
        }

        @Override
        public Long update(ClientDTO objectDTO) throws Exception {
            return super.updateObject(objectDTO);
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
