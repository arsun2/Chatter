package com.example.sunaustin8.chatter;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.common.api.Status;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by sunaustin8 on 4/25/17.
 */

public class CreateActivity extends AppCompatActivity {

    private Button mStartButton;
    private EditText mRoomName;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference chatterRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_chat_activity);

        //configures variables to their corresponding widgets
        mStartButton = (Button) findViewById(R.id.startButton);
        mRoomName = (EditText) findViewById(R.id.roomName);

        //gets the string name from user input and pushes a new ChatRoom with that name to the database
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roomName = mRoomName.getText().toString();
                ChatRoom newRoom = new ChatRoom(roomName);
                chatterRef.push().setValue(newRoom);
                finish();;
            }
        });
    }
}
