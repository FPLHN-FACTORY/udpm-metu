package udpm.hn.metu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.entity.Widget;

@Repository
public interface WidgetRepository extends JpaRepository<Widget, String> {
}
