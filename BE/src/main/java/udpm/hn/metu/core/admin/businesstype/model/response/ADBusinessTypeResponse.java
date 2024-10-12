package udpm.hn.metu.core.admin.businesstype.model.response;

import org.springframework.beans.factory.annotation.Value;
import udpm.hn.metu.core.common.base.IsIdentify;

public interface ADBusinessTypeResponse extends IsIdentify {


    String getStt();

    @Value("#{target.id}")
    String getId();

    @Value("#{target.code}")
    String getCode();

    @Value("#{target.name}")
    String getName();

    @Value("#{target.description}")
    String getDescription();

    @Value("#{target.create_by}")
    String getCreateBy();

}
