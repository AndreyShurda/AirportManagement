package com.andrey.main.dl.dao;

import java.util.List;

public interface OperationDAO<T> {

    void add(T record);

    void delete(long id);

    void update(T record);

    T getById(long id);

    List<T> getAll();
}
