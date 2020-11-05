package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ap.SociaLite.R;

public class MessageChatActivity extends AppCompatActivity {

    ImageView img_back;
    ImageView chat_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_chat);

        img_back = findViewById(R.id.img_back);
        chat_setting = findViewById(R.id.chat_setting);


        chat_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popup = new PopupMenu(MessageChatActivity.this,view);
                //Inflating the Popup using xml file

                popup.inflate(R.menu.chat_message_menu);

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.hide:
                                Toast.makeText(view.getContext(), "Clicked hide", Toast.LENGTH_SHORT).show();
                                //startActivity(new Intent(App.this, App_Main.class));
                                break;

                            case R.id.block:
                                Toast.makeText(view.getContext(), "Clicked block", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.delete:
                                Toast.makeText(view.getContext(), "Clicked delete", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.black_white:
                                Toast.makeText(view.getContext(), "Clicked black & white", Toast.LENGTH_SHORT).show();
                                break;

                            default:
                                return false;
                        }
                        // return true;
                        return false;
                    }
                });
                popup.show(); //showing popup menu
            }
        });




        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}