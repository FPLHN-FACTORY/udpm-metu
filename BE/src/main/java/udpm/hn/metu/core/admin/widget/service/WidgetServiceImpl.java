package udpm.hn.metu.core.admin.widget.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import udpm.hn.metu.core.admin.widget.WidgetService;
import udpm.hn.metu.core.admin.widget.dto.WidgetRequest;
import udpm.hn.metu.core.admin.widget.dto.WidgetResponse;
import udpm.hn.metu.core.common.base.ResponseObject;
import udpm.hn.metu.entity.Category;
import udpm.hn.metu.entity.Widget;
import udpm.hn.metu.infrastructure.constant.enums.WidgetType;
import udpm.hn.metu.repository.CategoryRepository;
import udpm.hn.metu.repository.WidgetRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Validated
@Slf4j
public class WidgetServiceImpl implements WidgetService {

    private final WidgetRepository widgetRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public List<WidgetResponse> allWidget() {
        List<WidgetResponse> list =widgetRepository.findAll().stream().map(WidgetResponse::new).collect(Collectors.toList());
        System.out.println(list.get(0).getName());
        return list;
    }

    @Override
    public ResponseObject<?> viewOnly(String id) {
        Widget widget = widgetRepository.findById(id).orElse(null);
        if (widget != null) {
            return ResponseObject.successForward(widget, "View only widget");
        }
        return ResponseObject.errorForward("Not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseObject<?> addWidget(@Valid WidgetRequest request) {
        Widget widget = convert(request);
        if (widget != null) {
            widgetRepository.save(widget);
            return ResponseObject.successForward(widget, "Add widget completed!");
        }
        return ResponseObject.errorForward("Not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseObject<?> changer(@Valid WidgetRequest request, String id) {
        Widget widget = widgetRepository.findById(id).orElse(null);
        Widget widget2 = convert(request);
        if (widget != null && widget2 != null) {
            widget.setCategory(widget2.getCategory());
            widget.setName(request.getName());
            widget.setDescription(request.getDescription());
            widget.setTop(request.getTop());
            widget.setCode(request.getCode());
            widget.setType(widget2.getType());
            widget.setIconUrl(request.getIconUrl());
            widgetRepository.save(widget);
            return ResponseObject.successForward(widget, "Update widget completed!");
        }
        return ResponseObject.errorForward("Not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseObject<?> deleteWidget(String id) {
        Widget widget = widgetRepository.findById(id).orElse(null);
        if (widget != null) {
            widget.setDeleted(true);
            widgetRepository.save(widget);
            return ResponseObject.successForward(widget, "Delete widget completed!");
        }
        return ResponseObject.errorForward("Not found", HttpStatus.NOT_FOUND);
    }

    public Widget convert(WidgetRequest request) {
        Category category = categoryRepository.findById(request.getCategory()).orElse(null);
        if (category != null) {
            Widget widget = new Widget();
            widget.setCategory(category);
            widget.setName(request.getName());
            widget.setDescription(request.getDescription());
            widget.setTop(request.getTop());
            widget.setCode(request.getCode());
            widget.setType(WidgetType.valueOf(request.getType()));
            widget.setIconUrl(request.getIconUrl());
            return widget;
        }
        return null;
    }
}
