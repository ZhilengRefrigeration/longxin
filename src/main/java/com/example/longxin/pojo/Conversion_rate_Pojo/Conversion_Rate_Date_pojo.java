package com.example.longxin.pojo.Conversion_rate_Pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 制冷
 * @date 2023/5/15 14:19
 * @description Conversion_Rate_Data_pojo
 * 传入日期实体类
 */
@Data
public class Conversion_Rate_Date_pojo {

    /**
     * 日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date ;
}
