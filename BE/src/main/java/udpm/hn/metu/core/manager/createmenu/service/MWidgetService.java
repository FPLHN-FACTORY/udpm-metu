package udpm.hn.metu.core.manager.createmenu.service;

import udpm.hn.metu.core.common.base.ResponseObject;
import udpm.hn.metu.core.manager.createmenu.model.response.MWidgetResponse;

import java.util.List;

public interface MWidgetService {

    ResponseObject<List<MWidgetResponse>> getWidgets();

}
