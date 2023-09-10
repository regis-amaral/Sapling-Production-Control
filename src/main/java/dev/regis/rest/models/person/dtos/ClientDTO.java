package dev.regis.rest.models.person.dtos;

import java.io.Serializable;
import java.util.List;

import dev.regis.rest.models.production.ExpeditionPlan;
import lombok.Data;

@Data
public class ClientDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private List<ExpeditionPlan> listExpeditionPlans;
    
}
