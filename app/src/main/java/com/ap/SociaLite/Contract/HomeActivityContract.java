package com.ap.SociaLite.Contract;

public interface HomeActivityContract {

    void fetch_profile(String user_id);

    void save_token(String user_id,String token_id);

    void user_notification_list (String UserId);
}
