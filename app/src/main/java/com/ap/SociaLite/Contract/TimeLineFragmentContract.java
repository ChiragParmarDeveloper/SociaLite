package com.ap.SociaLite.Contract;

public interface TimeLineFragmentContract {


    void time_line_post(String user_id);

    void hide_post(String user_id,String post_id);

    void category_save_post(String user_id,String post_id);

    void rating_post(String user_id,String post_id,String rate);


}
