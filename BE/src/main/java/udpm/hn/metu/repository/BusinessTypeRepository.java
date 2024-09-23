package udpm.hn.metu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.entity.BusinessType;

@Repository
public interface BusinessTypeRepository extends JpaRepository<BusinessType, String> {
}
