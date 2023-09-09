package dev.regis.rest.models.production;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class SaplingSelection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date selectionDate;

    @OneToMany(mappedBy = "saplingSelection", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("saplingSelection")  
    private List<Batch> listBatchs;

    @Column(nullable = false)
    private int totalRootedSeedlings;

}
