package com.dinsaren.oneposappserverapi.services.impl;

import com.dinsaren.oneposappserverapi.constants.Constants;
import com.dinsaren.oneposappserverapi.exception.AppException;
import com.dinsaren.oneposappserverapi.models.Category;
import com.dinsaren.oneposappserverapi.models.Post;
import com.dinsaren.oneposappserverapi.models.User;
import com.dinsaren.oneposappserverapi.models.notification.Notification;
import com.dinsaren.oneposappserverapi.models.notification.NotificationData;
import com.dinsaren.oneposappserverapi.models.notification.PushNotificationRequest;
import com.dinsaren.oneposappserverapi.repository.CategoryRepository;
import com.dinsaren.oneposappserverapi.repository.PostRepository;
import com.dinsaren.oneposappserverapi.services.AuthenticationUtilService;
import com.dinsaren.oneposappserverapi.services.PostService;
import com.dinsaren.oneposappserverapi.services.PushNotificationService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PushNotificationService pushNotificationService;
    private final PostRepository postRepository;
    private final AuthenticationUtilService authenticationUtilService;

    private final CategoryRepository categoryRepository;

    public PostServiceImpl(PushNotificationService pushNotificationService, PostRepository postRepository, AuthenticationUtilService authenticationUtilService, CategoryRepository categoryRepository) {
        this.pushNotificationService = pushNotificationService;
        this.postRepository = postRepository;
        this.authenticationUtilService = authenticationUtilService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Post> findAll(String status, int limit, int page) throws AppException {
//        Pageable pageable = PageRequest.of(page, limit);
//        if (status.equals("ALL")) {
//            List<String> stringList = new ArrayList<>();
//            stringList.add(Constants.STATUS_ACTIVE);
//            stringList.add(Constants.STATUS_DELETE);
//            return postRepository.findAllByStatusInAndUserIdOrderByIdDesc(stringList, pageable);
//        } else {
//            return postRepository.findAllByStatusOrderByIdDesc(status, pageable);
//        }
        return null;
    }

    @Override
    public List<Post> findAllByUserId(String status, int limit, int page, Integer userId, Integer categoryId, String name) throws AppException {
        authenticationUtilService.checkUser();
        Pageable paging = PageRequest.of(page, limit);
        if (name != null && !name.equals("")) {
            return postRepository.findByTitleLike("%"+name+"%");
        }
        if (userId != 0) {
            if (categoryId == 0) {
                return postRepository.findAllByStatusAndUser_IdOrderById(status, userId, paging);
            } else {
                return postRepository.findByStatusAndUser_IdAndCategory_IdOrderByIdDesc(status, userId, paging, categoryId);
            }
        } else {
            if (status.equals("ALL")) {
                List<String> stringList = new ArrayList<>();
                stringList.add(Constants.STATUS_ACTIVE);
                stringList.add(Constants.STATUS_DELETE);
                if (categoryId == 0) {
                    return postRepository.findAllByStatusInOrderByIdDesc(stringList, paging);
                } else {
                    return postRepository.findAllByStatusInAndCategory_IdOrderByIdDesc(stringList, paging, categoryId);
                }
            } else {
                return postRepository.findAllByStatusOrderByIdDesc(status, paging);
            }
        }

    }

    @Override
    public Post findById(Integer id, String status) throws AppException {
        Post post = postRepository.findByIdAndStatus(id, status).orElse(null);
        if (post != null) {
            post.setTotalView(post.getTotalView() + 1);
            postRepository.saveAndFlush(post);
        }
        return post;
    }

    @Override
    public void save(Post post) throws AppException {
        User user = authenticationUtilService.checkUser();
        post.setUser(user);
        post.setCreateAt(new Date());
        post.setCreateBy(user.getUsername());
        post.setStatus(Constants.STATUS_ACTIVE);
        Category category = categoryRepository.findById(post.getCategory().getId()).orElse(null);
        post.setCategory(category);
        Post create = postRepository.save(post);
        if (create.getId() > 0) {
            PushNotificationRequest request = new PushNotificationRequest();
            request.setTo("/topics/FREE_POST_APP");
            Notification notification = new Notification();
            notification.setTitle(post.getTitle());
            notification.setBody(post.getDescription());
            notification.setSound("default");
            NotificationData notificationData = new NotificationData();
            notificationData.setImageUrl(post.getImage());
            notificationData.setPostId(create.getId().toString());
            request.setData(notificationData);
            request.setNotification(notification);
            pushNotificationService.sendPushNotification(request);
        }
    }

    @Override
    public void update(Post post) throws AppException {
        User user = authenticationUtilService.checkUser();
        post.setCreateAt(new Date());
        post.setUpdateBy(user.getUsername());
        Category category = categoryRepository.findById(post.getCategory().getId()).orElse(null);
        post.setCategory(category);
        postRepository.save(post);
    }

    @Override
    public void delete(Post post) throws AppException {
        User user = authenticationUtilService.checkUser();
        post.setCreateAt(new Date());
        post.setUpdateBy(user.getUsername());
        post.setStatus(Constants.STATUS_DELETE);
        postRepository.save(post);
    }
}
