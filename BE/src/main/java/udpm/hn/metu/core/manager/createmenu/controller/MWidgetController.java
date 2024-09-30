package udpm.hn.metu.core.manager.createmenu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udpm.hn.metu.core.manager.createmenu.service.MWidgetService;
import udpm.hn.metu.infrastructure.constant.MappingConstant;
import udpm.hn.metu.utils.Helper;

@RestController
@RequestMapping(MappingConstant.API_MANAGER_CREATE_WIDGET)
@RequiredArgsConstructor
public class MWidgetController {

    private final MWidgetService widgetService;

    @GetMapping("/widget")
    public ResponseEntity<?> getWidget() {
        return Helper.createResponseEntity(widgetService.getWidgets());
    }

}
