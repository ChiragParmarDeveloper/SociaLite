package com.ap.SociaLite.Contract;

public interface NetworkFragmentContrast {

    void my_network_post(String UserId);

    void hide_post(String user_id, String post_id);

    void category_save_post(String user_id, String post_id);
    void rating_post(String user_id, String post_id, String rate);
}
