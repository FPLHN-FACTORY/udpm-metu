package udpm.hn.metu.core.admin.business.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import udpm.hn.metu.core.common.base.PageableRequest;

@Getter
@Setter
@ToString
public class AdminBusinessRequest extends PageableRequest {

    private String name;

    private String code;


}
