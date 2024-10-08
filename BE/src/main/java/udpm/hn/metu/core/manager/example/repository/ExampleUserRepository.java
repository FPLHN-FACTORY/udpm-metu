package udpm.hn.metu.core.manager.example.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.core.manager.example.model.ExampleUserResponse;
import udpm.hn.metu.repository.UserRepository;

import java.util.List;

@Repository
public interface ExampleUserRepository extends UserRepository {

    @Query(value = """
                SELECT
                    id as id,
                    full_name as name
                from user
                LIMIT 1
            """, nativeQuery = true)
    List<ExampleUserResponse> getListUser();

}
