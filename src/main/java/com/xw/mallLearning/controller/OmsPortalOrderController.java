package com.xw.mallLearning.controller;

import com.xw.mallLearning.common.api.CommonResult;
import com.xw.mallLearning.dto.OrderParam;
import com.xw.mallLearning.service.OmsPortalOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "订单管理")
@RestController("/order")
public class OmsPortalOrderController {
    @Resource
    private OmsPortalOrderService omsPortalOrderService;

    @ApiOperation("根据购物车信息生成订单")
    @PostMapping("/generateOrder")
    public CommonResult generateOrder(@RequestBody OrderParam orderParam) {
        return omsPortalOrderService.generateOrder(orderParam);
    }
}
