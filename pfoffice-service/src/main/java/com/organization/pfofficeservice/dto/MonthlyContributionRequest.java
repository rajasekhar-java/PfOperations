package com.organization.pfofficeservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MonthlyContributionRequest {
    private String pfid; // The PF ID to link this contribution to
    private BigDecimal employeeContribution;
    private BigDecimal employerContribution;
    private LocalDate contributionDate;

    // Getters and Setters
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

    public LocalDate getContributionDate() {
        return contributionDate;
    }

    public void setContributionDate(LocalDate contributionDate) {
        this.contributionDate = contributionDate;
    }
}
