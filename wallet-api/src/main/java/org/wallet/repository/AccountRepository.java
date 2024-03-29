package org.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wallet.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
