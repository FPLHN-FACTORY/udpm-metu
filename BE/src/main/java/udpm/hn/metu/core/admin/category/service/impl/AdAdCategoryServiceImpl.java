package udpm.hn.metu.core.admin.category.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import udpm.hn.metu.core.admin.category.repository.AdCategoryRepository;
import udpm.hn.metu.core.admin.category.service.AdCategoryService;
import udpm.hn.metu.core.common.base.ResponseObject;

@Service
@RequiredArgsConstructor
@Validated
@Slf4j
public class AdAdCategoryServiceImpl implements AdCategoryService {

    private final AdCategoryRepository categoryRepository;

    @Override
    public ResponseObject<?> allCategory() {

        return new ResponseObject<>(categoryRepository.getCombobox(), HttpStatus.OK,"Danh sách category!");
    }
}
