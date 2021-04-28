package com.ap.SociaLite.Contract;

public interface HiddedPostDetailContract {

    void view_hided_post(String user_id);

    void category_save_post(String user_id, String post_id);
    void rating_post(String user_id, String post_id, String rate);


    void unhide_post(String user_id, String post_id);
}
