package com.example.longxin.Controller;

import com.example.longxin.Service.Index_Service;
import com.example.longxin.pojo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 制冷
 * @date 2023/5/8 19:19
 * @description Index
 * 该接口用于编写转化率、流失率、复购率、跳失率等各项指标
 */
@Data
@Slf4j
@RestController
@CrossOrigin({"*"})
@Tag(name = "Index", description = "各种率")
public class Index {

    // service层对象
    private final Index_Service index_service;

    @Autowired
    public Index(Index_Service index_service) {
        this.index_service = index_service;
    }



    /**
     * 方法名：Conversion_rate
     * 转化率接口：返回所有行为的下单转化率
     * 传入：null
     * 输出：对象Get_Conversion_rate包装到Result
     * */
    @Operation(summary = "所有行为的下单转化率")
    @GetMapping("Conversion_rate")
    public Result<Map<String,Double>> Conversion_rate(){
       return Result.success(index_service.Conversion_rate_Service());
    }

    /**
     * 获取所有月份的行为转化率
     */
    @Operation(summary = "获取所有月份的行为转化率")
    @GetMapping("/Conversion_rateByMonth")
    public Result<Map<Integer,Map<Integer,Double>>> Conversion_rateByMonth(){
        return Result.success(index_service.Conversion_rate_ByDate());
    }

    /**
     *     获取所有月份的流失率
     */
    @Operation(summary = "获取所有月份的流失率")
    @GetMapping("/calculateMonthlyChurnRateByMonth")
    public Result<Map<String,Double>> calculateMonthlyChurnRateByMonth(){
        return Result.success(index_service.getChurnRateAllMonth());
    }

    /**
     * 获取所有月份的复购率
     */
    @Operation(summary = "获取所有月份的复购率")
    @GetMapping("LossByMonth")
    public Result<Map<String,Double>> LossByMonth(){
        return Result.success(index_service.getRepurchaseAllMonth());
    }

    /**
     * 获取所有月份的跳失率
     */
    @Operation(summary = "获取所有月份的跳失率")
    @GetMapping("JumpOutByMonth")
    public Result<Map<String,Double>> JumpOutByMonth(){
        return Result.success(index_service.getJumpOutAllMonth());
    }

}
