package com.example.sunaustin8.chatter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sunaustin8 on 4/23/17.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private ArrayList<ChatRoom> chatRooms;
    public static final String CHATROOM = "CHAT";

    public ChatAdapter(ArrayList<ChatRoom> chatRooms) {
        this.chatRooms = chatRooms;
    }

    /** Called to fill screen with views until entire screen is filled.
     * Then it recycles after that
     * @param parent the parent object which is the RecyclerView
     * @param viewType different types of views in our parent which is our RecyclerView
     * @return New ViewHolder that we have allocated
     */
    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       final View chatListItem = LayoutInflater.from(parent.getContext()).
               inflate(R.layout.chat_information, parent, false);

        return new ChatViewHolder(chatListItem);
    }

    /** this method updates a ViewHolder every time it needs to hold data for a
     * different move. This updates everything in the RecyclerView
     * @param holder ViewHolder that has the View we need to update in the UI
     * @param position the index of current movie in ArrayList of chatRooms
     */
    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        final ChatRoom currentRoom = chatRooms.get(position);

        holder.titleView.setText(currentRoom.getName());
        holder.parView.setText("Partipants:" + ChatRoomActions.getListOfPar(currentRoom));

        // Launches the DetailActivity for each movie when each View is clicked
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MessageActivity.class);
                intent.putExtra(CHATROOM, currentRoom);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chatRooms.size();
    }

    //class for the Adapter that links references to all the subviews and widgets
    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView titleView;
        public TextView parView;

        public ChatViewHolder(View itemView) {
            super(itemView);

            view = itemView;
            titleView = (TextView) itemView.findViewById(R.id.speakerTextView);
            parView = (TextView) itemView.findViewById(R.id.parTextView);
        }
    }
}
