package net.oriserver.aether.aether.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlaySoundEvent extends Event {//サウンドインベントリーをクリックした時のEventを入手するクラス

    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final int slot;
    public PlaySoundEvent(Player p, int slot) {
        this.player = p;
        this.slot = slot;
    }
    public Player getPlayer(){return player;}
    public int getSlot(){return slot;}

    @Override
    public HandlerList getHandlers(){return handlers;}
    public static HandlerList getHandlerList() {return handlers;}

}