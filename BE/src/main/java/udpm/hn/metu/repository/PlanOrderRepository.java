package udpm.hn.metu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.entity.PlanOrder;

@Repository
public interface PlanOrderRepository extends JpaRepository<PlanOrder, String> {
}
