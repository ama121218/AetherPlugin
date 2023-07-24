package net.oriserver.aether.aether.inventory.home.admin.giveitem;

import net.oriserver.aether.aether.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GiveItemInventory {
    Inventory invGiveItem = Bukkit.createInventory(null, 54, "Give_Item");
    public GiveItemInventory(){
        invGiveItem.setItem(0, Item.createitem(Material.IRON_DOOR, 1, "ホーム", ""));
        invGiveItem.setItem(45,Item.createitem(Material.BARRIER, 1, "閉じる", ""));

        invGiveItem.setItem(1,Item.createitem(Material.ARMOR_STAND, 1, "Give_Item", ""));
    }
    public void setinv(Player p){
        Inventory openinv = Item.inventorycopy(invGiveItem);
        Item.setInventory(p,openinv);
    }
}
