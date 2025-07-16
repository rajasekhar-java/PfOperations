package com.organization.pfofficeservice.service;
import com.organization.pfofficeservice.dto.PfAccountRequest;
import com.organization.pfofficeservice.repository.PfaccountRepository;
import com.organization.pfofficeservice.repository.MonthlyContributionRepository;
import com.organization.pfofficeservice.entity.PfAccount;
import com.organization.pfofficeservice.entity.MonthlyContribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.organization.pfofficeservice.dto.PfAccountResponse;
import com.organization.pfofficeservice.dto.PfAccountResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PfAccountService {
    private final PfaccountRepository pfAccountRepository;

    @Autowired
    public PfAccountService(PfaccountRepository pfAccountRepository) {
        this.pfAccountRepository = pfAccountRepository;
    }

    public PfAccountResponse createPFAccount(PfAccountRequest request) {
        // Check if PFID or Employee ID already exists to maintain uniqueness
        if (pfAccountRepository.findByPfid(request.getPfid()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "PF Account with PFID " + request.getPfid() + " already exists.");
        }
        if (pfAccountRepository.findByEmployeeId(request.getEmployeeId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "PF Account for Employee ID " + request.getEmployeeId() + " already exists.");
        }

        PfAccount pfAccount = new PfAccount();
        pfAccount.setPfid(request.getPfid());
        pfAccount.setEmployeeId(request.getEmployeeId());
        pfAccount.setTotalBalance(request.getInitialBalance() != null ? request.getInitialBalance() : BigDecimal.ZERO);

        PfAccount savedAccount = pfAccountRepository.save(pfAccount);
        return mapToPFAccountResponse(savedAccount);
    }

    public PfAccountResponse getPFAccountById(String id) {
        PfAccount pfAccount = pfAccountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PF Account not found with ID: " + id));
        return mapToPFAccountResponse(pfAccount);
    }

    public PfAccountResponse getPFAccountByPfid(String pfid) {
        PfAccount pfAccount = pfAccountRepository.findByPfid(pfid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PF Account not found with PFID: " + pfid));
        return mapToPFAccountResponse(pfAccount);
    }

    public PfAccountResponse getPFAccountByEmployeeId(String employeeId) {
        PfAccount pfAccount = pfAccountRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PF Account not found for Employee ID: " + employeeId));
        return mapToPFAccountResponse(pfAccount);
    }

    public List<PfAccountResponse> getAllPFAccounts() {
        return pfAccountRepository.findAll().stream()
                .map(this::mapToPFAccountResponse)
                .collect(Collectors.toList());
    }

    // Helper method to convert PFAccount entity to PFAccountResponse DTO
    private PfAccountResponse mapToPFAccountResponse(PfAccount pfAccount) {
        return new PfAccountResponse(
                pfAccount.getId(),
                pfAccount.getPfid(),
                pfAccount.getEmployeeId(),
                pfAccount.getTotalBalance()
        );
    }

    // Internal method for updating balance, used by MonthlyContributionService
    // Not exposed via controller directly, but could be if needed.
    public PfAccount updatePFAccountBalance(String pfid, BigDecimal amount) {
        PfAccount pfAccount = pfAccountRepository.findByPfid(pfid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PF Account not found with PFID: " + pfid));

        pfAccount.setTotalBalance(pfAccount.getTotalBalance().add(amount));
        return pfAccountRepository.save(pfAccount);
    }
}