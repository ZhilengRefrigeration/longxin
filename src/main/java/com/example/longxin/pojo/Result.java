package com.example.longxin.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 制冷
 * @date 2023/5/10 14:51
 * @description Result
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Result", description = "通用返回对象")
public class Result<E> {
    @Schema(name = "code", description = "状态码")
    private Integer code;//响应码，1 代表成功; 0 代表失败
    @Schema(name = "msg", description = "提示信息")
    private String msg;  //响应信息 描述字符串
    @Schema(name = "data", description = "数据封装")
    private E data; //返回的数据

    //增删改 成功响应
    public static Result<String> success(){
        return new Result<>(0,"success",null);
    }
    //查询 成功响应
    public static <T> Result<T> success(T data) {
        return new Result<>(0, "success", data);
    }
    public static <T> Result<T> success(String msg,T data) {
        return new Result<>(0, msg, data);
    }
    //失败响应
    public static <T> Result<T> error(int code,String msg){
        return new Result<>(code,msg,null);
    }


}
