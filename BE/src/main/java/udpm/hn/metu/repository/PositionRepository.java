package udpm.hn.metu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.entity.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, String> {
}
