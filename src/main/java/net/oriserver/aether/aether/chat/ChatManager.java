package net.oriserver.aether.aether.chat;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class ChatManager {

    private final HashMap<String, ChatRoom> chatRooms = new HashMap<>();
    private final HashMap<Player, ChatRoom> playerRooms = new HashMap<>();
    private final RomanjiToJapanese romanjiToJapanese;

    public ChatManager(){
        chatRooms.put("General",new ChatRoom("General", false));
        chatRooms.put("Admin",new ChatRoom("Admin", true));
        romanjiToJapanese = new RomanjiToJapanese();
    }
    public HashMap<String, ChatRoom> getChatrooms() {
        return chatRooms;
    }
    public HashMap<Player, ChatRoom> getPlayerRooms() {
        return playerRooms;
    }
    public String getJapanese(String romanji){
        return romanjiToJapanese.setJapanese(romanji);
    }
}
