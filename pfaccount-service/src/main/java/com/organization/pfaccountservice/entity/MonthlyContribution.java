package com.organization.pfaccountservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "monthlycontribution")
public class MonthlyContribution {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pfaccount_id") // Foreign key column
    @JsonBackReference
    private PfAccount pfaccount;
    private BigDecimal employeeContribution;
    private BigDecimal employerContribution;
    private BigDecimal totalContribution;
    private LocalDate contributionDate;
}
