package com.hoangbuix.dev.model.mapper;

import java.sql.ResultSet;

public interface RowMapper<E> {
    E mapRow(ResultSet resultSet);
}
