package com.organization.pfaccountservice.service;
import com.organization.pfaccountservice.repository.PfaccountRepository;
import com.organization.pfaccountservice.repository.MonthlyContributionRepository;
import com.organization.pfaccountservice.entity.PfAccount;
import com.organization.pfaccountservice.entity.MonthlyContribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class PfaccountService {
    @Autowired
    private PfaccountRepository pfaccountRepository;

    @Autowired
    private MonthlyContributionRepository monthlyContributionRepository;

    public PfAccount addPfaccount(PfAccount pfAccount){

        return pfaccountRepository.save(pfAccount);
    }

    public Boolean UpdatePfcontributionByMonth(PfAccount pfAccount,MonthlyContribution monthlyContribution){
        Optional<PfAccount> pfAccountObj = Optional.of(pfaccountRepository.save(pfAccount));
        if(pfAccountObj.isPresent())
        {
            Optional<MonthlyContribution> monthlyContributionObj = Optional.of(monthlyContributionRepository.save(monthlyContribution));
            if(monthlyContributionObj.isPresent())
            {
                return true;
            }
        }

        return false;
    }

    public PfAccount fetchPfAccountById(Long id){

        return pfaccountRepository.findById(id).orElse(null);
    }

    public List<PfAccount> getAllContributions() {
        
        return pfaccountRepository.findAll();
    }



}
