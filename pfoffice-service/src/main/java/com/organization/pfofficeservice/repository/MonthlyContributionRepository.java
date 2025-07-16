package com.organization.pfofficeservice.repository;

import com.organization.pfofficeservice.entity.MonthlyContribution;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MonthlyContributionRepository extends MongoRepository<MonthlyContribution, String> {
    List<MonthlyContribution> findByPfid(String pfid);
    List<MonthlyContribution> findByPfidAndContributionDateBetween(String pfid, LocalDate startDate, LocalDate endDate);
    List<MonthlyContribution> findByContributionDate(LocalDate date);
}
