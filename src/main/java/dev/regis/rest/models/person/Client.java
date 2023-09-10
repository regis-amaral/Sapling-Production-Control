package dev.regis.rest.models.person;

import java.util.List;

import dev.regis.rest.models.production.ExpeditionPlan;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
public class Client extends Person {

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<ExpeditionPlan> listExpeditionPlans;

    public Client() {
    }

    public List<ExpeditionPlan> getListExpeditionPlans() {
        return listExpeditionPlans;
    }

    public void setListExpeditionPlans(List<ExpeditionPlan> listExpeditionPlans) {
        this.listExpeditionPlans = listExpeditionPlans;
    }

}
