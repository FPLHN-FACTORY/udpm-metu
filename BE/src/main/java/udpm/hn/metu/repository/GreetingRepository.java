package udpm.hn.metu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.entity.Greeting;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, String> {
}
