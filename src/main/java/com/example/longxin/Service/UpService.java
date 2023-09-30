package com.example.longxin.Service;

import com.example.longxin.pojo.UP.UpPojo;
import org.springframework.stereotype.Service;

/**
 * @author 制冷
 * @date 2023/6/18 15:18
 * @description UpService
 */
public interface UpService {
    /**
     * 获取用户画像数据
     * @return 用户画像数据
     */
    UpPojo getUp();
}
