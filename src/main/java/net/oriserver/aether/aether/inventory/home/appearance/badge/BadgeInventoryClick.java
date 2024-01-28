package net.oriserver.aether.aether.inventory.home.appearance.badge;

import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.player.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.Arrays;
import java.util.HashSet;

public class BadgeInventoryClick {

    final private InventoryManager inventoryManager;
    private final HashSet<Integer> slotSet = new HashSet<>(Arrays.asList(3,4,5,12,13,14,21,22,23,30,31,32,39,40,41));
    public BadgeInventoryClick(InventoryManager inventoryManager){
        this.inventoryManager = inventoryManager;
    }
    public void event(Player p, Material material, int slot, InventoryClickEvent e){
        if(material == Material.IRON_DOOR && slot == 0) {inventoryManager.getHomeInventory().setinv(p);}
        else if(material == Material.BARRIER && slot == 45) {p.closeInventory();}

        if(slotSet.contains(slot)) {
            if (material.equals(Material.INK_SACK)) {
                p.sendMessage("このアイテムは手に入れてないためHeadBlockにすることはできません");
            }
            else if(material == Material.PRISMARINE_CRYSTALS){
                ItemStack itemStack = e.getCurrentItem();
                if(itemStack==null || !itemStack.hasItemMeta() || !itemStack.getItemMeta().hasDisplayName())return;
                inventoryManager.getPlayerManager().getPlayer(p.getUniqueId().toString()).setBadge(itemStack.getItemMeta().getDisplayName());
                p.sendMessage("Badgeを" + ChatColor.GREEN + e.getCurrentItem().getItemMeta().getDisplayName() + ChatColor.WHITE + "にしました。");
                p.closeInventory();
            }
        }
        else if(material == Material.END_CRYSTAL && slot == 21) {inventoryManager.getParticleInventory().setinv(p);}
        else if(material == Material.IRON_HELMET && slot == 7) {
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId()));
            inventoryManager.getHeadBlockInventory().setinv(p,playerStats.getHeadblock()[0],1);
        }
        else if(material == Material.JUKEBOX && slot == 30) {}
    }
}
