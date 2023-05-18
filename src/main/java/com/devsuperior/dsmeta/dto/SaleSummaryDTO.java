package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleSummaryProjection;

public class SaleSummaryDTO {

    private String name;
    private Double sum;

    public SaleSummaryDTO() {
    }

    public SaleSummaryDTO(String name, Double sum) {
        this.name = name;
        this.sum = sum;
    }

    public SaleSummaryDTO(SaleSummaryProjection projection) {
        name = projection.getName();
        sum = projection.getSum();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
