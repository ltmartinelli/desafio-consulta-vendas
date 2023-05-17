package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleReportProjection;

import java.time.LocalDate;

public class SaleReportDTO {

    private Integer id;
    private LocalDate date;
    private Double amount;
    private String name;

    public SaleReportDTO() {
    }

    public SaleReportDTO(Integer id, LocalDate date, Double amount, String name) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.name = name;
    }

    public SaleReportDTO(SaleReportProjection projection) {
        id = projection.getId();
        date = projection.getDate();
        amount = projection.getAmount();
        name = projection.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
