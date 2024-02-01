package net.oriserver.aether.aether.inventory.home.appearance.badge;

import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.player.PlayerStats;
import net.oriserver.aether.aether.statics.CommonMethods;
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
    public BadgeInventoryClick(InventoryManager inventoryManager){
        this.inventoryManager = inventoryManager;
    }
    public void event(Player p, Material material, int slot, InventoryClickEvent e){
        if(slot == 0 && material == Material.IRON_DOOR) {inventoryManager.getHomeInventory().setinv(p);}
        else if(slot == 45 && material == Material.BARRIER) {p.closeInventory();}
        if(CommonMethods.isInSlotSet(slot)) {
            if (material.equals(Material.INK_SACK)) {
                p.sendMessage("このアイテムは手に入れてないためBadgeにすることはできません");
            }
            else if(material == Material.PRISMARINE_CRYSTALS){
                ItemStack itemStack = e.getCurrentItem();
                if(itemStack==null || !itemStack.hasItemMeta() || !itemStack.getItemMeta().hasDisplayName())return;
                inventoryManager.getPlayerManager().getPlayer(p.getUniqueId().toString()).setBadge(itemStack.getItemMeta().getDisplayName());
                p.sendMessage("Badgeを" + ChatColor.GREEN + e.getCurrentItem().getItemMeta().getDisplayName() + ChatColor.WHITE + "にしました。");
                p.closeInventory();
            }
        }
        else if (slot == 49 && material == Material.BARRIER) {
            inventoryManager.getPlayerManager().getPlayer(p.getUniqueId().toString()).setBadge("");
            p.sendMessage("Badgeを外しました");
        }
        else if(slot == 21 && material == Material.END_CRYSTAL) {inventoryManager.getParticleInventory().setinv(p);}
        else if(slot == 7 && material == Material.IRON_HELMET) {
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId()));
            inventoryManager.getHeadBlockInventory().setinv(p,playerStats.getHeadblock()[0],1);
        }
        else if(slot == 30 && material == Material.JUKEBOX) {}
    }
}
