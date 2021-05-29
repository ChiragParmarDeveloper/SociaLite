package com.ap.SociaLite.Contract;

import android.widget.Toast;

import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public interface Sync_contactContrats {

  //  void contact_sync(ArrayList<String> contacts);

    void contact_sync(String contacts);
}


