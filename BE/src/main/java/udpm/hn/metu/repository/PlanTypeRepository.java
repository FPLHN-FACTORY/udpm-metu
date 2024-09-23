package udpm.hn.metu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.entity.PlanType;

@Repository
public interface PlanTypeRepository extends JpaRepository<PlanType, String> {
}
