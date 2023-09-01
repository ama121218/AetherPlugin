package net.oriserver.aether.aether.inventory.home.phonesetting;

import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PhoneSettingInventory {
    Inventory invPhoneSetting = Bukkit.createInventory(null, 54, "Phone Setting");
    public PhoneSettingInventory() {

        invPhoneSetting.setItem(0, Item.createitem(Material.IRON_DOOR, 1, "ホーム", ""));
        invPhoneSetting.setItem(45, Item.createitem(Material.BARRIER, 1, "閉じる", ""));

        invPhoneSetting.setItem(1, Item.createitem(Material.PAINTING, 1, ChatColor.GREEN + "Phone_Setting", ""));

        invPhoneSetting.setItem(21, Item.createitem(Material.WOOD_DOOR, 1, ChatColor.GREEN + "Phone_Appearance", ""));
        invPhoneSetting.setItem(22, Item.createitem(Material.THIN_GLASS, 1, ChatColor.GREEN + "Phone_Partition", ""));
        invPhoneSetting.setItem(23, Item.createitem(Material.IRON_BARDING, 1, ChatColor.GREEN + "Phone_Appearance", ""));
    }
    public void setinv(Player p){
        Inventory openinv = Item.inventorycopy(invPhoneSetting);
        Item.setInventory(p,openinv);
    }
}
