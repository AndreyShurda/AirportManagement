package com.andrey.main.bl.operations;

import java.util.List;

public interface ServiceOperation<T> {

    void add(T record);

    void delete(T record);

    void update(T record);

//    T getById(T id);

    List<T> getAll();
}
