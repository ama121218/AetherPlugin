package net.oriserver.aether.aether.inventory.chart;

import net.oriserver.aether.aether.statics.CommonMethods;
import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.player.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;


public class ChartInventoryClick {
    final private InventoryManager inventoryManager;
    final private PlayerManager pm;
    public ChartInventoryClick(InventoryManager inventoryManager, PlayerManager pm){
        this.inventoryManager = inventoryManager;
        this.pm = pm;
        map_number.put(3, 1);
        map_number.put(4, 2);
        map_number.put(5, 3);
        map_number.put(12, 4);
        map_number.put(13, 5);
        map_number.put(14, 6);
        map_number.put(21, 7);
        map_number.put(22, 8);
        map_number.put(23, 9);
        map_number.put(30, 10);
        map_number.put(31, 11);
        map_number.put(32, 12);
        map_number.put(39, 13);
        map_number.put(40, 14);
    }
    HashMap<Integer,Integer> map_number = new HashMap<Integer,Integer>();

    public void event(Player p, Material material, int slot, InventoryClickEvent e){
        if(material != null && map_number.get(slot)!=null){
            int number = map_number.get(slot);
            ItemStack pageItem = e.getInventory().getItem(1);
            if (!pageItem.hasItemMeta() || !pageItem.getItemMeta().hasLore()) return;
            int main_page = Integer.parseInt(ChatColor.stripColor(pageItem.getItemMeta().getLore().get(0)))-1;
            CommonMethods.setTeleport(p,ChartLocation.getChartLocation(number+(main_page*14)),"Chart"+(main_page+1)+"_"+number,pm.getPlayer(String.valueOf(p.getUniqueId())));
        }else if(material == Material.GRASS && slot == 7){
            inventoryManager.getChartInventory().setinv(p, 1  ,inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).getChart());
        }else if(material == Material.SAND && slot == 16){
            inventoryManager.getChartInventory().setinv(p, 2  ,inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).getChart());
        }else if(material == Material.SNOW_BLOCK && slot == 25){
            inventoryManager.getChartInventory().setinv(p, 3  ,inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).getChart());
        }else if(material == Material.QUARTZ_BLOCK && slot == 34){
            inventoryManager.getChartInventory().setinv(p, 4  ,inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).getChart());
        }
    }
    public String getChartNumber(int number){
        return "Chart"+((number - 1) / 14 + 1)+"_"+((number - 1) % 14 + 1);
    }
}