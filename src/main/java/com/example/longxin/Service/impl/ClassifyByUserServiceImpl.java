package com.example.longxin.Service.impl;

import com.example.longxin.Mapper.ClassifyByUserMapper;
import com.example.longxin.Service.ClassifyByUserService;
import com.example.longxin.pojo.ClassifyByUser.PaginationByClassifyPojo;
import com.example.longxin.pojo.ClassifyByUser.PaginationPojo;
import com.example.longxin.pojo.User;
import com.example.longxin.pojo.login.Loginpojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 制冷
 * @date 2023/6/14 15:44
 * @description ClassifyByUserServiceImpl
 */
@Service
public class ClassifyByUserServiceImpl implements ClassifyByUserService {

    ClassifyByUserMapper mapper;
    @Autowired
    public ClassifyByUserServiceImpl(ClassifyByUserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public User[] getClassifyByUser(PaginationPojo data) {
       return mapper.getClassifyByUser(data);
    }

    @Override
    public User[] getClassifyByClassify(PaginationByClassifyPojo data) {
        return mapper.getClassifyByClassify(data);
    }

    @Override
    public User[] SearchClassify(PaginationByClassifyPojo data) {
        return mapper.SearchClassify(data);
    }

    @Override
    public Boolean login(Loginpojo data) {
        return mapper.login(data) != null;
    }
}
