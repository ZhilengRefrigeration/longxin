package com.example.longxin.Controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.example.longxin.Service.ClassifyByUserService;
import com.example.longxin.cache.Conversion_Rate.Conversion_Rate_Cache;
import com.example.longxin.pojo.ClassifyByUser.PaginationByClassifyPojo;
import com.example.longxin.pojo.ClassifyByUser.PaginationPojo;
import com.example.longxin.pojo.ClassifyByUser.PaginationRequest;
import com.example.longxin.pojo.ClassifyByUser.PaginationRequestClassify;
import com.example.longxin.pojo.Result;
import com.example.longxin.pojo.User;
import com.example.longxin.pojo.login.Loginpojo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 制冷
 * @date 2023/6/14 15:22
 * @description classifyByUser
 *  用户分类
 */
@Data
@Slf4j
@RestController
@CrossOrigin({"*"})
@Tag(name = "ClassifyByUser", description = "用户分类")
public class ClassifyByUser {
    ClassifyByUserService service;
    @Operation(summary = "登录接口")
    @Parameter(name = "username",description = "用户名")
    @Parameter(name = "password",description = "密码")
    @PostMapping("/login")
    public Result<SaTokenInfo> login(@RequestBody Loginpojo data ){
        SaTokenInfo saTokenInfo;
        if(data==null){
            return Result.error(400,"没有提交数据或者格式错误");
        }
        if(service.login(data)){
            StpUtil.login(data.getUsername());
            saTokenInfo = StpUtil.getTokenInfo();
            return Result.success(saTokenInfo);
        }
        return Result.error(1,"用户名或密码错误");
    }

    @Operation(summary = "分页展示用户分类信息")
    @Parameter(name = "Page",description = "页码")
    @Parameter(name = "Number",description = "每页多少条")
    @PostMapping("/getClassifyByUser")
    public Result<User[]> getClassifyByUser(@RequestBody PaginationRequest data){
       if(data.getPage() == null||data.getPage()<0||data.getNumber()<1){
          return Result.error(1,"请求数据错误");
       }
        return Result.success(service.getClassifyByUser(new PaginationPojo(data.getPage()* data.getNumber(),data.getNumber())));
    }

    @Operation(summary = "根据用户分类信息分页展示用户分类信息")
    @Parameter(name = "Page",description = "页码")
    @Parameter(name = "Number",description = "每页多少条")
    @Parameter(name = "classify",description = "要查询的分类信息")
    @PostMapping("/getUserClassifyByClassify")
    public Result<User[]> getgetUserClassifyByClassify(@RequestBody PaginationRequestClassify data){
       if(data.getPage()<0||data.getNumber()<1){
           return Result.error(1,"请求数据错误");
       }
        if(!Conversion_Rate_Cache.AllUserClassify.contains(data.getClassify())){
            return Result.error(400,"没有这个用户类型");
        }
        return Result.success(service.getClassifyByClassify(new PaginationByClassifyPojo(data.getPage()* data.getNumber(),data.getNumber(),data.getClassify())));
    }
    @Operation(summary = "根据用户信息搜索相关用户")
    @Parameter(name = "Page",description = "页码")
    @Parameter(name = "Number",description = "每页多少条")
    @Parameter(name = "classify",description = "要搜索的字符串，支持id,地址")
    @PostMapping("/SearchClassify")
    public Result<User[]> SearchClassify(@RequestBody PaginationRequestClassify data){
      if(data.getPage()<0||data.getNumber()<1){
          return Result.error(1,"请求数据错误");
      }
        return Result.success(service.SearchClassify(new PaginationByClassifyPojo(data.getPage()* data.getNumber(),data.getNumber(),data.getClassify())));
    }

    //构造方法

    @Autowired
    public ClassifyByUser(ClassifyByUserService service) {
        this.service = service;
    }
}
