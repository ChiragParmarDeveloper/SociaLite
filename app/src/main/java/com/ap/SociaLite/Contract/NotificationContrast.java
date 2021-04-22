package com.ap.SociaLite.Contract;

public interface NotificationContrast {
    void user_notification_list (String UserId);

    void request_accept (String UserId, String RequestId);

    void request_denied (String UserId, String RequestId);
}
