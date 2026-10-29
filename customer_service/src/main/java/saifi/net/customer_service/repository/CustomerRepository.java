package saifi.net.customer_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saifi.net.customer_service.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
