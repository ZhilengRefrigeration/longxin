package com.example.longxin.Mapper;

import com.example.longxin.pojo.UP.UpPojo;
import org.apache.ibatis.annotations.*;

/**
 * @author 制冷
 * @date 2023/5/10 21:21
 * @description Conversion_Rate_Cache_Mapper
 * 和云端缓存做操作的mapper
 */
@Mapper
public interface Conversion_Rate_Cache_Mapper {

    //根据行为id获取数据
    @Select("select cacheData from Conversion_Rate_Cache where cacheType = #{cacheType}")
    Double getConversion_Rate_BY_type_from_Cache(Integer cacheType);

    //根据行为id更新数据
    @Update("update Conversion_Rate_Cache set cacheData = #{data} WHERE cacheType = #{cacheType} ")
    Integer UpConversion_Rate_Cache_Data(Integer cacheType,Double data);


     //更新云端按月份存储的转化率数据

    @Update("UPDATE Conversion_Rate_ByDateCache SET data = #{data} where type = #{type} and month = #{month};")
    int update_Conversion_rate_ByDateAndType(int month,int type,Double data);


     //从云端获取按月份存储的转化率数据

    @Select("select data from Conversion_Rate_ByDateCache where month = #{month} and type = #{type};")
    Double get_Conversion_rate_ByDateAndType(int month,int type);

    //根据月份从云端缓存获取流失率
    @Select("SELECT ChurnRate from indexCache where month = #{month}")
    Double getChurnRateByMonthFromCache(Integer month);

    //根据月份更新转化率
    @Update("update indexCache set ChurnRate = #{ChurnRate} where month = #{month}")
    Integer UpChurnRateByMonth(Integer month,Double ChurnRate);

    //根据月份从云端获取复购率
    @Select("SELECT Repurchase from indexCache where month = #{month}")
    Double getRepurchaseByMonth(int month);

    //根据月份跟新云端复购率
    @Update("update indexCache set Repurchase = #{Repurchase} where month = #{month}")
    Integer UpRepurchaseByMonth(Integer month,Double Repurchase);

    //根据月份获取云端跳失率
    @Select("select JumpOut from indexCache where month = #{month}")
    Double getJumpOutByMonth(Integer month);

    //根据月份更新云端跳失率
    @Update("update indexCache set JumpOut = #{JumpOut} where month = #{month}")
    int UpJumpOutByMonth(Integer month,Double JumpOut);

    //获取云端用户画像数据
    @Select("select * from user_profile_cache limit 0,1 ;")
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
    UpPojo getUser_profile_cache();

    //更新云端用户画像数据
    @Update("update user_profile_cache set female_ratio = #{female_ratio},male_ratio = #{male_ratio},first_tier_city_ratio = #{first_tier_city_ratio},second_tier_city_ratio = #{second_tier_city_ratio},third_tier_city_ratio = #{third_tier_city_ratio},fourth_tier_city_ratio = #{fourth_tier_city_ratio},fifth_tier_city_ratio =#{fifth_tier_city_ratio},age_0_to_10_ratio = #{age_0_to_10_ratio},age_11_to_20_ratio =#{age_11_to_20_ratio} where true")
    int UpdateUser_profile_cache(UpPojo upPojo);
}
