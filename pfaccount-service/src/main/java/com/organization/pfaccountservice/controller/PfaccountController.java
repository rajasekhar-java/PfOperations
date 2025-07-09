package com.organization.pfaccountservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.organization.pfaccountservice.service.PfaccountService;
import com.organization.pfaccountservice.entity.PfAccount;
import com.organization.pfaccountservice.entity.MonthlyContribution;
import java.util.List;
@CrossOrigin("*")
@RequestMapping(value = "/pf")
@RestController
public class PfaccountController {
    @Autowired
    private PfaccountService pfaccountService;

    @PostMapping("/account")
    public PfAccount addPfAccount(@RequestBody PfAccount pfAccount){

        return pfaccountService.addPfaccount(pfAccount);
    }

    @PutMapping("/contribute")
    public Boolean UpdatePfcontributionByMonth(@RequestBody PfAccount pfAccount){
        return pfaccountService.UpdatePfcontributionByMonth(pfAccount);
    }

    @GetMapping("/balance/{id}")
    public PfAccount fetchPfAccountById(@PathVariable Long id){

        return pfaccountService.fetchPfAccountById(id);
    }

    @GetMapping("/balance")
    public List<PfAccount> fetchPfAccounts(){

        return pfaccountService.getAllContributions();
    }
}
