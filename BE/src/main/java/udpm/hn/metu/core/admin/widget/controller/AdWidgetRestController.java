package udpm.hn.metu.core.admin.widget.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udpm.hn.metu.core.admin.widget.model.request.AdCUWidgetRequest;
import udpm.hn.metu.core.admin.widget.service.AdWidgetService;
import udpm.hn.metu.core.admin.widget.model.request.AdWidgetRequest;
import udpm.hn.metu.infrastructure.constant.MappingConstant;
import udpm.hn.metu.utils.Helper;

@RestController
@RequestMapping(MappingConstant.API_ADMIN_WIDGET)
@RequiredArgsConstructor
public class AdWidgetRestController {

    private final AdWidgetService adWidgetService;

    @GetMapping("")
    public ResponseEntity<?> getAllWidget(final AdWidgetRequest request){
        return Helper.createResponseEntity(adWidgetService.getAllWidget(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOnlyWidget(@PathVariable("id") String id){
        return Helper.createResponseEntity(adWidgetService.getOnlyWidget(id));
    }

    @PostMapping("")
    public ResponseEntity<?> addNewWidget(@Valid @RequestBody AdCUWidgetRequest request){
        return Helper.createResponseEntity(adWidgetService.createWidget(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> changeWidget(@Valid @PathVariable("id") String id,@RequestBody AdCUWidgetRequest request){
        return Helper.createResponseEntity(adWidgetService.updateWidget(id,request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> finishWidget(@PathVariable("id") String id){
        return Helper.createResponseEntity(adWidgetService.deletedWidget(id));
    }
}
