package com.andrey.main.dl.dao;


import com.andrey.main.dl.models.User;
import org.apache.log4j.Logger;

import java.util.List;

import static com.andrey.main.dl.dao.utils.HibernateDBUtil.operationCRUD;

public class UserDAO implements OperationDAO<User> {
    private static UserDAO instance = new UserDAO();
    private static final Logger log = Logger.getLogger(UserDAO.class);

    private UserDAO() {
    }

    public static UserDAO getInstance() {
        return instance;
    }

    @Override
    public void add(User record) {
        log.info("add record: " + record);
        operationCRUD(session -> session.save(record));
    }

    @Override
    public void delete(long id) {
        User user = getById(id);
        log.info("add record: " + user);
        operationCRUD(session -> {
            session.delete(user);
        });
    }

    @Override
    public void update(User record) {
        log.info("add update: " + record);
        operationCRUD(session -> session.update(record));
    }


    @Override
    public User getById(long id) {
        final User[] user = new User[1];
        operationCRUD(session -> {
            user[0] = session.get(User.class, id);
        });
        return user[0];
    }

    @Override
    public List<User> getAll() {
        final List[] list = new List[1];
        operationCRUD(session -> {
            list[0] = session.createQuery("FROM User ").list();
        });
        log.info("getAll records: " + list[0]);
        return list[0];
    }

}
