package udpm.hn.metu.core.admin.widget.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import udpm.hn.metu.core.admin.widget.model.request.AdWidgetRequest;
import udpm.hn.metu.core.admin.widget.model.response.AdDetailWidgetResponse;
import udpm.hn.metu.core.admin.widget.model.response.AdWidgetResponse;
import udpm.hn.metu.core.common.base.ResponseObject;
import udpm.hn.metu.entity.Widget;
import udpm.hn.metu.repository.WidgetRepository;

import java.util.List;
import java.util.Optional;

public interface AdWidgetRepository extends WidgetRepository {

    @Query(value = """
             SELECT
                  ROW_NUMBER() OVER(ORDER BY widget.create_at DESC) AS stt,
                         widget.id,
                         category.name AS category,
                         widget.code,
                         widget.name,
                         widget.icon_url,
                         widget.type,
                         widget.description                         
                  FROM
                         metu.widget widget
                  INNER JOIN
                         metu.category category ON widget.category_id = category.id
                  WHERE
                         widget.deleted = 0 AND 
                        (:#{#request.search} IS NULL OR LOWER(widget.code) LIKE LOWER(CONCAT('%', :#{#request.search}, '%')) 
                            OR LOWER(widget.name) LIKE LOWER(CONCAT('%', :#{#request.search}, '%'))
                            OR LOWER(widget.type) LIKE LOWER(CONCAT('%', :#{#request.search}, '%'))
                            OR LOWER(category.name) LIKE LOWER(CONCAT('%', :#{#request.search}, '%')))
               """,
                        countQuery = """
             SELECT
                  COUNT(*)
                  FROM
                        metu.widget widget
                  INNER JOIN
                        metu.category category ON widget.category_id = category.id
                  WHERE
                        widget.deleted = 0 AND 
                        (:#{#request.search} IS NULL OR LOWER(widget.code) LIKE LOWER(CONCAT('%', :#{#request.search}, '%')) 
                            OR LOWER(widget.name) LIKE LOWER(CONCAT('%', :#{#request.search}, '%'))
                            OR LOWER(widget.type) LIKE LOWER(CONCAT('%', :#{#request.search}, '%'))
                            OR LOWER(category.name) LIKE LOWER(CONCAT('%', :#{#request.search}, '%')))
            """, nativeQuery = true)
    Page<AdWidgetResponse> getAllWidget(Pageable pageable, AdWidgetRequest request);

    @Query(value = """
                SELECT
                    widget.name,
                    widget.category_id AS categoryId,
                    widget.code,
                    widget.icon_url,
                    widget.type,
                    widget.description
                FROM metu.widget widget
                WHERE widget.id = :id
        """,nativeQuery = true)
    Optional<AdDetailWidgetResponse> getWidgetById(@Param("id") String id);
}
