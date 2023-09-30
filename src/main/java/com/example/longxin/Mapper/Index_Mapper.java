package com.example.longxin.Mapper;

import com.example.longxin.pojo.Conversion_rate_Pojo.Set_Conversion_rate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author 制冷
 * @date 2023/5/8 19:51
 * @description Index_Mapper
 * 根据数据库信息计算各种数据
 */
@Mapper
public interface Index_Mapper {

    //计算所有商品对应的行为转化率
    @Select("select\n" +
            "(select sum(type#{type}) from biaob where type#{type} !=0 and type2!=0 )/\n" +
            "(select sum(type#{type}) from biaob where type#{type} !=0 )\n" +
            "AS result;")
    Double get_Conversion_rateByType(Integer type);

    //计算相应商品id的相应行为转化率
    @Select("select\n" +
            "(select sum(type#{type}) from biaob where type#{type} !=0 and type2!=0 and sku = #{commodityID})/\n" +
            "(select sum(type#{type}) from biaob where type#{type} !=0 and sku = #{commodityID})\n" +
            "AS result;")
    Double get_Conversion_rate_ByCommodityID_AndType(Set_Conversion_rate set_conversion_rate);



     // 计算按月行为转化率

    @Select("SELECT COALESCE((SELECT SUM(type#{type}) FROM DataActBYdate WHERE type#{type} != 0 AND type2 != 0 AND MONTH(month) = #{month}) / \n" +
            "           (SELECT SUM(type#{type}) FROM DataActBYdate WHERE type#{type} != 0 AND MONTH(month) = #{month}), 0)")
    Double calculateMonthlyConversionRate(int month,int type);

    /**
     * 按月计算流失率
     */
    @Select("SELECT (((SELECT COUNT(*) FROM user_act WHERE act_type = 2 AND MONTH(act_date) = #{month}) - " +
            "(SELECT COUNT(DISTINCT act_user) FROM user_act WHERE act_type = 2 AND MONTH(act_date) = #{month} +1 AND act_user IN " +
            "(SELECT act_user FROM user_act WHERE act_type = 2 AND MONTH(act_date) = #{month}))) / " +
            "(SELECT COUNT(*) FROM user_act WHERE act_type = 2 AND MONTH(act_date) = #{month})) * 100;")
    Double getChurnRateByMonth(int month);


    /**
     * 按月计算复购率
     */
    @Select("SELECT " +
            "((SELECT COUNT(DISTINCT act_user) " +
            "FROM user_act " +
            "WHERE act_type = 2 " +
            "AND MONTH(act_date) = #{month} +1 " +
            "AND act_user IN (SELECT act_user FROM user_act WHERE act_type = 2 AND MONTH(act_date) = #{month})) " +
            "/ " +
            "(SELECT COUNT(*) FROM user_act WHERE act_type = 2 AND MONTH(act_date) = #{month})) * 100;")
    Double getRepurchaseByMonth(int month);


    /**
     * 按月计算跳失率
     */
    @Select("SELECT " +
            "((SELECT COUNT(DISTINCT act_user) " +
            "FROM (SELECT act_user, COUNT(DISTINCT act_sku) AS count_one " +
            "      FROM user_act " +
            "      WHERE act_type = 1 " +
            "        AND MONTH(act_date) = #{month} " +
            "      GROUP BY act_user " +
            "      HAVING count_one = 1) AS subquery) " +
            "/ " +
            "(SELECT COUNT(DISTINCT act_user) " +
            " FROM user_act " +
            " WHERE act_type = 1 " +
            "   AND MONTH(act_date) = #{month})) * 100 AS bounce_rate;")
    Double getJumpOutByMonth(int month);




}
