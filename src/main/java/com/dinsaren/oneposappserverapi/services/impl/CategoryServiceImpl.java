package com.dinsaren.oneposappserverapi.services.impl;

import com.dinsaren.oneposappserverapi.constants.Constants;
import com.dinsaren.oneposappserverapi.exception.AppException;
import com.dinsaren.oneposappserverapi.models.Category;
import com.dinsaren.oneposappserverapi.models.User;
import com.dinsaren.oneposappserverapi.repository.CategoryRepository;
import com.dinsaren.oneposappserverapi.services.AuthenticationUtilService;
import com.dinsaren.oneposappserverapi.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final AuthenticationUtilService authenticationUtilService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, AuthenticationUtilService authenticationUtilService) {
        this.categoryRepository = categoryRepository;
        this.authenticationUtilService = authenticationUtilService;
    }


    @Override
    public List<Category> findAllCategoryByStatus(String status) throws AppException {
        if (status.equals("ALL")) {
            List<String> stringList = new ArrayList<>();
            stringList.add(Constants.STATUS_ACTIVE);
            stringList.add(Constants.STATUS_DELETE);
            return categoryRepository.findAllByStatusIn(stringList);
        }
        return categoryRepository.findAllByStatus(status);
    }

    @Override
    public Category findById(Integer id) throws AppException {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Category req) throws AppException {
        User user = authenticationUtilService.checkUser();
        req.setStatus(Constants.STATUS_ACTIVE);
        req.setCreateAt(new Date());
        req.setCreateBy(user.getUsername());
        categoryRepository.save(req);
    }

    @Override
    public void update(Category req) throws AppException {
        Category category = findById(req.getId());
        User user = authenticationUtilService.checkUser();
        if (category != null) {
            req.setStatus(Constants.STATUS_ACTIVE);
            req.setUpdateAt(new Date());
            req.setUpdateBy(user.getUsername());
            categoryRepository.save(req);

        }
    }

    @Override
    public void delete(Category req) throws AppException {
        Category category = findById(req.getId());
        User user = authenticationUtilService.checkUser();
        if (category != null) {
            req.setStatus(Constants.STATUS_DELETE);
            req.setUpdateAt(new Date());
            req.setUpdateBy(user.getUsername());
            categoryRepository.save(req);

        }
    }
}
