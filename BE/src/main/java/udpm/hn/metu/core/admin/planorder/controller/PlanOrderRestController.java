package udpm.hn.metu.core.admin.planorder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import udpm.hn.metu.core.admin.planorder.model.request.PlanOrderRequest;
import udpm.hn.metu.core.admin.planorder.service.PlanOrderService;
import udpm.hn.metu.infrastructure.constant.MappingConstant;
import udpm.hn.metu.utils.Helper;

@RestController
@RequestMapping(MappingConstant.API_ADMIN_PLAN_ORDER)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PlanOrderRestController {

    private final PlanOrderService planOrderService; // Đánh dấu là final

    @GetMapping
    public ResponseEntity<?> getAllPlanOrder() {
        return Helper.createResponseEntity(planOrderService.getAllPlanOrders());
    }
}