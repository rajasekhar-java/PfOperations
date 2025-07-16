package com.organization.pfofficeservice.repository;


import com.organization.pfofficeservice.entity.PfAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PfaccountRepository extends MongoRepository<PfAccount, String> {
    Optional<PfAccount> findByPfid(String pfid);
    Optional<PfAccount> findByEmployeeId(String employeeId);
}
