package com.hoangbuix.dev.dao;

import java.util.List;

public interface ProductDAO<E> extends BaseDAO<E> {
    int saveProductInfo(E instance);
    void updateProductInfo(E instance);
    List<E> findProductInfoAll();
    E findProductInfoById(int id);
    E findProductInfoByCode(String code);
}
