package dev.regis.rest.models.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class SeedlingSelection {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Date selectionDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "SeedlingSelection")
    private List<Batch> batchList;

    @Column(nullable = false)
    private int totalRootedSeedlings;

    public SeedlingSelection() {
    }

    public SeedlingSelection(int id, Date selectionDate, List<Batch> batchList, int totalRootedSeedlings) {
        this.id = id;
        this.selectionDate = selectionDate;
        this.batchList = batchList;
        this.totalRootedSeedlings = totalRootedSeedlings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
