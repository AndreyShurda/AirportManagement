package com.andrey.main.dl.dao;

import java.util.List;

public interface OperationDAO<T> {

    void add(T record);

    void delete(T record);

    void update(T record);

//    T getById(int id);

    List<T> getAll();
}
