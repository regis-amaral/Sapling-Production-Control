package dev.regis.rest.models.person.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import dev.regis.rest.models.person.Client;
import dev.regis.rest.models.production.dtos.ExpeditionPlanDTO;

public class ClientDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private List<ExpeditionPlanDTO> listExpeditionPlans;

    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        id = client.getId();
        name = client.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ExpeditionPlanDTO> getListExpeditionPlans() {
        return listExpeditionPlans;
    }

    public void setListExpeditionPlans(List<ExpeditionPlanDTO> listExpeditionPlanDTOs) {
        this.listExpeditionPlans = listExpeditionPlanDTOs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Converte uma lista ORM para DTO 
     * @param speciesList
     * @return
     */
    public static List<ClientDTO> convertList(List<Client> speciesList){
        return speciesList.stream().map(ClientDTO::new).collect(Collectors.toList());
    }
}
