package com.organization.pfofficeservice.controller;

import com.organization.pfofficeservice.service.MonthlyContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.organization.pfofficeservice.dto.MonthlyContributionRequest;
import com.organization.pfofficeservice.dto.MonthlyContributionResponse;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/contributions")
public class MonthlyContributionController {
    private final MonthlyContributionService monthlyContributionService;

    @Autowired
    public MonthlyContributionController(MonthlyContributionService monthlyContributionService) {
        this.monthlyContributionService = monthlyContributionService;
    }

    @PostMapping
    public ResponseEntity<MonthlyContributionResponse> addMonthlyContribution(@RequestBody MonthlyContributionRequest request) {
        MonthlyContributionResponse response = monthlyContributionService.addMonthlyContribution(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonthlyContributionResponse> getMonthlyContributionById(@PathVariable String id) {
        MonthlyContributionResponse response = monthlyContributionService.getMonthlyContributionById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-pfid/{pfid}")
    public ResponseEntity<List<MonthlyContributionResponse>> getContributionsByPfid(@PathVariable String pfid) {
        List<MonthlyContributionResponse> response = monthlyContributionService.getContributionsByPfid(pfid);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-pfid/{pfid}/date-range")
    public ResponseEntity<List<MonthlyContributionResponse>> getContributionsByPfidAndDateRange(
            @PathVariable String pfid,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<MonthlyContributionResponse> response = monthlyContributionService.getContributionsByPfidAndDateRange(pfid, startDate, endDate);
        return ResponseEntity.ok(response);
    }
}
