package com.ap.SociaLite.Contract;

public interface SpotLightActivityContract {

    void fetch_profile(String user_id);

    void frnd_story(String UserId);

    void story_view_added(String user_id, String story_id);
}
