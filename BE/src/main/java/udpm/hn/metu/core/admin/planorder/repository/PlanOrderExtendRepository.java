package udpm.hn.metu.core.admin.planorder.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.core.admin.planorder.model.request.PlanOrderRequest;
import udpm.hn.metu.core.admin.planorder.model.response.PlanOrderResponse;
import udpm.hn.metu.repository.PlanOrderRepository;

@Repository
public interface PlanOrderExtendRepository extends PlanOrderRepository {

    @Query(
            value = """
        SELECT 
            po.email AS email,
            po.createAt AS createAt,
            po.expirationDate AS expirationDate,
            po.price AS price,
            po.quantity AS quantity,
            po.status AS status,
            po.business AS business,
            po.plan AS plan
        FROM PlanOrder po
        WHERE 
            (:#{#request.email} IS NULL OR po.email LIKE CONCAT('%',:#{#request.email},'%'))
            AND (:#{#request.status} IS NULL OR po.status = :#{#request.status})
    """,
            countQuery = """
        SELECT COUNT(po) FROM PlanOrder po
        WHERE 
            (:#{#request.email} IS NULL OR po.email LIKE CONCAT('%',:#{#request.email},'%'))
            AND (:#{#request.status} IS NULL OR po.status = :#{#request.status})
    """,
            nativeQuery = false
    )
    Page<Object[]> getAllPlanOrders(Pageable pageable, PlanOrderRequest request);

}

