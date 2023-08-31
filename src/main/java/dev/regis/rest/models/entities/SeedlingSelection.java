package dev.regis.rest.models.entities;

import java.util.Date;
import java.util.List;

public class SeedlingSelection {
    private int id;	
    private Date selectionDate;
    private List<Batch> batchList;	
    private int totalRootedSeedlings;	
}
