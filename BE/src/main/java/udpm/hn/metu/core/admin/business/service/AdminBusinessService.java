package udpm.hn.metu.core.admin.business.service;

import jakarta.validation.Valid;
import udpm.hn.metu.core.admin.business.model.request.AdminBusinessRequest;
import udpm.hn.metu.core.common.base.ResponseObject;

public interface AdminBusinessService {

    ResponseObject<?> getAllBusiness(AdminBusinessRequest request);
    ResponseObject<?> updateBusiness(String businessId,@Valid AdminBusinessRequest request);
    ResponseObject<?> deleteBusiness(String id);
}
