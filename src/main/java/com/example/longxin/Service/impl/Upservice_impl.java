package com.example.longxin.Service.impl;

import com.example.longxin.Service.UpService;
import com.example.longxin.cache.Conversion_Rate.Conversion_Rate_Cache;
import com.example.longxin.pojo.UP.UpPojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author 制冷
 * @date 2023/6/18 15:20
 * @description Upservice_impl
 */
@Service
@Slf4j
public class Upservice_impl implements UpService {
    @Override
    public UpPojo getUp() {
        while (Conversion_Rate_Cache.upPojo==null){
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                log.info("休眠错误");
            }
        }
        return Conversion_Rate_Cache.upPojo;
    }
}
