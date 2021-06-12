package com.ap.SociaLite.Contract;

public interface CategoryFragmentContract {

    void fetch_all_intrest(String user_id);
    //post page dashboard category + icon

    void Category_post_fragment(String user_id,String interest_id);

    void hide_post(String user_id, String post_id);

    void category_save_post(String user_id, String post_id);

    void rating_post(String user_id, String post_id, String rate);

    void update_new_intrests(String user_id, String interest_id);
    //update new  intrests (on plus icon click)

    void remove_interest(String user_id,String interest_ids);

}
