package com.example.longxin.pojo.Conversion_rate_Pojo;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 制冷
 * @date 2023/5/10 17:22
 * @description Get_Conversion_rate
 */
@Data
@Service
public class Get_Conversion_rate {
    Map<Integer,Double> AllMap = new HashMap<>();
}
