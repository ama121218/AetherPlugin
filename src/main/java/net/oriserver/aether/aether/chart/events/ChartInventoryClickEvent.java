package net.oriserver.aether.aether.chart.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class ChartInventoryClickEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Player player;
    private Material material;
    private int slot;
    private Inventory inv;
    public ChartInventoryClickEvent(Player p, Material material, int slot, Inventory inv) {
        this.player = p;
        this.material = material;
        this.slot = slot;
        this.inv = inv;
    }
    public Player getPlayer() {return player;}
    public Material getMaterial() {return material;}
    public int getSlot() {return slot;}
    public Inventory getInventory() {return inv;}

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }

}

