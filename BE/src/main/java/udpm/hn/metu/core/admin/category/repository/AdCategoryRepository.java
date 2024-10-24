package udpm.hn.metu.core.admin.category.repository;

import org.springframework.data.jpa.repository.Query;
import udpm.hn.metu.core.admin.category.model.response.AdCatehoriesResponse;
import udpm.hn.metu.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

public interface AdCategoryRepository extends CategoryRepository {

    @Query( value = """
            SELECT 
                cate.id,
                cate.name
            FROM 
                metu.category cate
        """,nativeQuery = true)
    List<AdCatehoriesResponse> getCombobox();

}
