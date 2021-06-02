package com.hoangbuix.dev.dao.impl;

import com.hoangbuix.dev.dao.MenuDAO;
import com.hoangbuix.dev.entity.MenuEntity;
import com.hoangbuix.dev.model.mapper.MenuMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class MenuDAOImpl extends BaseDAOImpl<MenuEntity> implements MenuDAO<MenuEntity> {

    @Override
    public List<MenuEntity> findAll() {
        String sql = "call menu_findMenuAll()";
        return query(sql, new MenuMapper());
    }

    @Override
    public MenuEntity findById(int id) {
        return null;
    }

    @Override
    public int save(MenuEntity instance) {
        return 0;
    }

    @Override
    public void update(MenuEntity instance) {

    }

    @Override
    public void changeStatus(int id) {

    }

    @Override
    public void updatePermission(int roleId, int menuId, int permission) {

    }
}
