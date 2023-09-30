package com.example.longxin.pojo.UP;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 制冷
 * @date 2023/6/18 15:09
 * @description UpPojo
 */
@Data
@Schema(name = "Uppojo", description = "用户画像实体类")
public class UpPojo {
    //女生占比
    @Schema(name = "female_ratio", description = "女生占比")
   private Double female_ratio;

   //男生占比
   @Schema(name = "male_ratio", description = "男生占比")
    private Double male_ratio;

    //一线城市占比
    @Schema(name = "first_tier_city_ratio", description = "一线城市占比")
    private Double first_tier_city_ratio;

    //二线城市占比
    @Schema(name = "second_tier_city_ratio", description = "二线城市占比")
    private Double second_tier_city_ratio;

    //三线城市占比
    @Schema(name = "third_tier_city_ratio", description = "三线城市占比")
    private Double third_tier_city_ratio;

    //四线城市占比
    @Schema(name = "fourth_tier_city_ratio", description = "四线城市占比")
    private Double fourth_tier_city_ratio;

    //五线城市占比
    @Schema(name = "fifth_tier_city_ratio", description = "五线城市占比")
    private Double fifth_tier_city_ratio;

    //0到10岁年龄占比
    @Schema(name = "age_0_to_10_ratio", description = "0到10岁年龄占比")
    private Double age_0_to_10_ratio;

    //11到20岁年龄占比
    @Schema(name = "age_11_to_20_ratio", description = "11到20岁年龄占比")
    private Double age_11_to_20_ratio;


}
