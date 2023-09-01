package net.oriserver.aether.aether.inventory.home.phonesetting.appearance;

import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PhoneAppearanceInventory {
    Inventory invPhoneAppearance = Bukkit.createInventory(null, 54, "Phone Appearance");
    public PhoneAppearanceInventory(){
        invPhoneAppearance.setItem(0, Item.createitem(Material.IRON_DOOR, 1, "ホーム", ""));
        invPhoneAppearance.setItem(45,Item.createitem(Material.BARRIER, 1, "閉じる", ""));

        invPhoneAppearance.setItem(1, Item.createitem(Material.PAINTING, 1, ChatColor.GREEN + "Phone_Setting", ""));


        invPhoneAppearance.setItem(10, Item.createitem(Material.WOOD_DOOR, 1, ChatColor.GREEN + "Phone_Appearance", ""));
        invPhoneAppearance.setItem(12, Item.createitem(Material.IRON_DOOR, 1, ChatColor.GREEN + "IRON_DOOR", ""));
        invPhoneAppearance.setItem(13, Item.createitem(Material.WOOD_DOOR, 1, ChatColor.GREEN + "WOOD_DOOR", ""));
        invPhoneAppearance.setItem(14, Item.createitem(Material.BIRCH_DOOR_ITEM, 1, ChatColor.GREEN + "BIRCH_DOOR", ""));
        invPhoneAppearance.setItem(21, Item.createitem(Material.ACACIA_DOOR_ITEM, 1, ChatColor.GREEN + "ACACIA_DOOR", ""));
        invPhoneAppearance.setItem(22, Item.createitem(Material.DARK_OAK_DOOR_ITEM, 1, ChatColor.GREEN + "DARK_OAK_DOOR", ""));
        invPhoneAppearance.setItem(23, Item.createitem(Material.JUNGLE_DOOR_ITEM, 1, ChatColor.GREEN + "JUNGLE_DOOR", ""));
        invPhoneAppearance.setItem(30, Item.createitem(Material.PAINTING, 1, ChatColor.GREEN + "PAINTING", ""));

        invPhoneAppearance.setItem(7, Item.createitem(Material.THIN_GLASS, 1, ChatColor.GREEN + "Phone_Partition", ""));
        invPhoneAppearance.setItem(16, Item.createitem(Material.IRON_BARDING, 1, ChatColor.GREEN + "Phone_Appearance", ""));
    }
    public void setinv(Player p){
        Inventory openinv = Item.inventorycopy(invPhoneAppearance);
        Item.setInventory(p,openinv);
    }
}
