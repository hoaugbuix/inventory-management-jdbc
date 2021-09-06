package com.hoangbuix.dev.dao.impl;

import com.hoangbuix.dev.dao.MenuDAO;
import com.hoangbuix.dev.entity.MenuEntity;
import com.hoangbuix.dev.model.dto.MenuDTO;
import com.hoangbuix.dev.model.mapper.MenuMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class MenuDAOImpl extends BaseDAOImpl<MenuDTO> implements MenuDAO<MenuDTO> {

    @Override
    public List<MenuDTO> findAll() {
        String sql = "call menuFindAll()";
        return query(sql, new MenuMapper());
    }

    @Override
    public MenuDTO findById(int id) {
        String sql = "call menu_findById(?)";
        List<MenuDTO> menu = query(sql, new MenuMapper(), id);
        return menu.isEmpty() ? null : menu.get(0);
    }

    @Override
    public int save(MenuDTO menu) {
        String sql = "call menu_create(?, ?,?,?,?,?,?)";
        return insert(sql, menu.getParentId(), menu.getUrl(), menu.getName(), menu.getOrderIndex(),
                menu.getActiveFlag(), menu.getCreatedDate(), menu.getUpdatedDate());
    }

    @Override
    public void update(MenuDTO menu) {
        String sql = "call menu_update(?,?,?,?,?,?)";
        update(sql, menu.getParentId(), menu.getUrl(), menu.getName(), menu.getOrderIndex(),
                menu.getActiveFlag(), menu.getUpdatedDate());
    }


}
