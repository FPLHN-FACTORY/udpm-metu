package udpm.hn.metu.core.admin.planorder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<?> getAllPlanOrder(@ModelAttribute PlanOrderRequest request) {
        return Helper.createResponseEntity(planOrderService.getAllPlanOrders(request));
    }
}