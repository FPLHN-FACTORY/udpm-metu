package udpm.hn.metu.core.manager.createmenu.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import udpm.hn.metu.core.common.base.ResponseObject;
import udpm.hn.metu.core.manager.createmenu.model.response.MWidgetResponse;
import udpm.hn.metu.core.manager.createmenu.repository.MCategoryRepository;
import udpm.hn.metu.core.manager.createmenu.repository.MWidgetRepository;
import udpm.hn.metu.core.manager.createmenu.service.MWidgetService;
import udpm.hn.metu.entity.Category;
import udpm.hn.metu.entity.Widget;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MWidgetServiceImpl implements MWidgetService {

    private final MWidgetRepository widgetRepository;

    private final MCategoryRepository categoryRepository;

    @Override
    public ResponseObject<List<MWidgetResponse>> getWidgets() {
        List<MWidgetResponse> responses = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();

        if (categories.isEmpty()) {
            return new ResponseObject<>(null, HttpStatus.BAD_REQUEST, "Lấy danh sách widget thất bại");

        }
        for (Category category : categories) {
            MWidgetResponse cateInfo = new MWidgetResponse();
            cateInfo.setCategoryId(category.getId());
            cateInfo.setCategoryCode(category.getCode());
            cateInfo.setCategoryName(category.getName());
            List<Widget> widgets = widgetRepository.findAllByCategory_IdAndDeleted(category.getId(), false);
            cateInfo.setWidgets(widgets);
            responses.add(cateInfo);
        } 
        return new ResponseObject<>(responses, HttpStatus.OK, "Lấy danh sách widget thành công");
    }
}
