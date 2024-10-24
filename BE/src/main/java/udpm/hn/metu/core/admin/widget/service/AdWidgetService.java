package udpm.hn.metu.core.admin.widget.service;

import jakarta.validation.Valid;
import udpm.hn.metu.core.admin.widget.model.request.AdCUWidgetRequest;
import udpm.hn.metu.core.common.base.ResponseObject;

public interface AdWidgetService {
    ResponseObject<?> getAllWidget(udpm.hn.metu.core.admin.widget.model.request.AdWidgetRequest request);


    ResponseObject<?> getOnlyWidget(String id);

    ResponseObject<?> createWidget(@Valid AdCUWidgetRequest request);

    ResponseObject<?> updateWidget(String id, AdCUWidgetRequest request);

    ResponseObject<?> deletedWidget(String id);
}
