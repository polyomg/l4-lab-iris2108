package poly.edu.lab6java5.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.lab6java5.entity.Account;

public interface AccountDAO extends JpaRepository<Account, String> {
}
