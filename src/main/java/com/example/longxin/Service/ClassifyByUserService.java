package com.example.longxin.Service;

import com.example.longxin.pojo.ClassifyByUser.PaginationByClassifyPojo;
import com.example.longxin.pojo.ClassifyByUser.PaginationPojo;
import com.example.longxin.pojo.User;
import com.example.longxin.pojo.login.Loginpojo;

/**
 * @author 制冷
 * @date 2023/6/14 15:30
 * @description ClassifyByUserService
 * 处理查询用户分类
 */
public interface ClassifyByUserService {

    /**
     * 分页查询用户分类信息
     * @param data 分页信息
     * @return 用户分类信息数组
     */
    User[] getClassifyByUser(PaginationPojo data);

    /**
     * 根据用户分类分页查询用户信息
     * @param data 传入条件
     * @return 用户信息
     */

    User[] getClassifyByClassify(PaginationByClassifyPojo data);

    /**
     * 根据信息模糊搜索用户信息
      * @param data 传入的条件
     * @return 用户信息
     */
    User[] SearchClassify(PaginationByClassifyPojo data);

    /**
     * 登录判断
     * @param data 用户名和密码
     * @return 登录是否成功
     */
    Boolean login(Loginpojo data);

}
