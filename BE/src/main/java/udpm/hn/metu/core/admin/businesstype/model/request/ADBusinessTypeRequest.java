package udpm.hn.metu.core.admin.businesstype.model.request;

import lombok.Getter;
import lombok.Setter;
import udpm.hn.metu.core.common.base.PageableRequest;

@Getter
@Setter
public class ADBusinessTypeRequest extends PageableRequest {

    private String search;
}
