package com.ap.SociaLite.Contract;

public interface ProfileConnectionBusinessFragmentContrast {

    void my_post_business_intrection(String user_id);

    void hide_post(String user_id,String post_id);

    void category_save_post(String user_id,String post_id);

    void rating_post(String user_id,String post_id,String rate);
}
