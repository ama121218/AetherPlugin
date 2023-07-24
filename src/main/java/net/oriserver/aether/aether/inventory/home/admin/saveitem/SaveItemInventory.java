package net.oriserver.aether.aether.inventory.home.admin.saveitem;

import net.oriserver.aether.aether.Item;
import net.oriserver.aether.aether.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SaveItemInventory {
    Inventory invSaveItem = Bukkit.createInventory(null, 54, "Give_Item");
    public SaveItemInventory(){
        invSaveItem.setItem(0, Item.createitem(Material.IRON_DOOR, 1, "ホーム", ""));
        invSaveItem.setItem(45,Item.createitem(Material.BARRIER, 1, "閉じる", ""));

        invSaveItem.setItem(1,Item.createitem(Material.IRON_AXE, 1, "Save_Item", ""));

        invSaveItem.setItem(7,Item.createitem(Material.BARRIER,1, ChatColor.DARK_RED+"Delete_Save_Item",""));
        invSaveItem.setItem(16,Item.createitem(Material.MINECART,1,ChatColor.YELLOW+"Select_Save_Item",""));
    }
    public void setinv(Player p,int page){
        Inventory openinv = Item.inventorycopy(invSaveItem);
        Item.setInventory(p,openinv);
    }
}
