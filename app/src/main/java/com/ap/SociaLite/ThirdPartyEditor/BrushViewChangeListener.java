package com.ap.SociaLite.ThirdPartyEditor;



interface BrushViewChangeListener {
    void onViewAdd(BrushDrawingView brushDrawingView);

    void onViewRemoved(BrushDrawingView brushDrawingView);

    void onStartDrawing();


    void onStopDrawing();
}
