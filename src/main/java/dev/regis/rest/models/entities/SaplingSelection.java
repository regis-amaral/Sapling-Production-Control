package dev.regis.rest.models.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class SaplingSelection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date selectionDate;

    @ManyToMany
    @JoinTable(name = "sapling_selection_has_batch", joinColumns = @JoinColumn(name = "sapling_selection_id"), inverseJoinColumns = @JoinColumn(name = "batch_id"))
    private List<Batch> batchList;

    @Column(nullable = false)
    private int totalRootedSeedlings;

    public SaplingSelection() {
    }

    public SaplingSelection(int id, Date selectionDate, List<Batch> batchList, int totalRootedSeedlings) {
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
