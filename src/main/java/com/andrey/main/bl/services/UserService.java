package com.andrey.main.bl.services;

import com.andrey.main.bl.operations.ServiceOperation;
import com.andrey.main.dl.dao.UserDAO;
import com.andrey.main.dl.models.User;

import java.util.List;

public class UserService implements ServiceOperation<User> {

    UserDAO userDAO = UserDAO.getInstance();
    @Override
    public void add(User record) {
        userDAO.add(record);
    }

    @Override
    public void delete(User record) {
        userDAO.delete(record.getId());
    }

    @Override
    public void update(User record) {
        userDAO.update(record);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

}
