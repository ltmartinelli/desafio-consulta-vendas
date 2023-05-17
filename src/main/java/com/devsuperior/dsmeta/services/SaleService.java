package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.projections.SaleReportProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    public SaleMinDTO findById(Long id) {
        Optional<Sale> result = repository.findById(id);
        Sale entity = result.get();
        return new SaleMinDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<SaleReportDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {

        LocalDate minLocalDate;
        LocalDate maxLocalDate;

        if (maxDate == null) {
            maxLocalDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        } else {
            maxLocalDate = LocalDate.parse(maxDate);
        }

        if (minDate == null) {
            minLocalDate = maxLocalDate.minusYears(1L);
        } else {
            minLocalDate = LocalDate.parse(minDate);
        }

        Page<SaleReportProjection> result = repository.searchReport(minLocalDate, maxLocalDate, name, pageable);
        return result.map(x -> new SaleReportDTO(x));
    }


}
