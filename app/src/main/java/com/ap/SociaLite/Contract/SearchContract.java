package com.ap.SociaLite.Contract;

public interface SearchContract {

    void all_user(String user_id);

    void send_request(String UserId, String RequestId);

}
