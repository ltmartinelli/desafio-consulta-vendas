package com.devsuperior.dsmeta.projections;

import java.time.LocalDate;

public interface SaleReportProjection {
    Integer getId();
    LocalDate getDate();
    Double getAmount();
    String getName();
}
