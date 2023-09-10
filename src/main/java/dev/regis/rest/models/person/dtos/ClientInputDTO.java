package dev.regis.rest.models.person.dtos;

import java.io.Serializable;
import java.util.List;

import dev.regis.rest.models.person.Client;
import dev.regis.rest.models.production.dtos.ExpeditionPlanDTO;

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
