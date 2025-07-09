package com.organization.pfaccountservice.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pfaccount")
public class PfAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long employeeId;
    private BigDecimal pfBalance;

    @OneToMany(mappedBy = "pfaccount", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<MonthlyContribution> monthlyContributions;
}
