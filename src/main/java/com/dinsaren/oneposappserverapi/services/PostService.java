package com.dinsaren.oneposappserverapi.services;

import com.dinsaren.oneposappserverapi.exception.AppException;
import com.dinsaren.oneposappserverapi.models.Post;

import java.util.List;

public interface PostService {
    List<Post> findAll(String status, int limit, int page) throws AppException;

    List<Post> findAllByUserId(String status, int limit, int page, Integer userId,  Integer categoryId,  String name) throws AppException;

    Post findById(Integer id, String status) throws AppException;

    void save(Post post) throws AppException;

    void update(Post post) throws AppException;

    void delete(Post post) throws AppException;

}
