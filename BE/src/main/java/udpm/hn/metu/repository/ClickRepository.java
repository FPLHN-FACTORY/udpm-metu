package udpm.hn.metu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.entity.Click;

@Repository
public interface ClickRepository extends JpaRepository<Click, String> {
}
