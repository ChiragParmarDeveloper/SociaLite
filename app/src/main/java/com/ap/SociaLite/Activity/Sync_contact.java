package com.ap.SociaLite.Activity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Presenter.Sync_contactPresenter;
import com.ap.SociaLite.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        ArrayList<String> contacts = new ArrayList<>();
        while (phones.moveToNext()) {
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contacts.add(phoneNumber);
            Log.d("phoneNumber", String.valueOf(contacts));
            new Sync_contactPresenter(this, this).contact_sync(contacts);
        }
        phones.close();
    }
}