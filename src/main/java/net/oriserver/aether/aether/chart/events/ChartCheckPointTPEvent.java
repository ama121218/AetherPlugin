package net.oriserver.aether.aether.chart.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ChartCheckPointTPEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    public ChartCheckPointTPEvent(Player player){this.player = player;}
    public Player getPlayer() {return player;}
    @Override
    public HandlerList getHandlers(){return handlers;}
    public static HandlerList getHandlerList(){return handlers;}
}
