package net.oriserver.aether.aether.chart.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class ChartStageTPEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final String s_location;
    public ChartStageTPEvent(Player player, Location location) {
        this.player = player;
        this.s_location = location.getX()+","+location.getY()+","+location.getZ();
    }
    public Player getPlayer(){return player;}
    public String getS_Location(){return s_location;}

    @Override
    public HandlerList getHandlers(){return handlers;}
    public static HandlerList getHandlerList(){return handlers;}
}
