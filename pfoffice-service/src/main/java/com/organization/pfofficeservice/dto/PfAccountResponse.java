package com.organization.pfofficeservice.dto;

import java.math.BigDecimal;

public class PfAccountResponse {
    private String id; // MongoDB's internal ID
    private String pfid;
    private String employeeId;
    private BigDecimal balance;

    public PfAccountResponse() {
    }

    public PfAccountResponse(String id, String pfid, String employeeId, BigDecimal balance) {
        this.id = id;
        this.pfid = pfid;
        this.employeeId = employeeId;
        this.balance = balance;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
