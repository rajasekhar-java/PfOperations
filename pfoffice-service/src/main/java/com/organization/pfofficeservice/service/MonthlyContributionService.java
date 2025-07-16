package com.organization.pfofficeservice.service;

import com.organization.pfofficeservice.repository.MonthlyContributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional; // For multi-document transactions (MongoDB 4.0+)
import org.springframework.web.server.ResponseStatusException;
import com.organization.pfofficeservice.repository.PfaccountRepository;
import com.organization.pfofficeservice.dto.MonthlyContributionRequest;
import com.organization.pfofficeservice.dto.MonthlyContributionResponse;
import com.organization.pfofficeservice.entity.PfAccount;
import com.organization.pfofficeservice.entity.MonthlyContribution;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonthlyContributionService {
    private final MonthlyContributionRepository monthlyContributionRepository;
    private final PfaccountRepository pfAccountRepository; // Need to access PFAccounts for balance updates

    @Autowired
    public MonthlyContributionService(MonthlyContributionRepository monthlyContributionRepository, PfaccountRepository pfAccountRepository) {
        this.monthlyContributionRepository = monthlyContributionRepository;
        this.pfAccountRepository = pfAccountRepository;
    }

    // @Transactional is important here if you are using MongoDB 4.0+ and a replica set
    // to ensure both operations (saving contribution and updating balance) are atomic.
    // Without it, if the balance update fails, the contribution might still be saved.
    @Transactional
    public MonthlyContributionResponse addMonthlyContribution(MonthlyContributionRequest request) {
        PfAccount pfAccount = pfAccountRepository.findByPfid(request.getPfid())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PF Account not found with PFID: " + request.getPfid()));

        // Create the MonthlyContribution entity
        MonthlyContribution contribution = new MonthlyContribution(
                pfAccount,
                pfAccount.getPfid(), // Store pfid directly for easier querying
                request.getEmployeeContribution(),
                request.getEmployerContribution(),
                request.getContributionDate()
        );

        // Save the contribution
        MonthlyContribution savedContribution = monthlyContributionRepository.save(contribution);

        // Update the PFAccount balance
        BigDecimal totalContributionAmount = savedContribution.getTotalAmount();
        pfAccount.setTotalBalance(pfAccount.getTotalBalance().add(totalContributionAmount));
        pfAccountRepository.save(pfAccount); // Save the updated PFAccount

        return mapToMonthlyContributionResponse(savedContribution);
    }

    public MonthlyContributionResponse getMonthlyContributionById(String id) {
        MonthlyContribution contribution = monthlyContributionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Monthly Contribution not found with ID: " + id));
        return mapToMonthlyContributionResponse(contribution);
    }

    public List<MonthlyContributionResponse> getContributionsByPfid(String pfid) {
        // We don't need to resolve the @DBRef here if we only need contribution details
        // The direct 'pfid' field in MonthlyContribution is useful for this query.
        List<MonthlyContribution> contributions = monthlyContributionRepository.findByPfid(pfid);
        return contributions.stream()
                .map(this::mapToMonthlyContributionResponse)
                .collect(Collectors.toList());
    }

    public List<MonthlyContributionResponse> getContributionsByPfidAndDateRange(String pfid, LocalDate startDate, LocalDate endDate) {
        List<MonthlyContribution> contributions = monthlyContributionRepository.findByPfidAndContributionDateBetween(pfid, startDate, endDate);
        return contributions.stream()
                .map(this::mapToMonthlyContributionResponse)
                .collect(Collectors.toList());
    }

    // Helper method to convert MonthlyContribution entity to MonthlyContributionResponse DTO
    private MonthlyContributionResponse mapToMonthlyContributionResponse(MonthlyContribution contribution) {
        return new MonthlyContributionResponse(
                contribution.getId(),
                contribution.getPfid(),
                contribution.getEmployeeContribution(),
                contribution.getEmployerContribution(),
                contribution.getTotalAmount(),
                contribution.getContributionDate()
        );
    }
}
