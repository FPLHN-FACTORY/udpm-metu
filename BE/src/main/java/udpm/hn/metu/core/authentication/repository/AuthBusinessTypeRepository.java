package udpm.hn.metu.core.authentication.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.core.authentication.model.response.AuthBusinessTypeResponse;
import udpm.hn.metu.repository.BusinessTypeRepository;

import java.util.List;

@Repository
public interface AuthBusinessTypeRepository extends BusinessTypeRepository {

    @Query(value = """
                SELECT
                    id as id,
                    name as name
                FROM business_type
                WHERE code != 'Individual'
            """, nativeQuery = true)
    List<AuthBusinessTypeResponse> getBusinessType();

}
