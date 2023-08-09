package net.oriserver.aether.aether.listener;

import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.inventory.feather.FeatherInventory;
import net.oriserver.aether.aether.player.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemClickListener implements Listener {

    final PlayerManager pm;
    private final FeatherInventory featherInventory;
    final private InventoryManager inventoryManager;
    public ItemClickListener(PlayerManager pm,InventoryManager inventoryManager){
        this.pm = pm;
        featherInventory = new FeatherInventory();
        this.inventoryManager = inventoryManager;
    }

    @EventHandler
    public void ItemClickEvent(PlayerInteractEvent e){
        Player p = e.getPlayer();
        ItemStack item = e.getItem();
        if(item == null)return;
        if (p.isOp() && item.getType() == Material.FEATHER) {
            e.setCancelled(true);
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                featherInventory.openinv(p);
            }else if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
                if(p.getGameMode()==GameMode.ADVENTURE || p.getGameMode()==GameMode.SURVIVAL)p.setGameMode(GameMode.CREATIVE);
                else if(p.getGameMode()==GameMode.CREATIVE)p.setGameMode(GameMode.SURVIVAL);
            }
        }else if(item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals(ChatColor.AQUA+"Aether Phone")){
            e.setCancelled(true);
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                inventoryManager.getHomeInventory().setinv(p);
            }
        }



    }


}
