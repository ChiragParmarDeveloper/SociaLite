package com.ap.SociaLite.Contract;

public interface InterestFragmentContract {

    void fetch_all_intrest(String user_id);
    //post page dashboard category + icon

    void update_new_intrests(String user_id, String interest_id);
    //update new  intrests (on plus icon click)

    void fetch_my_intrest(String user_id);

    void fetch_my_intrest_wise_post(String user_id,String interest_id);

    void hide_post(String user_id, String post_id);

    void category_save_post(String user_id, String post_id);

    void rating_post(String user_id, String post_id, String rate);

    void remove_interest(String user_id,String interest_ids);
}
