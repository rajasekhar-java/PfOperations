package com.organization.pfaccountservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.organization.pfaccountservice.entity.MonthlyContribution;
@Repository
public interface MonthlyContributionRepository extends JpaRepository<MonthlyContribution,Integer> {
}
