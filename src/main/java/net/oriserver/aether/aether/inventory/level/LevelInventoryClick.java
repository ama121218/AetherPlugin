package net.oriserver.aether.aether.inventory.level;

import net.oriserver.aether.aether.AthleticLocation;
import net.oriserver.aether.aether.statics.CommonMethods;
import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.player.PlayerManager;
import net.oriserver.aether.aether.player.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

import static net.oriserver.aether.aether.inventory.level.LevelLocation.getLevelLocation;

public class LevelInventoryClick {//レベルアスレチックのインベントリーをクリックした時に操作するクラス
    private final InventoryManager inventoryManager;
    private final PlayerManager pm;
    public LevelInventoryClick(InventoryManager inventoryManager,PlayerManager pm){
        this.inventoryManager = inventoryManager;
        this.pm = pm;
    }

    public void event(Player p, Material material, int slot, InventoryClickEvent e){
        if (material == Material.BARRIER && slot == 45) {p.closeInventory();}
        else if (CommonMethods.isInSlotSet(slot)){
            if (material.equals(Material.INK_SACK)) {
                p.sendMessage(ChatColor.DARK_RED+"このレベルはまだ到達していません");
                return;
            } else {
                ItemStack clickedItem = e.getCurrentItem();
                if (clickedItem.hasItemMeta() && clickedItem.getItemMeta().hasDisplayName()) {
                    String itemName = clickedItem.getItemMeta().getDisplayName();
                    String levelStr = itemName.split(" ")[1];
                    int level = Integer.parseInt(levelStr);
                    CommonMethods.setTeleport(p,getLevelLocation(level),"Level_"+level,pm.getPlayer(String.valueOf(p.getUniqueId())));
                }
            }
        }else if(material == Material.GRASS && slot == 7){
            inventoryManager.getLevelInventory().setinv(p, 1  ,inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).getLevel());
            inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setLevel_page(1);
        }else if(material == Material.LOG && slot == 16){
            inventoryManager.getLevelInventory().setinv(p, 3  ,inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).getLevel());
            inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setLevel_page(3);
        }else if(material == Material.SMOOTH_BRICK && slot == 25){
            inventoryManager.getLevelInventory().setinv(p, 5  ,inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).getLevel());
            inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setLevel_page(5);
        }else if(material == Material.LAPIS_BLOCK && slot == 34){
            inventoryManager.getLevelInventory().setinv(p, 7  ,inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).getLevel());
            inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setLevel_page(7);
        }else if(material == Material.MAGMA && slot == 43){
            inventoryManager.getLevelInventory().setinv(p, 9  ,inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).getLevel());
            inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setLevel_page(9);
        }else if(material == Material.QUARTZ_BLOCK && slot == 52){
            inventoryManager.getLevelInventory().setinv(p, 11  ,inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).getLevel());
            inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setLevel_page(11);
        }
        else if (material == Material.ARROW && (slot == 48 || slot == 50)) {
            ItemStack pageItem = p.getOpenInventory().getTopInventory().getItem(1);
            if (pageItem == null || !pageItem.hasItemMeta() || !pageItem.getItemMeta().hasLore()) return;
            int main_page = Integer.parseInt(ChatColor.stripColor(pageItem.getItemMeta().getLore().get(0)));
            int sub_page = Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0));
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId()));
            inventoryManager.getLevelInventory().setinv(p, (main_page - 1) * 2 + sub_page,playerStats.getLevel());
            playerStats.setLevel_page((main_page - 1) * 2 + sub_page);
        }
        else if(material == Material.CHORUS_FRUIT_POPPED && slot == 18){
            CommonMethods.setTeleport(p,AthleticLocation.getLocation(AthleticLocation.SHRINE),"Level_Lobby",inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())));
        }
    }
}
