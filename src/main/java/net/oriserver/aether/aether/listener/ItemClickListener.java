package net.oriserver.aether.aether.listener;

import net.oriserver.aether.aether.statics.Item;
import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.inventory.feather.FeatherInventory;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;

public class ItemClickListener implements Listener {

    private final FeatherInventory featherInventory;
    private final InventoryManager inventoryManager;
    private final PressureListener pressureListener;
    private final Plugin plugin;
    private final HashSet<String> itemCoolTime = new HashSet<String>();
    public ItemClickListener(InventoryManager inventoryManager, PressureListener pressureListener, Plugin plugin){
        featherInventory = new FeatherInventory();
        this.inventoryManager = inventoryManager;
        this.pressureListener = pressureListener;
        this.plugin = plugin;
    }

    @EventHandler
    public void ItemClickEvent(PlayerInteractEvent e){
        Player p = e.getPlayer();
        ItemStack item = e.getItem();
        if(item == null)return;
        if(!p.isOp()){
            e.setCancelled(true);
            if(!isCoolTimeItem(p.getName())) {
                p.sendMessage(ChatColor.DARK_RED + "高速で連続クリックしないでください");
                return;
            }
        }
        if (p.isOp() && item.getType() == Material.FEATHER) {
            e.setCancelled(true);
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                featherInventory.openinv(p);
            }else if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
                if(p.getGameMode()==GameMode.ADVENTURE || p.getGameMode()==GameMode.SURVIVAL)p.setGameMode(GameMode.CREATIVE);
                else if(p.getGameMode()==GameMode.CREATIVE)p.setGameMode(GameMode.SURVIVAL);
            }
        }else if(item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals(ChatColor.AQUA+"Aether Phone")) {
            e.setCancelled(true);
            if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                inventoryManager.getHomeInventory().setinv(p);
            }
        }else if(item.getType() == Material.PRISMARINE_SHARD){
            e.setCancelled(true);
            if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                pressureListener.resetChartStart(p);
            }
        }else if(item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().charAt(0)=='/') {
            e.setCancelled(true);
            if(item.getItemMeta().getDisplayName().contains(";")){
                String[] left_right = ChatColor.stripColor(item.getItemMeta().getDisplayName()).split(";");
                String left = left_right[0].substring(1);
                String right = left_right[1].substring(1);
                if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                    p.sendMessage(left);
                    p.performCommand(left);
                } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    p.sendMessage(right);
                    p.performCommand(right);
                }
            }else{
                String right = ChatColor.stripColor(item.getItemMeta().getDisplayName().substring(1));
                if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    if(right.startsWith("br")){
                        ItemStack nitem = Item.changename(p.getItemInHand(),right);
                        p.getInventory().setItem(p.getInventory().getHeldItemSlot(),nitem);
                    }
                    p.sendMessage(right);
                    p.performCommand(right);
                }
            }
        }else if(item.getType()==Material.IRON_INGOT){
            e.setCancelled(true);
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if(p.getLocation().getWorld().getName().equals("shrine"))inventoryManager.getLevelInventory().setinv(p,1,inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).getLevel());
                else if(p.getLocation().getWorld().getName().equals("chart"))inventoryManager.getChartInventory().setinv(p,1,inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).getChart());
                //else if(p.getLocation().getWorld().getName().equals("global"))inventoryManager.getLevelInventory().setinv(p,1,inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).getLevel());
            }
        }
    }

    public boolean isCoolTimeItem(String player_name){
        if(!itemCoolTime.contains(player_name)){
            setCoolTimeItem(player_name);
            return true;
        }else{
            return false;
        }
    }
    public void setCoolTimeItem(String player_name){
        itemCoolTime.add(player_name);
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                itemCoolTime.remove(player_name);
            }
        };
        task.runTaskLater(plugin,5);
    }
}
