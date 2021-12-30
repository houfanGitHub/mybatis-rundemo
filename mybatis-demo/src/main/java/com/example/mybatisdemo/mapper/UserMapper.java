package com.example.mybatisdemo.mapper;

import com.example.mybatisdemo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List<User> findUser();

    User findUserById(@Param("id") Long id);
}
