package udpm.hn.metu.core.admin.planorder.service;

import jakarta.validation.Valid;
import udpm.hn.metu.core.admin.planorder.model.request.CreateUpdatePlanOrderRequest;
import udpm.hn.metu.core.admin.planorder.model.request.PlanOrderRequest;
import udpm.hn.metu.core.admin.planorder.model.response.PlanOrderResponse;
import udpm.hn.metu.core.common.base.ResponseObject;

import java.util.List;

public interface PlanOrderService {

    ResponseObject<List<PlanOrderResponse>> getAllPlanOrders();

    ResponseObject<?> createPlanOrder(@Valid CreateUpdatePlanOrderRequest request);

    ResponseObject<?> updatePlanOrder(String planOrderId, @Valid CreateUpdatePlanOrderRequest request);

    ResponseObject<?> changePlanOrderStatus(String planOrderId);

    ResponseObject<?> findPlanOrderById(String planOrderId);

}