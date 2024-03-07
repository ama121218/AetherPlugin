package net.oriserver.aether.aether.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;

public class AnvilClickEvent extends Event {//ステージを作成するときに名前を変更するためにかな床インベントリーのEventを入手
    private static final HandlerList handlers = new HandlerList();
    private InventoryClickEvent e;


    public AnvilClickEvent(InventoryClickEvent e) {
        this.e = e;
    }
    public InventoryClickEvent getEvent(){
        return e;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}