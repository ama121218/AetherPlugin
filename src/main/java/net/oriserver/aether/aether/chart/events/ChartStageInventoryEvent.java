package net.oriserver.aether.aether.chart.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ChartStageInventoryEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final Material material;
    private final int slot;
    private String item_name;
    public ChartStageInventoryEvent(Player player,Material material,int slot,String item_name) {
        this.player = player;
        this.material = material;
        this.slot = slot;
        this.item_name = item_name;
    }
    public Player getPlayer() {return player;}
    public Material getMaterial(){return material;}
    public int getSlot(){return slot;}
    public String getItem_name(){return item_name;}
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
}