package com.example.longxin.pojo.ClassifyByUser;

import lombok.Data;

/**
 * @author 制冷
 * @date 2023/6/14 15:26
 * @description PaginationPojo
 * 前端请求页面数据
 */
@Data
public class PaginationPojo {
    private int from;
    private int to;

    public PaginationPojo(int from, int to) {
        this.from = from;
        this.to = to;
    }
}
