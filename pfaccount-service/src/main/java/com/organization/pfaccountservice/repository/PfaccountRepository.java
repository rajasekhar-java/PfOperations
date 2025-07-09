package com.organization.pfaccountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.organization.pfaccountservice.entity.PfAccount;
@Repository
public interface PfaccountRepository extends JpaRepository<PfAccount,Long> {
}
