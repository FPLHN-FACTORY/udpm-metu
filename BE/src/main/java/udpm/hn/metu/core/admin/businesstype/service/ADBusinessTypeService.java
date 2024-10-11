package udpm.hn.metu.core.admin.businesstype.service;

import jakarta.validation.Valid;
import udpm.hn.metu.core.admin.businesstype.model.request.ADBusinessTypeCreateRequest;
import udpm.hn.metu.core.admin.businesstype.model.request.ADBusinessTypeRequest;
import udpm.hn.metu.core.admin.businesstype.model.response.ADBusinessTypeResponse;
import udpm.hn.metu.core.common.base.ResponseObject;
import udpm.hn.metu.entity.BusinessType;

import java.util.List;

public interface ADBusinessTypeService {

    ResponseObject<?> getAllBusinessType(ADBusinessTypeRequest request);

    ResponseObject<?> getBusinessTypeById(String id);

    ResponseObject<?> addBusinessType(@Valid ADBusinessTypeCreateRequest request);

    ResponseObject<?> updateBusinessType(String id, @Valid ADBusinessTypeCreateRequest request);

    ResponseObject<?> deleteBusinessType(String id);

}
