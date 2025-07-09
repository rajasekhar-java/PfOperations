package com.organization.pfaccountservice.entity;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id") // Foreign key column
    private PfAccount pfAccount;
    private BigDecimal employeeContribution;
    private BigDecimal employerContribution;
    private BigDecimal totalContribution;
    private LocalDate contributionDate;
}
