package udpm.hn.metu.core.admin.widget.model.response;

import org.springframework.beans.factory.annotation.Value;
import udpm.hn.metu.core.common.base.IsIdentify;

public interface AdWidgetResponse extends IsIdentify {

    String getStt();

    @Value("#{target.id}")
    String getId();

    @Value("#{target.category}")
    String getCategoryName();

    @Value("#{target.code}")
    String getCode();

    @Value("#{target.name}")
    String getName();

    @Value("#{target.iconUrl}")
    String getIconUrl();

    @Value("#{target.type}")
    String getType();

    @Value("#{target.description}")
    String getDescription();

}
