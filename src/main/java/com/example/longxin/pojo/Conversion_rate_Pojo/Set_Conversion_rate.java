package com.example.longxin.pojo.Conversion_rate_Pojo;

import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * @author 制冷
 * @date 2023/5/8 19:25
 * @description Set_Conversion_rate
 * 用于转化率接口传入的实体类
 */
@Data
@Service
public class Set_Conversion_rate {
    private int commodityID;//商品id
    private int type;//行为
}
