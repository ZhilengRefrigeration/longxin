package com.example.longxin.Service;

import java.util.Map;

/**
 * @author 制冷
 * @date 2023/5/8 19:34
 * @description index_Service
 * 处理index类传过来的数据接口
 */
public interface Index_Service {

    /**
     * 方法名：Conversion_rate_Service
     * 传入：null
     * 输出：Result
     * 作用：返回所有行为的转化率
     */
    Map<String, Double> Conversion_rate_Service();

    /**
     * 获取所有所有的转化率（月）
     */
    Map<Integer,Map<Integer,Double>>  Conversion_rate_ByDate();

    /**
     * 获取所有月的流失率
     */
    Map<String,Double> getChurnRateAllMonth();

    /**
     * 获取所有月份的复购率
     */
    Map<String,Double> getRepurchaseAllMonth();

    /**
     * 获取所有月份跳失率
     */
    Map<String,Double> getJumpOutAllMonth();

}
