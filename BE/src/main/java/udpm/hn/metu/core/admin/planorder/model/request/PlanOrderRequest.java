package udpm.hn.metu.core.admin.planorder.model.request;

import lombok.Getter;
import lombok.Setter;
import udpm.hn.metu.core.common.base.PageableRequest;

@Getter
@Setter
public class PlanOrderRequest extends PageableRequest {

    private String businessName;

    private String status;

}
