package com.example.sunaustin8.chatter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.api.Status;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by sunaustin8 on 4/9/17.
 */

public class ChatActivity extends AppCompatActivity {

    private ArrayList<ChatRoom> chatRoomList;
    public static Button createButton;
    public static RecyclerView mRecyclerView;
    private ChatAdapter chatAdapter;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference chatterRef = database.getReference();
    private DatabaseReference currentRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);

        //intializes the RecyclerView with the ChatAdapter
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));

        chatRoomList = new ArrayList<>();
        chatAdapter = new ChatAdapter(chatRoomList);

        mRecyclerView.setAdapter(chatAdapter);

        //adds Listener to the Reference for any data changes. sets the key value for each
        // room to the reference of that room. Also adds each room to chatRoomList
        chatterRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                chatRoomList.clear();
                for(DataSnapshot snapShot: dataSnapshot.getChildren()) {
                    ChatRoom currentRoom = snapShot.getValue(ChatRoom.class);
                    String key = snapShot.getKey();
                    currentRoom.setKey(snapShot.getKey());
                    ChatRoom tempRoom = new ChatRoom(currentRoom.getName(), key, currentRoom.getParticipants() );
                    currentRoom = tempRoom;
                    chatRoomList.add(currentRoom);
                    chatterRef.child(key).child("key").setValue(key);
                }
                chatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        createButton = (Button) findViewById(R.id.chatButton);

        //opens the create new ChatRoom activity
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });
    }
}


