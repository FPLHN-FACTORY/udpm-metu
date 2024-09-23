package udpm.hn.metu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.entity.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, String> {
}
