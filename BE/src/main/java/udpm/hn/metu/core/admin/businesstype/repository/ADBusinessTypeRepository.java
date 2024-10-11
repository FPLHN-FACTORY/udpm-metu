package udpm.hn.metu.core.admin.businesstype.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.core.admin.businesstype.model.request.ADBusinessTypeRequest;
import udpm.hn.metu.core.admin.businesstype.model.response.ADBusinessTypeResponse;
import udpm.hn.metu.repository.BusinessTypeRepository;

import java.util.Optional;

@Repository
public interface ADBusinessTypeRepository extends BusinessTypeRepository {

    @Query(value = """
            SELECT
                    ROW_NUMBER() OVER(ORDER BY bu.create_at DESC) AS stt,
                                bu.id,
                                bu.code,
                                bu.name,
                                bu.description,
                                bu.create_by
                        FROM
                            metu.business_type bu
                      WHERE
                            bu.deleted = 0 AND
                            (:#{#request.search} IS NULL OR bu.code LIKE CONCAT('%', :#{#request.search}, '%') OR bu.name LIKE CONCAT('%', :#{#request.search}, '%'))                                                                                                                                                                               
            """,
            countQuery = """
                    SELECT
                            COUNT(*)
                                FROM
                                    metu.business_type bu
                                WHERE
                                    bu.deleted = 0 AND
                                    (:#{#request.search} IS NULL OR bu.code LIKE CONCAT('%', :#{#request.search}, '%') OR bu.name LIKE CONCAT('%', :#{#request.search}, '%'))
                    """, nativeQuery = true)
    Page<ADBusinessTypeResponse> getAllBusinessType(Pageable pageable, @Param("request") ADBusinessTypeRequest request);

    @Query(value = """
                SELECT
                    ROW_NUMBER() OVER(ORDER BY bu.create_at DESC) AS stt,
                                bu.id,
                                bu.code,
                                bu.name,
                                bu.description,
                                bu.create_by
                FROM
                    metu.business_type bu
                WHERE
                    bu.id = :id
            """, nativeQuery = true)
    Optional<ADBusinessTypeResponse> getBusinessTypeById(@Param("id") String id);

}
