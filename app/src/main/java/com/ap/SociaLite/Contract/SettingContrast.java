package com.ap.SociaLite.Contract;

public interface SettingContrast {
    void fetch_profile(String user_id);

    void notification_on_off (String user_id,String is_toggle);
}
