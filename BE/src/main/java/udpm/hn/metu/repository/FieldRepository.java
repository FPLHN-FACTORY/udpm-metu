package udpm.hn.metu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.entity.Field;

@Repository
public interface FieldRepository extends JpaRepository<Field, String> {
}
