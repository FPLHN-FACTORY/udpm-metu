package udpm.hn.metu.core.admin.business.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.core.admin.business.model.request.AdminBusinessRequest;
import udpm.hn.metu.core.admin.business.model.request.AdminBusinessRespone;
import udpm.hn.metu.repository.BusinessRepository;

@Repository
public interface AdminBusinessRepository extends BusinessRepository {
    @Query(
            value = """

                    SELECT
                         ROW_NUMBER() OVER(ORDER BY bu.id DESC) AS stt,
                                      bu.id as id,
                                                                           bu.code as code,
                                                                           bu.name as name,
                                                                           bu.description as description,
                                                                           bu.create_by as getCreateBy,
                                                                           bu.deleted as getDeleted,
                                                                           bu.update_at as getUpdateAt,
                                                                           bu.type_id as getBusinessType,
                                                                           bu.create_at as getCreateAt ,
                                                                           bu.update_by as getUpdateBy,
                                                                           bu.status as status
                             FROM
                                 business bu
                           WHERE
                                 bu.deleted = 0 AND
                                 (:#{#request.name} IS NULL OR :#{#request.name} LIKE '' OR bu.name LIKE :#{#request.name}) AND
                                (:#{#request.code} IS NULL OR :#{#request.code} LIKE '' OR bu.code LIKE :#{#request.code})
                               
                     """,
            countQuery = """    
                        SELECT
                        COUNT(*)
                        FROM
                        business bu
                        WHERE
                        bu.deleted = 0 AND
                                 (:#{#request.name} IS NULL OR :#{#request.name} LIKE '' OR bu.name LIKE :#{#request.name}) AND
                                (:#{#request.code} IS NULL OR :#{#request.code} LIKE '' OR bu.code LIKE :#{#request.code})
                    """,
            nativeQuery = true
    )
    Page<AdminBusinessRespone> getAllBusiness(Pageable pageable, @Param("request") AdminBusinessRequest request);




}
