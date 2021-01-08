package com.ap.SociaLite.Contract;

public interface SpotlightActivityForUserContract {

    void fetch_profile(String user_id);


    void my_all_storys(String user_id);
    //(spot light)


    void view_story_viewer(String user_id, String story_id);
}
