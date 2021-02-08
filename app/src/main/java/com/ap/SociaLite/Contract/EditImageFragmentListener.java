package com.ap.SociaLite.Contract;

public interface EditImageFragmentListener {

    void onBrightnessChanged(int brightness);

    void onSaturationChanged(float saturation);

    void onContrastChanged(float contrast);

    void onEditStarted();

    void onEditCompleted();


}
