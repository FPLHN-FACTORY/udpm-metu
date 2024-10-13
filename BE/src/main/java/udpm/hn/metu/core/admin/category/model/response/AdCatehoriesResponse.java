package udpm.hn.metu.core.admin.category.model.response;

import org.springframework.beans.factory.annotation.Value;
import udpm.hn.metu.core.common.base.IsIdentify;

public interface AdCatehoriesResponse extends IsIdentify {

    @Value("#{target.id}")
    String getId();

    @Value("#{target.name}")
    String getName();

}


