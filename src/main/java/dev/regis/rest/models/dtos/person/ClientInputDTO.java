package dev.regis.rest.models.dtos.person;

import java.io.Serializable;
import java.util.List;

import dev.regis.rest.models.dtos.production.ExpeditionPlanDTO;
import dev.regis.rest.models.entities.person.Client;

public class ClientInputDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private List<ExpeditionPlanDTO> listExpeditionPlan;

    public ClientInputDTO() {
    }

    public ClientInputDTO(Client client) {
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
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExpeditionPlanDTO> getListExpeditionPlan() {
        return listExpeditionPlan;
    }

    public void setListExpeditionPlan(List<ExpeditionPlanDTO> listExpeditionPlanDTOs) {
        this.listExpeditionPlan = listExpeditionPlanDTOs;
    }

}
