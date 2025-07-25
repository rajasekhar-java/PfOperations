package com.organization.pfofficeservice.controller;
import com.organization.pfofficeservice.dto.PfAccountRequest;
import com.organization.pfofficeservice.dto.PfAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import com.organization.pfofficeservice.service.PfAccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PfAccountResponse> createPFAccount(@RequestBody PfAccountRequest request) {
        PfAccountResponse response = pfAccountService.createPFAccount(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<PfAccountResponse> getPFAccountById(@PathVariable String id) {
        PfAccountResponse response = pfAccountService.getPFAccountById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-pfid/{pfid}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<PfAccountResponse> getPFAccountByPfid(@PathVariable String pfid) {
        PfAccountResponse response = pfAccountService.getPFAccountByPfid(pfid);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-employeeid/{employeeId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<PfAccountResponse> getPFAccountByEmployeeId(@PathVariable String employeeId) {
        PfAccountResponse response = pfAccountService.getPFAccountByEmployeeId(employeeId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<PfAccountResponse>> getAllPFAccounts() {
        List<PfAccountResponse> response = pfAccountService.getAllPFAccounts();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/public/info")
    public String publicInfo() {
        return "Public info";
    }
    @GetMapping("/me")
    public String getUserInfo(@AuthenticationPrincipal Jwt jwt) {
        return "Hello, " + jwt.getClaims();
    }

}
