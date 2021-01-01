package com.ap.SociaLite.Contract;

public interface CommentContract {

    void add_comment (String user_id,String post_id,String comment);


    void view_comment(String post_id);
}
