package com.andrey.main.dl.dao;


import com.andrey.main.bl.access.PermissionAction;
import com.andrey.main.dl.models.UserEntity;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.andrey.main.dl.dao.HibernateDBUtil.operationCRUD;

public class UserEntityDAO implements OperationDAO<UserEntity> {
    private static UserEntityDAO instance = new UserEntityDAO();
    private static final Logger log = Logger.getLogger(UserEntityDAO.class);

    private UserEntityDAO() {
    }

    public static UserEntityDAO getInstance() {
        return instance;
    }

    @Override
    public void add(UserEntity record) {
        log.info("add record: " + record);
        operationCRUD(session -> session.save(record));
    }

    @Override
    public void delete(long id) {
        UserEntity userEntity = getById(id);
        log.info("add record: " + userEntity);
        operationCRUD(session -> {
            session.delete(userEntity);
        });
    }

    @Override
    public void update(UserEntity record) {
        log.info("add update: " + record);
        operationCRUD(session -> session.update(record));
    }


    @Override
    public UserEntity getById(long id) {
        final UserEntity[] user = new UserEntity[1];
        operationCRUD(session -> {
            user[0] = session.get(UserEntity.class, id);
        });
        return user[0];
    }

    @Override
    public List<UserEntity> getAll() {
        final List[] list = new List[1];
        operationCRUD(session -> {
            list[0] = session.createQuery("FROM UserEntity ").list();
        });
        log.info("getAll records: " + list[0]);
        return list[0];
    }
//
//    public List<PermissionAction> getPermissions(UserEntity userEntity) {
//        List<PermissionAction> userEntities = new ArrayList<>();
//        for (UserEntity user : getAll()) {
//            if (user.equals(userEntity)) {
//                userEntities.add(user.getPermissionAction());
//            }
//        }
//        return userEntities;
//    }

}
