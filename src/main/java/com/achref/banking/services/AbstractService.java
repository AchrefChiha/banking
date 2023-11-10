package com.achref.banking.services;

import java.util.List;

public interface AbstractService<T> {

    Integer save(T dto);

    List<T> finAll();

    T findById(Integer id);

    void delete(Integer id);

}
