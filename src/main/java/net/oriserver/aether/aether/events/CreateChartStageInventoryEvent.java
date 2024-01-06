package net.oriserver.aether.aether.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CreateChartStageInventoryEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Material material;
    private int slot;
    private String title;


    public CreateChartStageInventoryEvent(Player player, int slot, Material material,String title) {
        this.player = player;
        this.slot = slot;
        this.material = material;
        this.title = title;
    }

    public Player getPlayer() {return player;}
    public int getSlot() {return slot;}
    public Material getMaterial() {return material;}
    public String getTitle(){return title;}


    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
