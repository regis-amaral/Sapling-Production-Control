package dev.regis.rest.models.production;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date stakingDate;

    @Column(nullable = false)
    private int amount;

    // @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // @JsonIgnoreProperties("listBatchs")  
    // private SaplingSelection saplingSelection;

    @ManyToOne(optional = false, fetch = FetchType.LAZY) 
    private GeneticMaterial geneticMaterial;

      
}
