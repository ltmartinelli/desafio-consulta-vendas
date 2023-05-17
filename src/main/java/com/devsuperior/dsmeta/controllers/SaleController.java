package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    @Autowired
    private SaleService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
        SaleMinDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/report")
    public ResponseEntity<Page<SaleReportDTO>> getReport(
            @RequestParam(name = "minDate", required = false) String minDate,
            @RequestParam(name = "maxDate", required = false) String maxDate,
            @RequestParam(name = "name", defaultValue = "", required = false) String name,
			Pageable pageable) {
		return ResponseEntity.ok(service.getReport(minDate, maxDate, name, pageable));
    }

    @GetMapping(value = "/summary")
    public ResponseEntity<?> getSummary() {
        // TODO
        return null;
    }
}
