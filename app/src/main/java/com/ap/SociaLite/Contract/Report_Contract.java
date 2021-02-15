package com.ap.SociaLite.Contract;

public interface Report_Contract {

    void report_list();

    void report_post(String user_id, String post_id, String reason);
}
