package dev.regis.rest.models.dtos.person;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import dev.regis.rest.models.dtos.production.ExpeditionPlanDTO;
import dev.regis.rest.models.entities.person.Client;

public class ClientDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private List<ExpeditionPlanDTO> listExpeditionPlan;

    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        id = client.getId();
        name = client.getName();
        listExpeditionPlan = ExpeditionPlanDTO.convert(client.getListExpeditionPlans());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ExpeditionPlanDTO> getListExpeditionPlan() {
        return listExpeditionPlan;
    }

    public void setListExpeditionPlan(List<ExpeditionPlanDTO> listExpeditionPlanDTOs) {
        this.listExpeditionPlan = listExpeditionPlanDTOs;
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
