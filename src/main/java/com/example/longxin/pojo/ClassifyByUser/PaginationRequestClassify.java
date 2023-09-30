package com.example.longxin.pojo.ClassifyByUser;

import lombok.Data;

/**
 * @author 制冷
 * @date 2023/6/19 18:18
 * @description PaginationRequest
 */
@Data
public class PaginationRequestClassify {
    private Integer page;
    private Integer number;
    private String classify;
}
