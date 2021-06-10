package com.ap.SociaLite.Contract;

import java.util.ArrayList;

public interface ShareToFriendContrast {

    void friend_list(String UserId);

    void share_link_api(String user_id, ArrayList<String> shared_id,
                        String url, String type, String profile_share_id, String post_id);

}