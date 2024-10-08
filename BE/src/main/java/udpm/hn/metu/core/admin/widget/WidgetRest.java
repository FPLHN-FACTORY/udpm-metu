package udpm.hn.metu.core.admin.widget;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udpm.hn.metu.core.admin.widget.dto.WidgetRequest;
import udpm.hn.metu.infrastructure.constant.MappingConstant;
import udpm.hn.metu.utils.Helper;

@RestController
@RequestMapping(MappingConstant.API_VERSION_PREFIX)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class WidgetRest {

    private final WidgetService widgetService;

    @GetMapping("view-widget")
    public ResponseEntity<?> viewWidget() {
        return ResponseEntity.ok(widgetService.allWidget());
    }

    @GetMapping("view-widget-only/{id}")
    public ResponseEntity<?> viewWidgetOnly(@PathVariable String id) {
        return Helper.createResponseEntity(widgetService.viewOnly(id));
    }

    @PostMapping("new-widget")
    public ResponseEntity<?> newWidget(@RequestBody WidgetRequest request) {
        return Helper.createResponseEntity(widgetService.addWidget(request));
    }

    @PutMapping("change-widget/{id}")
    public ResponseEntity<?> changeWidget(@PathVariable String id, @RequestBody WidgetRequest request) {
        return Helper.createResponseEntity(widgetService.changer(request,id));
    }

    @PutMapping("delete-widget/{id}")
    public ResponseEntity<?> deleteWidget(@PathVariable String id) {
        return Helper.createResponseEntity(widgetService.deleteWidget(id));
    }

}
