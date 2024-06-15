package com.dinsaren.oneposappserverapi.services;

import com.dinsaren.oneposappserverapi.exception.AppException;
import com.dinsaren.oneposappserverapi.models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategoryByStatus(String status) throws AppException;

    Category findById(Integer id) throws AppException;

    void save(Category req) throws AppException;

    void update(Category req) throws AppException;

    void delete(Category req) throws AppException;


}
