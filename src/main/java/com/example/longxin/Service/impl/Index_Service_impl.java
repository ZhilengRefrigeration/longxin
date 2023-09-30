package com.example.longxin.Service.impl;
import com.example.longxin.Service.Index_Service;
import com.example.longxin.cache.Conversion_Rate.Conversion_Rate_Cache;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//实现类

@Service
@Slf4j
@Data
public class Index_Service_impl implements Index_Service {


    // 等待时间常量
    private static final long WAIT_TIMEOUT = 1000;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();


    /**
     * 方法名：Conversion_rate_Service
     * 传入：null
     * 输出：Result
     * 作用：返回所有行为的转化率
     */
    @Override
    public Map<String, Double> Conversion_rate_Service() {
        try {
            lock.lock();
            while (Conversion_Rate_Cache.AllMap.get("投诉") == null) {
                if(condition.await(WAIT_TIMEOUT, TimeUnit.MILLISECONDS))
                {log.info("等待期间发生了异常或被虚假唤醒");}
            }
            return Conversion_Rate_Cache.AllMap;
        } catch (InterruptedException e) {
            throw new CustomException(e);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 根据时间
     */
    @Override
    public Map<Integer,Map<Integer,Double>> Conversion_rate_ByDate() {
        try {
            lock.lock();
            while (Conversion_Rate_Cache.AllMonthData.get(12) == null) {
                if(condition.await(WAIT_TIMEOUT, TimeUnit.MILLISECONDS))
                {log.info("等待期间发生了异常或被虚假唤醒");}
            }
            return Conversion_Rate_Cache.AllMonthData;
        } catch (InterruptedException e) {
            throw new CustomException(e);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取所有月的流失率
     */
    @Override
    public Map<String, Double> getChurnRateAllMonth() {
        try {
            lock.lock();
            while (Conversion_Rate_Cache.ChurnRateAllMonth.get("3月") == null) {
                if(condition.await(WAIT_TIMEOUT, TimeUnit.MILLISECONDS))
                {log.info("等待期间发生了异常或被虚假唤醒");}
            }
            return Conversion_Rate_Cache.ChurnRateAllMonth;
        } catch (InterruptedException e) {
            throw new CustomException(e);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取所有月份复购率
     *
     * @return 所有月份复购率
     */
    @Override
    public Map<String, Double> getRepurchaseAllMonth() {
        try {
            lock.lock();
            while (Conversion_Rate_Cache.RepurchaseAllMonth.get("3月") == null) {
                if(condition.await(WAIT_TIMEOUT, TimeUnit.MILLISECONDS))
                {log.info("等待期间发生了异常或被虚假唤醒");}
            }
            return Conversion_Rate_Cache.RepurchaseAllMonth;
        } catch (InterruptedException e) {
            throw new CustomException(e);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取所有月份跳失率
     *
     * @return 所有月份跳失率
     */

    @Override
    public Map<String, Double> getJumpOutAllMonth() {
        try {
            lock.lock();
            while (Conversion_Rate_Cache.JumpOutAllMonth.get("3月") == null) {
                if(condition.await(WAIT_TIMEOUT, TimeUnit.MILLISECONDS))
                {log.info("等待期间发生了异常或被虚假唤醒");}
            }
            return Conversion_Rate_Cache.JumpOutAllMonth;
        } catch (InterruptedException e) {
            throw new CustomException(e);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 自定义异常类
     */
    static class CustomException extends RuntimeException {
        CustomException(Throwable cause) {
            super(cause);
        }
    }
}