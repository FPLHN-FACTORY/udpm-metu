package udpm.hn.metu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String> {
}
