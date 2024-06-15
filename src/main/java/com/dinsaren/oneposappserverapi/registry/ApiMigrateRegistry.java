package com.dinsaren.oneposappserverapi.registry;

import com.dinsaren.oneposappserverapi.caches.SmsCache;
import com.dinsaren.oneposappserverapi.services.OtpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ApiMigrateRegistry {

    private final OtpService otpService;

    public ApiMigrateRegistry(OtpService otpService) {
        this.otpService = otpService;
    }

    public void loadComponent() {
        log.info("Loading component ...");
        this.loadResponseCode();
    }

    private void loadResponseCode() {
        SmsCache.init();
        log.info("Init otp service ...");
        this.otpService.init();
    }

}
