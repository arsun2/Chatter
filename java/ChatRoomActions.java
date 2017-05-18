package com.example.sunaustin8.chatter;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by sunaustin8 on 5/2/17.
 */

public class ChatRoomActions {

    /** Each string key of a participant is their name added with a 5 digit code
     * that is the number of particpants in the hashmap of participants of that chatRoom
     * @param chatRoom chatRoom parameter of which we use the participant number
     * @return appropriate String concatenation for the chatRoom based on its number of participants
     */
    public static String getCountSuffix(ChatRoom chatRoom) {
        int count = chatRoom.participants.size() + 1;
        if(count >= 10000) {
            return "" + count;
        }
        else if(count < 10) {
            return "0000" + count;
        }
        else if(count < 100) {
            return "000" + count;
        }
        else if(count <- 1000) {
            return "00" + count;
        }
        else
            return "0" + count;
    }

    /** Goes through Hashmap of participants, and then adds them in order to a String ArrayList
     * after combing them with a "%$" concatenation
     * @param chatRoom chatRoom of which we are getting the participants from
     * @return ordered String ArrayList of messages formed from the merge of the key and value of each participant
     */
    public static ArrayList<String> getMessageOrder(ChatRoom chatRoom) {
        ArrayList<String> orderedMessages = new ArrayList<>();
        final String concat = "%$";
        for (int i = 1; i <= chatRoom.participants.size(); i++)
        {
            for (String key : chatRoom.participants.keySet()) {
                int valueStart = key.indexOf("0");
                int value = Integer.parseInt(key.substring(valueStart));
                if(value == i)
                {
                    orderedMessages.add(key + concat + chatRoom.participants.get(key));
                }
            }
        }
        return orderedMessages;
    }

    /** takes the encrypted Hashmap of Participants and messages and returns a single
     * string of them separated by a space.
     * @param chatRoom
     * @return a single string of all of the participants separated by a space.
     */
    public static String getListOfPar(ChatRoom chatRoom) {
        ArrayList<String> part = new ArrayList<>();
        String result = "";
        for (String key : chatRoom.participants.keySet()) {
            String parName = key.substring(0, key.length() - 5);
            if(!part.contains(parName)) {
                part.add(parName);
            }
        }
        for(int i = 0; i < part.size(); i++) {
            result += ", " + part.get(i);
        }
        return result;
    }

    /** Takes a ChatRoom, get its HashMap of Participants
     * and returns and ordered ArrayList of Participants
     * @param chatRoom chatroom whose Hashmap is going to be sorted
     * @return ordered ArrayList of Participants of speaking order
     */
    public static ArrayList<Participant> getParOrder(ChatRoom chatRoom) {
        ArrayList<Participant> orderedPar = new ArrayList<>();
        final String concat = "%$";
        for (int i = 1; i <= chatRoom.participants.size(); i++)
        {
            for (String key : chatRoom.participants.keySet()) {
                int valueStart = key.indexOf("0");
                int value = Integer.parseInt(key.substring(valueStart));
                if(value == i)
                {
                    Participant newPar = new Participant(key, chatRoom.participants.get(key));
                    orderedPar.add(newPar);
                }
            }
        }
        return orderedPar;
    }
}
