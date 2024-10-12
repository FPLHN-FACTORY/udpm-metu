package udpm.hn.metu.core.admin.planorder.model.request;

import lombok.Getter;
import lombok.Setter;
import udpm.hn.metu.core.common.base.PageableRequest;
import udpm.hn.metu.infrastructure.constant.enums.Status;

@Getter
@Setter
public class PlanOrderRequest extends PageableRequest {

    private String email;

    private Status status;

}
