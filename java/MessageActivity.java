package com.example.sunaustin8.chatter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by sunaustin8 on 4/25/17.
 */

public class MessageActivity extends AppCompatActivity {

    public static Button mMessageButton;
    public static Button mEditButton;
    public static EditText mNewMessage;
    public static  RecyclerView mRecyclerView;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference chatterRef = database.getReference();
    private ArrayList<Participant> parList;
    private MessageAdapter messageAdapter;
    private final String CONCAT = "%$";
    private String roomKey;
    private ChatRoom workRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatroom_activity);

        //configures widgets and recyclerviews to their xml correspondent
        mEditButton = (Button) findViewById(R.id.editButton);
        mMessageButton = (Button) findViewById(R.id.messageButton);
        mNewMessage = (EditText) findViewById(R.id.newMessage);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));

        //gets the ChatRoom that was just entered into from the intent
        Intent intent  = getIntent();
        final ChatRoom chatRoom = intent.getParcelableExtra(ChatAdapter.CHATROOM);

        //configures recyclerview with the MessageAdapter
        parList = new ArrayList<>();
        parList = ChatRoomActions.getParOrder(chatRoom);
        messageAdapter = new MessageAdapter(parList);
        mRecyclerView.setAdapter(messageAdapter);

        //attaches a listener to the root Reference for any data changes. Also stores the currentRoom and
        //its key as class variables and gets the ordered ArrayList of Participants from the currentRoom
        chatterRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                parList.clear();
                for(DataSnapshot snapShot: dataSnapshot.getChildren()) {
                    ChatRoom currentRoom = snapShot.getValue(ChatRoom.class);
                    if(currentRoom.getName().equals(chatRoom.getName())) {
                        roomKey = currentRoom.getKey();
                        workRoom = currentRoom;
                        parList = ChatRoomActions.getParOrder(currentRoom);
                    }
                }
                MessageAdapter newMAdapter = new MessageAdapter(parList);
                mRecyclerView.setAdapter(newMAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        //opens the edit Detail Activity upon the clicking of this button
        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessageActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });

        //takes in the new message in the edit text field and properly concatendates to it for a
        // new Participant object. Then pushes the new Participant to Firebase
        mMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                String message = mNewMessage.getText().toString();
                                String suffix = ChatRoomActions.getCountSuffix(workRoom);
                                String namePar = MainActivity.accountPar.getName() + suffix;
                                String parFinal = namePar.substring(14);
                                Participant newPar = new Participant(parFinal, message);
                                parList.add(newPar);
                                database.getReference(roomKey).child("participants").child(newPar.getName()).setValue(message);
                                for(int i = 0; i < parList.size(); i++) {
                                    System.out.println(parList.get(i));
                                }
                            }
                        });
                        messageAdapter.notifyDataSetChanged();
                    }

    }


