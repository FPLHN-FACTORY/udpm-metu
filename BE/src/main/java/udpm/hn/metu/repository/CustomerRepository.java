package udpm.hn.metu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}