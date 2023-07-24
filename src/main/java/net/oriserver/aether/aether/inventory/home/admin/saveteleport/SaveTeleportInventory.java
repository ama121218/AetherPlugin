package net.oriserver.aether.aether.inventory.home.admin.saveteleport;

import net.oriserver.aether.aether.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SaveTeleportInventory {
    Inventory invSaveTeleport = Bukkit.createInventory(null, 54, "Give_Item");
    public SaveTeleportInventory(){
        invSaveTeleport.setItem(0, Item.createitem(Material.IRON_DOOR, 1, "ホーム", ""));
        invSaveTeleport.setItem(45,Item.createitem(Material.BARRIER, 1, "閉じる", ""));

        invSaveTeleport.setItem(1,Item.createitem(Material.ARMOR_STAND, 1, "Give_Item", ""));

        invSaveTeleport.setItem(7,Item.createitem(Material.BARRIER,1, ChatColor.DARK_RED+"Delete_Save_Teleport",""));
        invSaveTeleport.setItem(16,Item.createitem(Material.MINECART,1,ChatColor.YELLOW+"Select_Save_Teleport",""));
    }
    public void setinv(Player p){
        Inventory openinv = Item.inventorycopy(invSaveTeleport);
        Item.setInventory(p,openinv);
    }
    public void setDeleteinv(Player p){

    }
}
