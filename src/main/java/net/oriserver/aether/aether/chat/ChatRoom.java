package net.oriserver.aether.aether.chat;

import org.bukkit.entity.Player;

import java.util.HashSet;

public class ChatRoom {//ローマ字を日本語へ変換するクラス
    private final String name;
    private final boolean isPrivate;
    private final HashSet<Player> members = new HashSet<>();
    private Player owner;

    public ChatRoom(String name, boolean isPrivate, Player owner) {
        this.name = name;
        this.isPrivate = isPrivate;
        this.owner = owner;
        this.members.add(owner);
    }
    public ChatRoom(String name, boolean isPrivate) {
        this.name = name;
        this.isPrivate = isPrivate;
    }
    public String getName() {
        return this.name;
    }

    public void addMember(Player player) {
        this.members.add(player);
    }

    public void removeMember(Player player) {
        this.members.remove(player);
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public HashSet<Player> getMembers() {
        return members;
    }

    public Player getOwner() {
        return owner;
    }
    public void setOwner(Player owner) {
        this.owner = owner;
    }
}