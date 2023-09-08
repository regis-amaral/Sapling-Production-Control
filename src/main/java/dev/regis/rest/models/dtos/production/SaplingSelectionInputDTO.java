package dev.regis.rest.models.dtos.production;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import dev.regis.rest.models.entities.production.Batch;
import dev.regis.rest.models.entities.production.SaplingSelection;

public class SaplingSelectionInputDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private Date selectionDate;
    private List<Batch> batchList;
    private int totalRootedSeedlings;
    
    public SaplingSelectionInputDTO() {
    }

    public SaplingSelectionInputDTO(SaplingSelection saplingSelection) {
        id = saplingSelection.getId();
        selectionDate = saplingSelection.getSelectionDate();
        batchList = saplingSelection.getBatchList();
        totalRootedSeedlings = saplingSelection.getTotalRootedSeedlings();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSelectionDate() {
        return selectionDate;
    }

    public void setSelectionDate(Date selectionDate) {
        this.selectionDate = selectionDate;
    }

    public List<Batch> getBatchList() {
        return batchList;
    }

    public void setBatchList(List<Batch> batchList) {
        this.batchList = batchList;
    }

    public int getTotalRootedSeedlings() {
        return totalRootedSeedlings;
    }

    public void setTotalRootedSeedlings(int totalRootedSeedlings) {
        this.totalRootedSeedlings = totalRootedSeedlings;
    }

}
