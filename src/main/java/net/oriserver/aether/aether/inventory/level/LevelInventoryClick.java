package net.oriserver.aether.aether.inventory.level;

import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.player.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import static net.oriserver.aether.aether.inventory.level.LevelLocation.getLocation;

public class LevelInventoryClick {
    final private InventoryManager inventoryManager;
    public LevelInventoryClick(InventoryManager inventoryManager){this.inventoryManager = inventoryManager;}

    public void event(Player p, Material material, int slot, InventoryClickEvent e){
        if (material == Material.IRON_DOOR && slot == 0) {inventoryManager.getHomeInventory().setinv(p);}
        else if (material == Material.BARRIER && slot == 45) {p.closeInventory();}
        else if (slot==3||slot==4||slot==5||slot==12||slot==13||slot==14||slot==21||slot==22||slot==23||slot==30||slot==31||slot==32||slot==39||slot==40||slot==41){
            if (material.equals(Material.INK_SACK)) {
                p.sendMessage(ChatColor.DARK_RED+"このレベルはまだ到達していません");
                return;
            } else {
                ItemStack clickedItem = e.getCurrentItem();
                if (clickedItem.hasItemMeta() && clickedItem.getItemMeta().hasDisplayName()) {
                    String itemName = clickedItem.getItemMeta().getDisplayName();
                    String levelStr = itemName.split(" ")[1];
                    int level = Integer.parseInt(levelStr);
                    p.teleport(getLocation(level));
                }
            }
        }if (material == Material.ARROW && (slot == 48 || slot == 50)) {
            ItemStack pageItem = p.getOpenInventory().getTopInventory().getItem(1);
            if (pageItem == null || !pageItem.hasItemMeta() || !pageItem.getItemMeta().hasLore()) return;
            int main_page = Integer.parseInt(pageItem.getItemMeta().getLore().get(0));
            int sub_page = Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0));
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId()));
            inventoryManager.getLevelInventory().setinv(p, playerStats.getLevel(), (main_page - 1) * 2 + sub_page);
        }
    }
}
