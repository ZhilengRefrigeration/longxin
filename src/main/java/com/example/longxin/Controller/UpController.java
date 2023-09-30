package com.example.longxin.Controller;

import com.example.longxin.Service.UpService;
import com.example.longxin.pojo.Result;
import com.example.longxin.pojo.UP.UpPojo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 制冷
 * @date 2023/6/18 15:21
 * @description UpController
 */
@Data
@Slf4j
@RestController
@CrossOrigin({"*"})
@Tag(name = "UpController", description = "用户画像")
public class UpController {
    UpService service;

    /**
     * 用户画像接口
     * @return 用户画像数据
     */
    @Operation(summary = "用户画像数据")
    @GetMapping("UpData")
    public Result<UpPojo> Conversion_rate(){
        return Result.success(service.getUp());
    }

    @Autowired
    public UpController(UpService service) {
        this.service = service;
    }

}
