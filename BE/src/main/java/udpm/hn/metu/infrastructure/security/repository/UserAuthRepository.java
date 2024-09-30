package udpm.hn.metu.infrastructure.security.repository;

import udpm.hn.metu.entity.User;
import udpm.hn.metu.repository.UserRepository;

import java.util.Optional;

public interface UserAuthRepository extends UserRepository {

    Optional<User>findByEmail(String email);

}
