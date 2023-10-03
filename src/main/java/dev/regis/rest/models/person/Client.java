package dev.regis.rest.models.person;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import dev.regis.rest.models.production.ExpeditionPlan;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
public class Client extends Person {

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("client")
    private List<ExpeditionPlan> listExpeditionPlans;

}
