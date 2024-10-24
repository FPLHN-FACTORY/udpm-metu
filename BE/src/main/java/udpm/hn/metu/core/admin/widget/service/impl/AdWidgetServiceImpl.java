package udpm.hn.metu.core.admin.widget.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import udpm.hn.metu.core.admin.category.repository.AdCategoryRepository;
import udpm.hn.metu.core.admin.widget.model.request.AdCUWidgetRequest;
import udpm.hn.metu.core.admin.widget.repository.AdWidgetRepository;
import udpm.hn.metu.core.admin.widget.service.AdWidgetService;
import udpm.hn.metu.core.common.base.PageableObject;
import udpm.hn.metu.core.common.base.ResponseObject;
import udpm.hn.metu.entity.Category;
import udpm.hn.metu.entity.Widget;

@Service
@RequiredArgsConstructor
@Validated
@Slf4j
public class AdWidgetServiceImpl implements AdWidgetService {

    private final AdWidgetRepository adWidgetRepository;

    private final AdCategoryRepository categoryRepository;

    @Override
    public ResponseObject<?> getAllWidget(udpm.hn.metu.core.admin.widget.model.request.AdWidgetRequest request) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize());
        return new ResponseObject<>(
                PageableObject.of(adWidgetRepository.getAllWidget(pageable, request)),
                HttpStatus.OK,
                "Lấy danh sách widget thành công!"
        );
    }

    @Override
    public ResponseObject<?> getOnlyWidget(String id) {
        return adWidgetRepository.getWidgetById(id).map(
                widget-> new ResponseObject<>(widget,HttpStatus.OK,"Lấy widget thành công!"))
                .orElseGet(()->new ResponseObject<>(null,HttpStatus.NOT_FOUND,id+" không tồn tại"));
    }

    @Override
    public ResponseObject<?> createWidget(AdCUWidgetRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        if (category!=null){
            Widget widget = new Widget(request);
            widget.setCategory(category);
            adWidgetRepository.save(widget);
            return new ResponseObject<>(widget,HttpStatus.OK,"Thêm widget thành công!");
        }
        return new ResponseObject<>(null,HttpStatus.NOT_FOUND,"Thêm widget thất bại!");
    }

    @Override
    public ResponseObject<?> updateWidget(String id, AdCUWidgetRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        Widget widget = adWidgetRepository.findById(id).orElse(null);
        if (widget!=null&& category!=null){
            widget.updateFromRequest(request);
            widget.setCategory(category);
            adWidgetRepository.save(widget);
            return new ResponseObject<>(widget,HttpStatus.OK,"Chỉnh sửa widget thành công!");
        }
        return new ResponseObject<>(null,HttpStatus.NOT_FOUND,id+" hoặc category "+request.getCategoryId()+" không tồn tại");
    }

    @Override
    public ResponseObject<?> deletedWidget(String id) {
        Widget widget = adWidgetRepository.findById(id).orElse(null);
        if (widget!=null){
            adWidgetRepository.delete(widget);
            return new ResponseObject<>(id,HttpStatus.OK,"Xóa thành công widget!");
        }
        return new ResponseObject<>(null,HttpStatus.NOT_FOUND,id+" không tồn tại");
    }

}
