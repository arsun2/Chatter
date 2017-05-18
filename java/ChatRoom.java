package com.example.sunaustin8.chatter;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sunaustin8 on 4/16/17.
 */

public class ChatRoom implements Parcelable {

    String name;
    String key;
    HashMap<String, String> participants = new HashMap<String, String>();

    public ChatRoom() {}

    public ChatRoom(String name) { this.name = name; this.participants = participants;}

    public ChatRoom(String name, HashMap<String, String> participants){
        this.name = name;
        this.participants = participants;
    }

    public ChatRoom(String name, String key, HashMap<String, String> participants) {
        this.name = name;
        this.key = key;
        this.participants = participants;
    }

    public ChatRoom(HashMap<String, String> participants) {
        this.participants = participants;
    }

    public HashMap<String, String> getParticipants() {
        return this.participants;
    }

    public void addParticipant(Participant newPart, String message) {
        participants.put(newPart.getName(), message);
    }

    public String getParticipantMessage(String parName) {
        return participants.get(parName);
    }

    public String getName() { return this.name; }

    public void setKey(String newKey) { this.key = newKey; }

    public String getKey() { return this.key; }

    private int getMessageCount() { return this.participants.size(); }

    //parcel methods for the Adapter
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    protected ChatRoom(Parcel in) {
        this.name = in.readString();
    }

    public static final Creator<ChatRoom> CREATOR = new Creator<ChatRoom>() {
        @Override
        public ChatRoom createFromParcel(Parcel parcel) {
            return new ChatRoom(parcel);
        }

        @Override
        public ChatRoom[] newArray(int size) {
            return new ChatRoom[size];
        }
    };

}
