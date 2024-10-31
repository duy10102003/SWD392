package com.swd392.group2.hms_outpatient_gr2.repository;

import com.swd392.group2.hms_outpatient_gr2.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsername(String username);
}
