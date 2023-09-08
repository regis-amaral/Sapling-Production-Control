package dev.regis.rest.models.production.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SaplingSelectionDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Date selectionDate;
    private List<BatchDTO> batchList;
    private int totalRootedSeedlings;
    
    public SaplingSelectionDTO() {
    }

    public SaplingSelectionDTO(Date selectionDate, List<BatchDTO> batchList, int totalRootedSeedlings) {
        this.selectionDate = selectionDate;
        this.batchList = batchList;
        this.totalRootedSeedlings = totalRootedSeedlings;
    }

    public Date getSelectionDate() {
        return selectionDate;
    }

    public void setSelectionDate(Date selectionDate) {
        this.selectionDate = selectionDate;
    }

    public List<BatchDTO> getBatchList() {
        return batchList;
    }

    public void setBatchList(List<BatchDTO> batchList) {
        this.batchList = batchList;
    }

    public int getTotalRootedSeedlings() {
        return totalRootedSeedlings;
    }

    public void setTotalRootedSeedlings(int totalRootedSeedlings) {
        this.totalRootedSeedlings = totalRootedSeedlings;
    }

    
}
