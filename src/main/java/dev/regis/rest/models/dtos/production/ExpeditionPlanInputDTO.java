package dev.regis.rest.models.dtos.production;

import java.io.Serializable;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

import dev.regis.rest.models.entities.person.Client;
import dev.regis.rest.models.entities.production.ExpeditionPlan;
import dev.regis.rest.models.entities.production.GeneticMaterial;

public class ExpeditionPlanInputDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private int planned;
    private int realized;
    private Month month;
    private GeneticMaterial geneticMaterial;
    private Client client;

    public ExpeditionPlanInputDTO() {
    }

    public ExpeditionPlanInputDTO(ExpeditionPlan expeditionPlan) {
        id = expeditionPlan.getId();
        planned = expeditionPlan.getPlanned();
        realized = expeditionPlan.getRealized();
        month = expeditionPlan.getMonth();
        geneticMaterial = expeditionPlan.getGeneticMaterial();
        client = expeditionPlan.getClient();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPlanned() {
        return planned;
    }

    public void setPlanned(int planned) {
        this.planned = planned;
    }

    public int getRealized() {
        return realized;
    }

    public void setRealized(int realized) {
        this.realized = realized;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public GeneticMaterial getGeneticMaterial() {
        return geneticMaterial;
    }

    public void setGeneticMaterial(GeneticMaterial geneticMaterial) {
        this.geneticMaterial = geneticMaterial;
    }   
    
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Converte uma lista ORM para DTO 
     * @param list
     * @return
     */
    public static List<ExpeditionPlanDTO> convert(List<ExpeditionPlan> list){
        return list.stream().map(ExpeditionPlanDTO::new).collect(Collectors.toList());
    }
    
}
