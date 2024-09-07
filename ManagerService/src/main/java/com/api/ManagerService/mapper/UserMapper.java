package com.api.ManagerService.mapper;

import com.api.ManagerService.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByUsername(String userName);
    void save(User user);
}
