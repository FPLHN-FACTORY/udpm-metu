package udpm.hn.metu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.entity.ButtonForm;

@Repository
public interface ButtonFormRepository extends JpaRepository<ButtonForm, String> {
}
