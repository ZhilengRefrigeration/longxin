package com.example.longxin.pojo;

import com.example.longxin.common.DateUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * @author 制冷
 * @date 2023/6/14 15:31
 * @description User
 */
@Data
@Schema(name = "User", description = "用户信息实体类")
public class User {

    @Schema(name = "userId", description = "用户id")
    @JsonProperty("userId")
    private int userId;

    @Schema(name = "userAddress", description = "用户地址")
    @JsonProperty("userAddress")
    private String userAddress;

    @Schema(name = "userBirthday", description = "出生日期")
    @JsonProperty("userBirthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userBirthday;

    @Schema(name = "userGender", description = "用户性别")
    @JsonProperty("userGender")
    private String userGender;

    @Schema(name = "city", description = "用户所在城市")
    @JsonProperty("city")
    private String city;

    @Schema(name = "classify", description = "用户类型")
    @JsonProperty("classify")
     private String classify;

    //年份转年龄
    public int  getUserBirthday() {
        Calendar c = Calendar.getInstance();
        c.setTime(userBirthday);
        return Calendar.getInstance().get(Calendar.YEAR)-c.get(Calendar.YEAR);
    }

    public String getUserGender() {
        if(this.userGender.equals("female")){
            return "女";
        }
        return "男";
    }

}
