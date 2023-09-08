package dev.regis.rest.models.entities.production;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class SaplingSelection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date selectionDate;

    @OneToMany(mappedBy = "saplingSelection", fetch = FetchType.EAGER)
    private List<Batch> batchList;

    @Column(nullable = false)
    private int totalRootedSeedlings;

    public SaplingSelection() {
    }

    public SaplingSelection(Long id, Date selectionDate, List<Batch> listBatchs, int totalRootedSeedlings) {
        this.id = id;
        this.selectionDate = selectionDate;
        // this.listBatchs = listBatchs;
        this.totalRootedSeedlings = totalRootedSeedlings;
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

    public int getTotalRootedSeedlings() {
        return totalRootedSeedlings;
    }

    public void setTotalRootedSeedlings(int totalRootedSeedlings) {
        this.totalRootedSeedlings = totalRootedSeedlings;
    }

    public List<Batch> getBatchList() {
        return batchList;
    }

    public void setBatchList(List<Batch> batchList) {
        this.batchList = batchList;
    }

}
