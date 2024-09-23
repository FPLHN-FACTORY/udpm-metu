package udpm.hn.metu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.entity.MenuDisplayOn;

@Repository
public interface MenuDisplayOnRepository extends JpaRepository<MenuDisplayOn, String> {

}
