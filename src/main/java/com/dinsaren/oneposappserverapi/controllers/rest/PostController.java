package com.dinsaren.oneposappserverapi.controllers.rest;

import com.dinsaren.oneposappserverapi.constants.Constants;
import com.dinsaren.oneposappserverapi.exception.AppException;
import com.dinsaren.oneposappserverapi.models.Post;
import com.dinsaren.oneposappserverapi.models.req.BasePostReq;
import com.dinsaren.oneposappserverapi.models.res.MessageRes;
import com.dinsaren.oneposappserverapi.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/app/post")
@Slf4j
@PreAuthorize("hasRole('USER') or hasRole('CUSTOMER') or hasRole('ADMIN') or hasRole('MERCHANT')")
public class PostController {
    private final PostService postService;
    private MessageRes messageRes;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/list")
    public ResponseEntity<Object> getAll(@RequestBody BasePostReq req) {
        try {
            log.info("Intercept get all post by user id req {}", req);
            List<Post> brands = postService.findAllByUserId(req.getStatus(), req.getLimit(), req.getPage(), req.getUserId(), req.getCategoryId(), req.getName());
            messageRes = new MessageRes();
            messageRes.setSuccess(brands);
            return new ResponseEntity<>(messageRes, HttpStatus.OK);
        } catch (AppException e) {
            log.error("Error get all post by user id {}", e.toString());
            return new ResponseEntity<>(new MessageRes(e.getErrorCode(), e.getMessage(), null), e.getHttpStatus());
        } catch (Exception e) {
            log.error("Error internal error get all post by user id {}", e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> getById(@RequestBody BasePostReq req) {
        try {
            log.info("Intercept get post by req {}", req);
            Post object = postService.findById(req.getId(), req.getStatus());
            messageRes = new MessageRes();
            messageRes.setSuccess(object);
            return new ResponseEntity<>(messageRes, HttpStatus.OK);
        } catch (AppException e) {
            log.error("Error get post by id {}", e.toString());
            return new ResponseEntity<>(new MessageRes(e.getErrorCode(), e.getMessage(), null), e.getHttpStatus());
        } catch (Exception e) {
            log.error("Error internal error get post by id {}", e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody Post req) {
        try {
            log.info("Intercept create post req {}", req);
            postService.save(req);
            messageRes = new MessageRes();
            messageRes.setCreateSuccess("Create success");
            return new ResponseEntity<>(messageRes, HttpStatus.OK);
        } catch (AppException e) {
            log.error("Error create post : {}", e.toString());
            return new ResponseEntity<>(new MessageRes(e.getErrorCode(), e.getMessage(), null), e.getHttpStatus());
        } catch (Exception e) {
            log.error("Error internal error create post : {}", e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Object> update(@RequestBody Post req) {
        try {
            log.info("Intercept update post req {}", req);
            postService.update(req);
            messageRes = new MessageRes();
            messageRes.setUpdateSuccess("Update success");
            return new ResponseEntity<>(messageRes, HttpStatus.OK);
        } catch (AppException e) {
            log.error("Error update post : {}", e.toString());
            return new ResponseEntity<>(new MessageRes(e.getErrorCode(), e.getMessage(), null), e.getHttpStatus());
        } catch (Exception e) {
            log.error("Error internal error post brand : {}", e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> delete(@RequestBody Post req) {
        try {
            log.info("Intercept delete post req {}", req);
            postService.update(req);
            messageRes = new MessageRes();
            messageRes.setUpdateSuccess("Delete success");
            return new ResponseEntity<>(messageRes, HttpStatus.OK);
        } catch (AppException e) {
            log.error("Error delete post : {}", e.toString());
            return new ResponseEntity<>(new MessageRes(e.getErrorCode(), e.getMessage(), null), e.getHttpStatus());
        } catch (Exception e) {
            log.error("Error internal error delete post : {}", e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
