package com.organization.pfofficeservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Document(collection = "monthlyContributions")
public class MonthlyContribution {
    @Id
    private String id; // MongoDB's default _id

    // @DBRef creates a reference to the PFAccount document.
    // When you fetch a MonthlyContribution, Spring Data MongoDB can automatically
    // resolve (fetch) the linked PFAccount if needed.
    @DBRef
    private PfAccount pfAccount; // Reference to the PFAccount document

    // You can also store the pfid directly for faster queries if you don't always need the full PFAccount object
    // For example, if you frequently query contributions by pfid without needing account details.
    // @Indexed // Index this for efficient lookups by pfid
    private String pfid; // Storing the pfid directly for convenience/query optimization

    private BigDecimal employeeContribution;
    private BigDecimal employerContribution;
    private BigDecimal totalAmount;

    @Indexed // Indexing date for efficient time-based queries
    private LocalDate contributionDate; // Using LocalDate for date only

    public MonthlyContribution() {
    }

    public MonthlyContribution(PfAccount pfAccount, String pfid, BigDecimal employeeContribution, BigDecimal employerContribution, LocalDate contributionDate) {
        this.pfAccount = pfAccount;
        this.pfid = pfid; // Set the direct pfid from the PFAccount
        this.employeeContribution = employeeContribution;
        this.employerContribution = employerContribution;
        this.totalAmount = employeeContribution.add(employerContribution); // Calculate total
        this.contributionDate = contributionDate;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PfAccount getPfAccount() {
        return pfAccount;
    }

    public void setPfAccount(PfAccount pfAccount) {
        this.pfAccount = pfAccount;
        if (pfAccount != null) {
            this.pfid = pfAccount.getPfid(); // Ensure direct pfid is consistent
        }
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
        updateTotalAmount();
    }

    public BigDecimal getEmployerContribution() {
        return employerContribution;
    }

    public void setEmployerContribution(BigDecimal employerContribution) {
        this.employerContribution = employerContribution;
        updateTotalAmount();
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    // Total amount is derived, so no direct setter, update via contributions
    private void updateTotalAmount() {
        if (this.employeeContribution != null && this.employerContribution != null) {
            this.totalAmount = this.employeeContribution.add(this.employerContribution);
        } else {
            this.totalAmount = BigDecimal.ZERO;
        }
    }

    public LocalDate getContributionDate() {
        return contributionDate;
    }

    public void setContributionDate(LocalDate contributionDate) {
        this.contributionDate = contributionDate;
    }

    @Override
    public String toString() {
        return "MonthlyContribution{" +
                "id='" + id + '\'' +
                ", pfid='" + pfid + '\'' + // Show the direct pfid
                ", employeeContribution=" + employeeContribution +
                ", employerContribution=" + employerContribution +
                ", totalAmount=" + totalAmount +
                ", contributionDate=" + contributionDate +
                '}';
    }
}
