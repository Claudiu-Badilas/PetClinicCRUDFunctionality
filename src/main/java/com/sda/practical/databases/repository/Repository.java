package com.sda.practical.databases.repository;

import java.util.List;

public interface Repository<T> {

    void save(T t);

    List<T> findAll();

    void delete(T t);

    void update(T t);

    T findById(Integer id);

}
