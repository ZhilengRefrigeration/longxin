package com.example.longxin.Mapper;

import com.example.longxin.pojo.ClassifyByUser.PaginationByClassifyPojo;
import com.example.longxin.pojo.ClassifyByUser.PaginationPojo;
import com.example.longxin.pojo.User;
import com.example.longxin.pojo.login.Loginpojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author 制冷
 * @date 2023/6/14 15:45
 * @description ClassifyByUserMapper
 */
@Mapper
public interface ClassifyByUserMapper {

    /**
     * 查找登录用户
     * @param loginpojo 传入要登录的用户信息
     * @return 用户信息
     */
    @Select("select * from login where username = #{username} and password = #{password}")
    Loginpojo login(Loginpojo loginpojo);

    /**
     * 分页查询用户分类信息
     * @param data 分页信息
     * @return 用户信息组
     */
    @Select("select user_id,user_address,user_birthday,user_gender,city,classify from user limit #{from},#{to}")
    User[] getClassifyByUser(PaginationPojo data);

    /**
     * 根据用户类型查询用户信息
     * @param data 传入的条件
     * @return 用户信息
     */
    @Select("select user_id,user_address,user_birthday,user_gender,city,classify from user WHERE classify = #{classify} limit #{from},#{to}")
    User[] getClassifyByClassify(PaginationByClassifyPojo data);

    /**
     * 根据传入的信息搜索用户信息
     * @param data 传入的信息
     * @return 用户信息数组
     */
    @Select("SELECT user_id, user_address, user_birthday, user_gender, city, classify " +
            "FROM user " +
            "WHERE user_id LIKE CONCAT('%', #{classify}, '%') " +
            "   OR user_address LIKE CONCAT('%', #{classify}, '%') " +
            "   OR classify LIKE CONCAT('%', #{classify}, '%') " +
            "LIMIT #{from}, #{to}")
    User[] SearchClassify(PaginationByClassifyPojo data);



}
