package udpm.hn.metu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.entity.Button;

@Repository
public interface ButtonRepository extends JpaRepository<Button, String> {
}
