package net.oriserver.aether.aether.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class PlaySoundEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Player player;
    private int slot;
    public PlaySoundEvent(Player p, int slot) {
        this.player = p;
        this.slot = slot;
    }
    public Player getPlayer() {return player;}
    public int getSlot() {return slot;}

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }

}