package com.ap.SociaLite.Contract;

public interface CategoryFragmentContract {

    void fetch_all_intrest(String user_id);
    //post page dashboard category + icon

    void Category_post_fragment(String user_id);

    void hide_post(String user_id,String post_id);

    void category_save_post(String user_id,String post_id);

    void rating_post(String user_id,String post_id,String rate);



}
