package udpm.hn.metu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.entity.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, String> {
}
