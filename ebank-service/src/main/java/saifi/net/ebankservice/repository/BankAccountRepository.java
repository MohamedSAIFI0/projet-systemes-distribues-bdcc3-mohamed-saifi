package saifi.net.ebankservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saifi.net.ebankservice.entities.BankAccount;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

    List<BankAccount> findByCustomerId (Long id);
}
