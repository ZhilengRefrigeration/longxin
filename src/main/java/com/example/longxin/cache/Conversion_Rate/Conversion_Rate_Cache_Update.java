package com.example.longxin.cache.Conversion_Rate;

import com.example.longxin.Mapper.Conversion_Rate_Cache_Mapper;
import com.example.longxin.Mapper.Index_Mapper;
import com.example.longxin.Mapper.UPMapper;
import com.example.longxin.cache.Tool.BeanContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;


/**
 * @author 制冷
 * @date 2023/5/10 20:50
 * @description Conversion_Rate_Cache
 * 更新缓存
 */

@Slf4j
public class Conversion_Rate_Cache_Update implements Runnable{
    String[] xw = {"浏览","下单","关注","评论","加入购物车","咨询","投诉"};

    Conversion_Rate_Cache_Mapper conversion_rate_cache_mapper =  BeanContext.getApplicationContext().getBean(Conversion_Rate_Cache_Mapper.class);
    Index_Mapper index_mapper =  BeanContext.getApplicationContext().getBean(Index_Mapper.class);

    UPMapper upMapper = BeanContext.getApplicationContext().getBean(UPMapper.class);


    boolean running = true;

    @Override
    public void run() {
        while (running){

            //更新本地用户画像缓存
            new Thread(() -> Conversion_Rate_Cache.upPojo = conversion_rate_cache_mapper.getUser_profile_cache()).start();


            //更新缓存本地数据
           new Thread(()->{
               for(int i=1;i<=7;i++){
                   if(i==2){continue;}
                   Conversion_Rate_Cache.AllMap.put(xw[i-1],conversion_rate_cache_mapper.getConversion_Rate_BY_type_from_Cache(i));
               }
           }).start();


            //更新本地月份转化率缓存
            new Thread(()->{
                HashMap<Integer,Double> hashMap = new HashMap<>();
                for(int i=1;i<=12;i++){
                    for(int j=1;j<=7;j++){
                        hashMap.put(j,conversion_rate_cache_mapper.get_Conversion_rate_ByDateAndType(i, j));
                    }
                Conversion_Rate_Cache.AllMonthData.put(i,hashMap);
                    hashMap = new HashMap<>();
                }
            }).start();



            //更新本地流失率，复购率，跳失率
            new Thread(()->{
                for(int i =1;i<=12;i++){
                    Conversion_Rate_Cache.ChurnRateAllMonth.put(i+"月",conversion_rate_cache_mapper.getChurnRateByMonthFromCache(i));
                    Conversion_Rate_Cache.RepurchaseAllMonth.put(i+"月",conversion_rate_cache_mapper.getRepurchaseByMonth(i));
                    Conversion_Rate_Cache.JumpOutAllMonth.put(i+"月",conversion_rate_cache_mapper.getJumpOutByMonth(i));
                }
            }).start();



             //**********************将更新慢的云端更新放到后边，提升速度*****************************
            try {
                TimeUnit.DAYS.sleep(1);
            } catch (InterruptedException e) {
                log.info("休眠错误");
            }


            //更新云端流失率，复购率，跳失率
           new Thread(()->{
               for(int i=1;i<=12;i++){
                   conversion_rate_cache_mapper.UpChurnRateByMonth(i,index_mapper.getChurnRateByMonth(i));
                   conversion_rate_cache_mapper.UpRepurchaseByMonth(i,index_mapper.getRepurchaseByMonth(i));
                   conversion_rate_cache_mapper.UpJumpOutByMonth(i, index_mapper.getJumpOutByMonth(i));
               }
           }).start();

            //更新云端月份转化率缓存
           new Thread(()->{
               for(int i=1;i<=12;i++){
                   for(int j=1;j<=7;j++){
                       if(j==2||i<2||i>4){continue;}
                       double dta = index_mapper.calculateMonthlyConversionRate(i,j);
                       conversion_rate_cache_mapper.update_Conversion_rate_ByDateAndType(i,j,dta);
                   }
               }
           }).start();

            //更新云端缓存数据库;
           new Thread(()->{
               for(int i=1;i<=7;i++){
                   if(i==2){continue;}
                   Double ste = index_mapper.get_Conversion_rateByType(i);
                   if(ste==null)continue;
                   conversion_rate_cache_mapper.UpConversion_Rate_Cache_Data(i,ste);
               }
           }).start();

           //更新云端用户画像
            new Thread(()->
               conversion_rate_cache_mapper.UpdateUser_profile_cache(upMapper.getUp())
            ).start();


            log.info("更新一次数据......");
            try {
                TimeUnit.DAYS.sleep(1);
            } catch (InterruptedException e) {
                log.info("休眠错误");
            }
        }
    }
}
