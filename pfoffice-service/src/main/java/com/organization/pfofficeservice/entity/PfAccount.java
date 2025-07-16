package com.organization.pfofficeservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;


@Document(collection = "pfaccounts")
public class PfAccount {
    @Id
    private String id; // MongoDB's default _id, can be a String or ObjectId

    @Indexed(unique = true) // Ensures pfid is unique
    private String pfid; // Your custom PF ID

    @Indexed(unique = true) // Ensures employeeId is unique, assuming one PF account per employee
    private String employeeId;

    private BigDecimal totalBalance; // Use BigDecimal for currency to avoid precision issues

    public PfAccount() {
        this.totalBalance = BigDecimal.ZERO; // Initialize balance to zero
    }

    public PfAccount(String pfid, String employeeId, BigDecimal totalBalance) {
        this.pfid = pfid;
        this.employeeId = employeeId;
        this.totalBalance = totalBalance;
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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }

    @Override
    public String toString() {
        return "PfAccount{" +
                "id='" + id + '\'' +
                ", pfid='" + pfid + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", totalBalance=" + totalBalance +
                '}';
    }
}
