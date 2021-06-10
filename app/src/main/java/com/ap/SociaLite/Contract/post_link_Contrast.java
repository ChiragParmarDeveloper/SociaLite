package com.ap.SociaLite.Contract;

public interface post_link_Contrast {

    void rating_post(String user_id, String post_id, String rate);

    void hide_post(String user_id, String post_id);

    void category_save_post(String user_id, String post_id);

    void post(String post_id);

}
