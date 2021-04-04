package com.hoangbuix.dev.dao.impl;

import com.hoangbuix.dev.dao.BaseDAO;
import com.hoangbuix.dev.model.mapper.RowMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
@Transactional(rollbackFor = Exception.class)
public class BaseDAOImpl<E> implements BaseDAO<E> {
    final static Logger log = Logger.getLogger(BaseDAOImpl.class);

    ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
    public Connection getConnection(){
        try {
            Class.forName(resourceBundle.getString("driverName")).newInstance();
            String url = resourceBundle.getString("url");
            String user = resourceBundle.getString("user");
            String password = resourceBundle.getString("password");
            return DriverManager.getConnection(url, user, password);
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e){
            log.info(e.getMessage());
            return null;
        }
    }

    @Override
    public <E> List<E> query(String sql, RowMapper<E> rowMapper, Object... parameters) {
        List<E> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            // set Parameter
            setParameter(statement, parameters);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                results.add(rowMapper.mapRow(resultSet));
            }
            return results;
        }catch (SQLException e){
            return null;
        }finally {
            try {
                if (connection != null){
                    connection.close();
                }
                if (statement != null){
                    statement.close();
                }
                if (resultSet != null){
                    resultSet.close();
                }
            }catch (SQLException e2){
                e2.printStackTrace();
            }
        }
    }

    private void setParameter(PreparedStatement statement, Object... parameters) {
        try {
            for (int i = 0; i < parameters.length; i++){
                Object parameter = parameters[i];
                int index = i + 1;
                if (parameter instanceof Long){
                    statement.setLong(index, (Long) parameter);
                }
                else if (parameter instanceof String){
                    statement.setString(index, (String) parameter);
                }
                else if (parameter instanceof Integer){
                    statement.setInt(index, (Integer) parameter);
                }
                else if (parameter instanceof Timestamp){
                    statement.setTimestamp(index,(Timestamp) parameter);
                }
                else if (parameter instanceof Date){
                    statement.setDate(index,(Date) parameter);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            setParameter(statement, parameters);
            statement.executeUpdate();
            connection.commit();
        }catch (SQLException e){
            if (connection != null){
                try {
                    connection.rollback();
                }catch (SQLException e1){
                    e.printStackTrace();
                }
            }
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null){
                    statement.close();
                }
            }catch (SQLException e2){
                e2.printStackTrace();
            }
        }
    }

    @Override
    public Integer insert(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            int id = 0;
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
            setParameter(statement, parameters);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                id = resultSet.getInt(1);
            }
            connection.commit();
            return id;
        }catch (SQLException e){
            try {
                if (connection != null) {
                    connection.rollback();
                }
            }catch (SQLException e1){
                e1.printStackTrace();
            }
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null){
                    statement.close();
                }
                if (resultSet != null){
                    resultSet.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int count(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            int count = 0;
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            setParameter(statement, parameters);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                count = resultSet.getInt(1);
            }
            return count;
        }catch (SQLException e){
            return 0;
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null){
                    statement.close();
                }
                if (resultSet != null){
                    resultSet.close();
                }
            }catch (SQLException e2){
                return 0;
            }
        }
    }
    // regex
    public String getGenericName() {
        String s = getClass().getGenericSuperclass().toString();
        Pattern pattern = Pattern.compile("\\<(.*?)\\>");
        Matcher m = pattern.matcher(s);
        String generic = "null";
        if (m.find()) {
            generic = m.group(1);
        }
        return generic;
    }
}
