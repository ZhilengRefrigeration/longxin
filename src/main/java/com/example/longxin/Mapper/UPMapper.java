package com.example.longxin.Mapper;

import com.example.longxin.pojo.UP.UpPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @author 制冷
 * @date 2023/6/18 15:09
 * @description UPMapper
 */
@Mapper
public interface UPMapper {

    @Select(" SELECT\n" +
            "        (SUM(CASE WHEN user_gender = 'female' THEN 1 ELSE 0 END) / COUNT(*)) * 100 AS female_ratio,\n" +
            "        (SUM(CASE WHEN user_gender = 'male' THEN 1 ELSE 0 END) / COUNT(*)) * 100 AS male_ratio,\n" +
            "        (SUM(CASE WHEN city_sublevel = '一线城市' THEN 1 ELSE 0 END) / COUNT(*)) * 100 AS first_tier_city_ratio,\n" +
            "        (SUM(CASE WHEN city_sublevel = '二线城市' THEN 1 ELSE 0 END) / COUNT(*)) * 100 AS second_tier_city_ratio,\n" +
            "        (SUM(CASE WHEN city_sublevel = '三线城市' THEN 1 ELSE 0 END) / COUNT(*)) * 100 AS third_tier_city_ratio,\n" +
            "        (SUM(CASE WHEN city_sublevel = '四线城市' THEN 1 ELSE 0 END) / COUNT(*)) * 100 AS fourth_tier_city_ratio,\n" +
            "        (SUM(CASE WHEN city_sublevel = '五线城市' THEN 1 ELSE 0 END) / COUNT(*)) * 100 AS fifth_tier_city_ratio,\n" +
            "        (SUM(CASE WHEN YEAR(NOW()) - YEAR(user_birthday) BETWEEN 0 AND 10 THEN 1 ELSE 0 END) / COUNT(*)) * 100 AS age_0_to_10_ratio,\n" +
            "        (SUM(CASE WHEN YEAR(NOW()) - YEAR(user_birthday) BETWEEN 11 AND 20 THEN 1 ELSE 0 END) / COUNT(*)) * 100 AS age_11_to_20_ratio\n" +
            "    FROM user;")
    @Results({
            @Result(property = "female_ratio", column = "female_ratio"),
            @Result(property = "male_ratio", column = "male_ratio"),
            @Result(property = "first_tier_city_ratio", column = "first_tier_city_ratio"),
            @Result(property = "second_tier_city_ratio", column = "second_tier_city_ratio"),
            @Result(property = "third_tier_city_ratio", column = "third_tier_city_ratio"),
            @Result(property = "fourth_tier_city_ratio", column = "fourth_tier_city_ratio"),
            @Result(property = "fifth_tier_city_ratio", column = "fifth_tier_city_ratio"),
            @Result(property = "age_0_to_10_ratio", column = "age_0_to_10_ratio"),
            @Result(property = "age_11_to_20_ratio", column = "age_11_to_20_ratio")
    })
    UpPojo getUp();
}
