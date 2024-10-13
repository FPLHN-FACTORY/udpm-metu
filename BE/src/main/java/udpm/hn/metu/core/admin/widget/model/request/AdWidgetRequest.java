package udpm.hn.metu.core.admin.widget.model.request;

import lombok.Getter;
import lombok.Setter;
import udpm.hn.metu.core.common.base.PageableRequest;

@Getter
@Setter

public class AdWidgetRequest  extends PageableRequest {

    private String search;
}
