package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.projections.SaleReportProjection;
import com.devsuperior.dsmeta.projections.SaleSummaryProjection;
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

        LocalDate maxLocalDate = convertMaxDate(maxDate);
        LocalDate minLocalDate = convertMinDate(minDate, maxDate);

        Page<SaleReportProjection> result = repository.searchReport(minLocalDate, maxLocalDate, name, pageable);
        return result.map(x -> new SaleReportDTO(x));
    }

    @Transactional(readOnly = true)
    public List<SaleSummaryDTO> getSummary(String minDate, String maxDate) {

        LocalDate maxLocalDate = convertMaxDate(maxDate);
        LocalDate minLocalDate = convertMinDate(minDate, maxDate);

        List<SaleSummaryProjection> result = repository.searchSummary(minLocalDate, maxLocalDate);
        return result.stream().map(x -> new SaleSummaryDTO(x)).toList();
    }

    private LocalDate convertMaxDate(String maxDate) {
        if (maxDate == null) {
            return LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        } else {
            return LocalDate.parse(maxDate);
        }
    }

    private LocalDate convertMinDate(String minDate, String maxDate) {
        if (minDate == null) {
            return convertMaxDate(maxDate).minusYears(1L);
        } else {
            return LocalDate.parse(minDate);
        }
    }
}
