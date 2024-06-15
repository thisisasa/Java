package com.dinsaren.oneposappserverapi.services;

import com.dinsaren.oneposappserverapi.constants.Constants;
import com.dinsaren.oneposappserverapi.constants.ErrorCode;
import com.dinsaren.oneposappserverapi.exception.AppException;
import com.dinsaren.oneposappserverapi.models.User;
import com.dinsaren.oneposappserverapi.repository.UserRepository;
import com.dinsaren.oneposappserverapi.security.services.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthenticationUtilService {
    private final UserRepository userRepository;

    public AuthenticationUtilService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User checkUser() throws AppException {
        log.info("Intercept get authentication");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Optional<User> user = userRepository.findByPhoneNumberAndStatus(userDetails.getPhoneNumber(), Constants.STATUS_ACTIVE);
            if (user.isPresent()) {
                log.info("Get authentication success {}", authentication);
                return user.get();
            }
        }else {
            throw new AppException(HttpStatus.BAD_GATEWAY, ErrorCode.USER_NOT_PERMISSION, "User don't have permission");
        }

        return null;
    }

}
