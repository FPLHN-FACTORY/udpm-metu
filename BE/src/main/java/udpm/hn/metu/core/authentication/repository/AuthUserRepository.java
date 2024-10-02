package udpm.hn.metu.core.authentication.repository;

import org.springframework.stereotype.Repository;
import udpm.hn.metu.entity.User;
import udpm.hn.metu.repository.UserRepository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends UserRepository {

    Optional<User> findByCode(String code);

    Optional<User> findByEmail(String email);

}
