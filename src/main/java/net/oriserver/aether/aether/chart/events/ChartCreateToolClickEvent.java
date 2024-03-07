package net.oriserver.aether.aether.chart.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ChartCreateToolClickEvent extends Event {//ステージ作成アイテムをクリックした時のイベント
    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    public ChartCreateToolClickEvent(Player player){this.player = player;}
    public Player getPlayer(){return player;}
    @Override
    public HandlerList getHandlers(){return handlers;}
    public static HandlerList getHandlerList(){return handlers;}
}