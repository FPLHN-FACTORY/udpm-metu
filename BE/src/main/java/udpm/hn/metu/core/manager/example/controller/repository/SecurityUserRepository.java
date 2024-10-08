package udpm.hn.metu.core.manager.example.controller.repository;

import udpm.hn.metu.entity.User;
import udpm.hn.metu.repository.UserRepository;

import java.util.Optional;

public interface SecurityUserRepository extends UserRepository {

    Optional<User>findByEmail(String email);

}
