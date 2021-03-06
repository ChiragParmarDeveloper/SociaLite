package com.ap.SociaLite.Activity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Presenter.Sync_contactPresenter;
import com.ap.SociaLite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Sync_contact extends AppCompatActivity {

    @BindView(R.id.rv_contact)
    public RecyclerView rv_contact;

    @BindView(R.id.progressbar)
    public ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_contact);
        ButterKnife.bind(this);
        getPhoneNumbers();

    }

    public void getPhoneNumbers() {
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);

        // ArrayList<String> contacts = new ArrayList<>();
        // contacts.add("9879073207");
        while (phones.moveToNext()) {
            //  String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            //  String phoneNumber = "9879073207";
            //  contacts.add(phoneNumber);
       //     Log.d("phoneNumber", phoneNumber);
            new Sync_contactPresenter(this, this).contact_sync(phoneNumber);
        }
        phones.close();
    }

    @OnClick({R.id.img_back})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

        }
    }
}