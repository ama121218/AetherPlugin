package net.oriserver.aether.aether.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEvent;

public class CreateTNTRunStageClickItemEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private PlayerInteractEvent e;


    public CreateTNTRunStageClickItemEvent(PlayerInteractEvent e) {
        this.e = e;
    }
    public PlayerInteractEvent getEvent(){
        return e;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
