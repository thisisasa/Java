package com.dinsaren.oneposappserverapi.services;

import com.dinsaren.oneposappserverapi.models.notification.PushNotificationRequest;

public interface PushNotificationService {
    void sendPushNotification(PushNotificationRequest req);
}
