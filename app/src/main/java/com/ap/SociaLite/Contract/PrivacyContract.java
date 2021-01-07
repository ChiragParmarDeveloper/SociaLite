package com.ap.SociaLite.Contract;

public interface PrivacyContract {

    void private_account(String user_id,String is_private_account);

    void fetch_profile(String user_id);
}
