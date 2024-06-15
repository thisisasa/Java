package com.dinsaren.oneposappserverapi.controllers.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/app/user/utils")
@Slf4j
@PreAuthorize("hasRole('USER') or hasRole('CUSTOMER') or hasRole('ADMIN')")
public class UtilsController {

}
