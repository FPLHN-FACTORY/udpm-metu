package udpm.hn.metu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.entity.Business;

@Repository
public interface BusinessRepository extends JpaRepository<Business, String> {
}
