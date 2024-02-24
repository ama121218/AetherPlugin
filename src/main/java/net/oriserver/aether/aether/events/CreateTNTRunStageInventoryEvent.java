package net.oriserver.aether.aether.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CreateTNTRunStageInventoryEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final int slot;
    public CreateTNTRunStageInventoryEvent(Player player,int slot) {
        this.player = player;
        this.slot = slot;
    }
    public Player getPlayer(){return player;}
    public int getSlot(){return slot;}

    @Override
    public HandlerList getHandlers() {return handlers;}

    public static HandlerList getHandlerList() {return handlers;}
}