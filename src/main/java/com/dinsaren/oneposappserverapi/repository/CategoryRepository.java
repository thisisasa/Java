package com.dinsaren.oneposappserverapi.repository;

import com.dinsaren.oneposappserverapi.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllByStatus(String status);
    List<Category> findAllByStatusIn(List<String> stringList);
    Optional<Category> findByIdAndStatus(Integer id, String status);
}
