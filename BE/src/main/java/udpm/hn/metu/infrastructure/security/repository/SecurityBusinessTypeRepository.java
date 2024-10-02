package udpm.hn.metu.infrastructure.security.repository;

import udpm.hn.metu.entity.BusinessType;
import udpm.hn.metu.repository.BusinessTypeRepository;

import java.util.Optional;

public interface SecurityBusinessTypeRepository extends BusinessTypeRepository {

    Optional<BusinessType> findByCode(String code);

}
