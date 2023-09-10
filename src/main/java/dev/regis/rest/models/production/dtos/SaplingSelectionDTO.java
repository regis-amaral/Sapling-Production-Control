package dev.regis.rest.models.production.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import dev.regis.rest.models.production.Batch;
import lombok.Data;

@Data
public class SaplingSelectionDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long id;
    
    private Date selectionDate;

    private List<Batch> listBatchs;

    private int totalRootedSaplings;
    
}
