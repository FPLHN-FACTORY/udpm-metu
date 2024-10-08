package udpm.hn.metu.core.admin.planorder.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import udpm.hn.metu.core.admin.planorder.model.request.PlanOrderRequest;
import udpm.hn.metu.core.admin.planorder.model.response.PlanOrderResponse;
import udpm.hn.metu.core.common.base.ResponseObject;

import java.util.List;

public interface PlanOrderService {

    ResponseObject<List<PlanOrderResponse>> getAllPlanOrders(PlanOrderRequest request);

}