package dev.regis.rest.models.dtos;

import java.io.Serializable;
import java.time.Month;

public class ExpeditionPlanDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private int planned;
    private int realized;
    private Month month;
    private GeneticMaterialDTO geneticMaterial;
    
    public ExpeditionPlanDTO() {
    }

    public ExpeditionPlanDTO(int planned, int realized, Month month, GeneticMaterialDTO geneticMaterial) {
        this.planned = planned;
        this.realized = realized;
        this.month = month;
        this.geneticMaterial = geneticMaterial;
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

    public GeneticMaterialDTO getGeneticMaterial() {
        return geneticMaterial;
    }

    public void setGeneticMaterial(GeneticMaterialDTO geneticMaterial) {
        this.geneticMaterial = geneticMaterial;
    }

    
    
}
