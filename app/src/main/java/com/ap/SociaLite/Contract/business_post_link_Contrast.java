package com.ap.SociaLite.Contract;

public interface business_post_link_Contrast {

    void business_post_link(String user_id,String post_id);

    void hide_post(String user_id, String post_id);

    void category_save_post(String user_id, String post_id);
    void rating_post(String user_id, String post_id, String rate);


    void interest_button(String user_id,String post_id);
    void remove_interest_button(String user_id,String post_id);
}
