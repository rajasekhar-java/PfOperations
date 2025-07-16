package com.organization.pfofficeservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MonthlyContributionResponse {
    private String id;
    private String pfid;
    private BigDecimal employeeContribution;
    private BigDecimal employerContribution;
    private BigDecimal totalAmount;
    private LocalDate contributionDate;

    public MonthlyContributionResponse() {
    }

    public MonthlyContributionResponse(String id, String pfid, BigDecimal employeeContribution, BigDecimal employerContribution, BigDecimal totalAmount, LocalDate contributionDate) {
        this.id = id;
        this.pfid = pfid;
        this.employeeContribution = employeeContribution;
        this.employerContribution = employerContribution;
        this.totalAmount = totalAmount;
        this.contributionDate = contributionDate;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPfid() {
        return pfid;
    }

    public void setPfid(String pfid) {
        this.pfid = pfid;
    }

    public BigDecimal getEmployeeContribution() {
        return employeeContribution;
    }

    public void setEmployeeContribution(BigDecimal employeeContribution) {
        this.employeeContribution = employeeContribution;
    }

    public BigDecimal getEmployerContribution() {
        return employerContribution;
    }

    public void setEmployerContribution(BigDecimal employerContribution) {
        this.employerContribution = employerContribution;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getContributionDate() {
        return contributionDate;
    }

    public void setContributionDate(LocalDate contributionDate) {
        this.contributionDate = contributionDate;
    }
}
