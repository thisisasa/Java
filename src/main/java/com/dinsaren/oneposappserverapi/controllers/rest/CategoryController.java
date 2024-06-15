package com.dinsaren.oneposappserverapi.controllers.rest;

import com.dinsaren.oneposappserverapi.exception.AppException;
import com.dinsaren.oneposappserverapi.models.Category;
import com.dinsaren.oneposappserverapi.models.req.BasePostReq;
import com.dinsaren.oneposappserverapi.models.res.MessageRes;
import com.dinsaren.oneposappserverapi.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/app/category")
@Slf4j
@PreAuthorize("hasRole('USER') or hasRole('CUSTOMER') or hasRole('ADMIN') or hasRole('MERCHANT')")
public class CategoryController {
    private final CategoryService categoryService;
    private MessageRes messageRes;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/list")
    public ResponseEntity<Object> getAll(@RequestBody BasePostReq req) {
        try {
            log.info("Intercept get all categories req {}", req);
            List<Category> categories = categoryService.findAllCategoryByStatus(req.getStatus());
            messageRes = new MessageRes();
            messageRes.setSuccess(categories);
            return new ResponseEntity<>(messageRes, HttpStatus.OK);
        } catch (AppException e) {
            log.error("Error get all categories {}", e.toString());
            return new ResponseEntity<>(new MessageRes(e.getErrorCode(), e.getMessage(), null), e.getHttpStatus());
        } catch (Exception e) {
            log.error("Error internal error get all categories {}", e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> getById(@RequestBody BasePostReq req, @PathVariable("id") Integer id) {
        try {
            log.info("Intercept get category by req {}", req);
            Category category = categoryService.findById(id);
            messageRes = new MessageRes();
            messageRes.setSuccess(category);
            return new ResponseEntity<>(messageRes, HttpStatus.OK);
        } catch (AppException e) {
            log.error("Error get category by id {}", e.toString());
            return new ResponseEntity<>(new MessageRes(e.getErrorCode(), e.getMessage(), null), e.getHttpStatus());
        } catch (Exception e) {
            log.error("Error internal error get category by id {}", e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody Category req) {
        try {
            log.info("Intercept create category req {}", req);
            categoryService.save(req);
            messageRes = new MessageRes();
            messageRes.setCreateSuccess("Create success");
            return new ResponseEntity<>(messageRes, HttpStatus.OK);
        } catch (AppException e) {
            log.error("Error create category : {}", e.toString());
            return new ResponseEntity<>(new MessageRes(e.getErrorCode(), e.getMessage(), null), e.getHttpStatus());
        } catch (Exception e) {
            log.error("Error internal error create category : {}", e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Object> update(@RequestBody Category req) {
        try {
            log.info("Intercept update category req {}", req);
            categoryService.update(req);
            messageRes = new MessageRes();
            messageRes.setUpdateSuccess("Update success");
            return new ResponseEntity<>(messageRes, HttpStatus.OK);
        } catch (AppException e) {
            log.error("Error update category : {}", e.toString());
            return new ResponseEntity<>(new MessageRes(e.getErrorCode(), e.getMessage(), null), e.getHttpStatus());
        } catch (Exception e) {
            log.error("Error internal error update category : {}", e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> delete(@RequestBody Category req) {
        try {
            log.info("Intercept delete category req {}", req);
            categoryService.update(req);
            messageRes = new MessageRes();
            messageRes.setUpdateSuccess("Delete success");
            return new ResponseEntity<>(messageRes, HttpStatus.OK);
        } catch (AppException e) {
            log.error("Error delete category : {}", e.toString());
            return new ResponseEntity<>(new MessageRes(e.getErrorCode(), e.getMessage(), null), e.getHttpStatus());
        } catch (Exception e) {
            log.error("Error internal error delete brand : {}", e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
