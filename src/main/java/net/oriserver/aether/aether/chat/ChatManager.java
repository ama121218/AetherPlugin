package net.oriserver.aether.aether.chat;

import org.bukkit.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ChatManager {
    private final HashMap<String, ChatRoom> chatRooms = new HashMap<>();
    private final HashMap<Player, ChatRoom> playerRooms = new HashMap<>();
    private final RomanjiToJapanese romanjiToJapanese;
    @Autowired
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
