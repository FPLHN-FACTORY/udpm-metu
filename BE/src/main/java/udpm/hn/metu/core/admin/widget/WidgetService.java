package udpm.hn.metu.core.admin.widget;

import jakarta.validation.Valid;
import udpm.hn.metu.core.admin.widget.dto.WidgetRequest;
import udpm.hn.metu.core.admin.widget.dto.WidgetResponse;
import udpm.hn.metu.core.common.base.ResponseObject;

import java.util.List;

public interface WidgetService {
    List<WidgetResponse> allWidget();

    ResponseObject<?> viewOnly(String id);

    ResponseObject<?> addWidget(@Valid WidgetRequest request);

    ResponseObject<?> changer(@Valid WidgetRequest request,String id);

    ResponseObject<?> deleteWidget(String id);
}
