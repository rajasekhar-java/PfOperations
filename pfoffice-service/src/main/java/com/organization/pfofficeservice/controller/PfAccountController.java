package com.organization.pfofficeservice.controller;
import com.organization.pfofficeservice.dto.PfAccountRequest;
import com.organization.pfofficeservice.dto.PfAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.organization.pfofficeservice.service.PfAccountService;
import com.organization.pfofficeservice.entity.PfAccount;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/pfaccounts")
public class PfAccountController {

    private final PfAccountService pfAccountService;

    @Autowired
    public PfAccountController(PfAccountService pfAccountService) {
        this.pfAccountService = pfAccountService;
    }

    @PostMapping
    public ResponseEntity<PfAccountResponse> createPFAccount(@RequestBody PfAccountRequest request) {
        PfAccountResponse response = pfAccountService.createPFAccount(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PfAccountResponse> getPFAccountById(@PathVariable String id) {
        PfAccountResponse response = pfAccountService.getPFAccountById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-pfid/{pfid}")
    public ResponseEntity<PfAccountResponse> getPFAccountByPfid(@PathVariable String pfid) {
        PfAccountResponse response = pfAccountService.getPFAccountByPfid(pfid);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-employeeid/{employeeId}")
    public ResponseEntity<PfAccountResponse> getPFAccountByEmployeeId(@PathVariable String employeeId) {
        PfAccountResponse response = pfAccountService.getPFAccountByEmployeeId(employeeId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PfAccountResponse>> getAllPFAccounts() {
        List<PfAccountResponse> response = pfAccountService.getAllPFAccounts();
        return ResponseEntity.ok(response);
    }

}
