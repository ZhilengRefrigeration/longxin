package com.example.longxin.pojo.ClassifyByUser;

import lombok.Data;

/**
 * @author 制冷
 * @date 2023/6/14 15:26
 * @description PaginationPojo
 * 分页传入
 */
@Data
public class PaginationByClassifyPojo {
    private int from;
    private int to;
    private String classify;

    public PaginationByClassifyPojo() {
    }

    public PaginationByClassifyPojo(int from, int to, String classify) {
        this.from = from;
        this.to = to;
        this.classify = classify;
    }
}
