package com.ap.SociaLite.Contract;

public interface BusinessFragmentContract {

    void fetch_all_intrest(String user_id);
    //post page dashboard category + icon

    void update_new_intrests(String user_id, String interest_id);
    //update new  intrests (on plus icon click)
}
