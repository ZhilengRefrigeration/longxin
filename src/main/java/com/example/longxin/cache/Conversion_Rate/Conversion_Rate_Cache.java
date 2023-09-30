package com.example.longxin.cache.Conversion_Rate;

import com.example.longxin.pojo.UP.UpPojo;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 制冷
 * @date 2023/5/10 20:54
 * @description Conversion_Rate_Cache
 * 缓存
 */
@Service
@Data
public class Conversion_Rate_Cache {

   /**
    * 所有行为转化率
    */
   public static Map<String,Double> AllMap = new HashMap<>();

   /**
    * 按时间分类的转化率
    */
   public static Map<Integer,Map<Integer,Double>> AllMonthData = new HashMap<>();

   /**
    * 所有月份流失率
    */
   public static Map<String,Double> ChurnRateAllMonth = new HashMap<>();

   /**
    * 所有月份复购率
    */

   public static Map<String,Double> RepurchaseAllMonth = new HashMap<>();

   /**
    * 所有月份跳失率
    */
   public static Map<String,Double> JumpOutAllMonth = new HashMap<>();

   /**
    * 所有客户类型
    */
   public static List<String>  AllUserClassify = new ArrayList<String>(){{
      add("一般维持客户");
      add("新客户");
      add("流失客户");
      add("潜力客户");
      add("重要保持客户");
      add("重要发展客户");
      add("重要挽留客户");
   }};

   /**
    * 用户画像缓存
    */
   public static UpPojo upPojo;
}
