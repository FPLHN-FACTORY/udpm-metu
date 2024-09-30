package udpm.hn.metu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udpm.hn.metu.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
}
