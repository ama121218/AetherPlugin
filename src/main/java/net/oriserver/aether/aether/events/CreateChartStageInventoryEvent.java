package net.oriserver.aether.aether.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CreateChartStageInventoryEvent extends Event {//Chartのステージを作成するときに使うEvent
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final Material material;
    private final int slot;
    private final String title;

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
    public HandlerList getHandlers(){return handlers;}
    public static HandlerList getHandlerList(){return handlers;}
}
