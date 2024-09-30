package udpm.hn.metu.core.manager.createmenu.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import udpm.hn.metu.core.manager.createmenu.model.response.MWidgetResponse;
import udpm.hn.metu.entity.Widget;
import udpm.hn.metu.repository.WidgetRepository;

import java.util.List;

@Repository
public interface MWidgetRepository extends WidgetRepository {

    List<Widget> findAllByCategory_IdAndDeleted(String categoryId, Boolean deleted);

}
