package com.andrey.main.bl.services;

import com.andrey.main.dl.dao.UserEntityDAO;
import com.andrey.main.dl.models.User;
import com.andrey.main.dl.models.UserEntity;

import java.util.List;

public class UserEntityService implements ServiceOperation<UserEntity> {

    UserEntityDAO userEntityDAO = UserEntityDAO.getInstance();
    @Override
    public void add(UserEntity record) {
        userEntityDAO.add(record);
    }

    @Override
    public void delete(UserEntity record) {
        userEntityDAO.delete(record.getId());
    }

    @Override
    public void update(UserEntity record) {
        userEntityDAO.update(record);
    }

    @Override
    public List<UserEntity> getAll() {
        return userEntityDAO.getAll();
    }

    public User getUserPermission(User user){
        return null;
    }
}
