package com.example.sunaustin8.chatter;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;
/**
 * Created by sunaustin8 on 5/2/17.
 */

public class ChatterTest {

    private ChatRoom testRoom;
    private String name = "oobleck";
    private HashMap<String, String> testPar = new HashMap<>();
    private ChatRoom testRoomB;
    private String nameB = "Finnagan";
    private HashMap<String, String> testParB = new HashMap<>();


    @Before
    public void initializeTestRoom() {
        testPar.put("Nick00004", "Nic");
        testPar.put("Chas00001", "Cha");
        testPar.put("Mukhuil00006", "Muk");
        testPar.put("Austin00002", "Aus");
        testPar.put("George00003", "Geo");
        testPar.put("Peter00005", "Pet");
        testRoom = new ChatRoom(name, testPar);

        testParB.put("Oliver00003", "chicken");
        testParB.put("Mickey00001", "Mic");
        testParB.put("Craig00002", "Cra");
        testRoomB = new ChatRoom(nameB, testParB);
    }

    @Test
    public void addPar() {
        assertEquals(testRoom.participants.size(), 6);
        assertEquals(testRoom.participants.get("Mukhuil00006"), "Muk");
        assertEquals(testRoom.participants.get("Peter00005"), "Pet");
    }

    @Test
    public void addParB() {
        assertEquals(testRoomB.participants.size(), 3);
        assertEquals(testRoomB.participants.get("Oliver00003"), "chicken");
        assertEquals(testRoomB.participants.get("Craig00002"), "Cra");
    }

    @Test
    public void testSuffix() {
        assertEquals(ChatRoomActions.getCountSuffix(testRoom), "00007");
        assertEquals(ChatRoomActions.getCountSuffix(testRoomB), "00004");
    }

    @Test
    public void testMessageOrder() {
        ArrayList<String> testOrder = new ArrayList<>();
        testOrder.add("Chas00001%$Cha");
        testOrder.add("Austin00002%$Aus");
        testOrder.add("George00003%$Geo");
        testOrder.add("Nick00004%$Nic");
        testOrder.add("Peter00005%$Pet");
        testOrder.add("Mukhuil00006%$Muk");

        ArrayList<String> product = ChatRoomActions.getMessageOrder(testRoom);
        for(int i = 0; i < testOrder.size(); i++) {
            assertEquals(testOrder.get(i), product.get(i));
        }
    }

    @Test
    public void testParList() {
        String testStringPar = ChatRoomActions.getListOfPar(testRoom);
        String testStringParB = ChatRoomActions.getListOfPar(testRoomB);
        assertEquals(", Mukhuil, George, Chas, Peter, Nick, Austin", testStringPar);
        assertEquals(", Mickey, Craig, Oliver", testStringParB);
    }
}
