package com.organization.pfofficeservice.dto;

import java.math.BigDecimal;

public class PfAccountRequest {
    private String pfid;
    private String employeeId;
    private BigDecimal initialBalance; // Optional, for initial creation

    // Getters and Setters
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

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }
}
