package com.dinsaren.oneposappserverapi.repository;


import com.dinsaren.oneposappserverapi.models.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByStatusOrderByIdDesc(String status, Pageable pageable);

    List<Post> findAllByStatusInAndCategory_IdOrderByIdDesc(List<String> stringList, Pageable pageable, Integer cateId);

    List<Post> findAllByStatusInOrderByIdDesc(List<String> statusList,  Pageable pageable);

    List<Post> findAllByStatusAndUser_IdOrderById(String status, Integer userId, Pageable pageable);

    List<Post> findByStatusAndUser_IdAndCategory_IdOrderByIdDesc(String status, Integer userId, Pageable pageable, Integer catId);

    Optional<Post> findByIdAndStatus(Integer id, String status);

    int countAllByStatus(String status);

    List<Post> findByTitleLike(String title);
}
