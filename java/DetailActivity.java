package com.example.sunaustin8.chatter;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

/**
 * Created by sunaustin8 on 5/2/17.
 */

public class DetailActivity extends AppCompatActivity {

    private Button mColorButton;
    private Button mBlueButton;
    private Button mGoogleButton;
    private EditText mGoogleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        mColorButton = (Button) findViewById(R.id.redButton);
        mBlueButton = (Button) findViewById(R.id.blueButton);
        mGoogleButton = (Button) findViewById(R.id.googleButton);
        mGoogleText = (EditText) findViewById(R.id.googleEditText);

        //takes in the String userinput and adds that to the search URL, then
        //starts the intent of the new URL after the button is clicked
        mGoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String googleSearch = mGoogleText.getText().toString();
                String googleURL = "http://lmgtfy.com/?q=" + googleSearch;
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(googleURL));
                startActivity(intent);
            }
        });

        //generates several different random colors and assigns them to various widgets throughout the app
        mColorButton.setOnClickListener(new View.OnClickListener() {
            Random randomNum = new Random();
            int color = Color.argb(255, randomNum.nextInt(256), randomNum.nextInt(256), randomNum.nextInt(256));
            int color1 = Color.argb(255, randomNum.nextInt(256), randomNum.nextInt(256), randomNum.nextInt(256));
            int color2 = Color.argb(255, randomNum.nextInt(256), randomNum.nextInt(256), randomNum.nextInt(256));
            int color3 = Color.argb(255, randomNum.nextInt(256), randomNum.nextInt(256), randomNum.nextInt(256));
            int color4 = Color.argb(255, randomNum.nextInt(256), randomNum.nextInt(256), randomNum.nextInt(256));

            @Override
            public void onClick(View v) {
                ChatActivity.createButton.setBackgroundColor(color);
                mColorButton.setBackgroundColor(color1);
                ChatActivity.mRecyclerView.setBackgroundColor(color2);
                MessageActivity.mEditButton.setBackgroundColor(color4);
                MessageActivity.mNewMessage.setBackgroundColor(color);
                MessageActivity.mRecyclerView.setBackgroundColor(color1);
                MessageActivity.mMessageButton.setBackgroundColor(color3);
                MainActivity.mChatButton.setBackgroundColor(color1);
                mGoogleButton.setBackgroundColor(color);
                mBlueButton.setBackgroundColor(color3);
                mGoogleText.setBackgroundColor(color4);

            }
        });

        //sets many widgets throughout the app to a blue color
        mBlueButton.setOnClickListener(new View.OnClickListener() {
            Random randomNum = new Random();
            int color = 0xff0000ff;

            @Override
            public void onClick(View v) {
                ChatActivity.createButton.setBackgroundColor(color);
                mColorButton.setBackgroundColor(color);
                mBlueButton.setBackgroundColor(color);
                mGoogleText.setBackgroundColor(color);
                mGoogleButton.setBackgroundColor(color);
                ChatActivity.mRecyclerView.setBackgroundColor(color);
                MessageActivity.mEditButton.setBackgroundColor(color);
                MessageActivity.mNewMessage.setBackgroundColor(color);
                MessageActivity.mRecyclerView.setBackgroundColor(color);
                MessageActivity.mMessageButton.setBackgroundColor(color);
                MainActivity.mChatButton.setBackgroundColor(color);
            }
        });
    }
}
