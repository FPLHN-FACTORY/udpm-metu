package udpm.hn.metu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
