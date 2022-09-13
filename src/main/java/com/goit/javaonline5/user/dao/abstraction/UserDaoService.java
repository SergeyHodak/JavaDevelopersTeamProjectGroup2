package com.goit.javaonline5.user.dao.abstraction;

import com.goit.javaonline5.baseservices.dao.BaseDaoService;
import com.goit.javaonline5.user.model.UserModel;

public interface UserDaoService extends BaseDaoService<UserModel> {
    UserModel findByEmail(String email);
}
