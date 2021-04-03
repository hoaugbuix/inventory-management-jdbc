package com.hoangbuix.dev.dao;

import com.hoangbuix.dev.model.mapper.RowMapper;

import java.util.List;

public interface BaseDAO<E> {
    <E>List<E> query(String sql, RowMapper<E> rowMapper, Object... parameters);
    void update(String sql, Object... parameters);
    Integer insert(String sql, Object... parameters);
    int count(String sql, Object... parameters);
}
