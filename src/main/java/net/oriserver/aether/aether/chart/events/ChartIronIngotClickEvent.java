package net.oriserver.aether.aether.chart.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ChartIronIngotClickEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final int page,chart;

    public ChartIronIngotClickEvent(Player player, int page, int chart) {
        this.player = player;
        this.page = page;
        this.chart = chart;
    }
    public Player getPlayer() {return player;}
    public int getPage() {return page;}
    public int getChart() {return chart;}

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
