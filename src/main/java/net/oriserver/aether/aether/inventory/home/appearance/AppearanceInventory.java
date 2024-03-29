package net.oriserver.aether.aether.inventory.home.appearance;

import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class AppearanceInventory {//プレイヤー装飾インベントリークラス
    Inventory invAppearance = Bukkit.createInventory(null, 54, "Appearance");
    public AppearanceInventory(){
        invAppearance.setItem(0, Item.createitem(Material.IRON_DOOR, 1, "ホーム", ""));
        invAppearance.setItem(45,Item.createitem(Material.BARRIER, 1, "閉じる", ""));

        invAppearance.setItem(1,Item.createitem(Material.TOTEM, 1, "Appearance", ""));

        invAppearance.setItem(21, Item.createitem(Material.END_CRYSTAL, 1, ChatColor.GREEN + "Particle", ""));
        invAppearance.setItem(22, Item.createitem(Material.IRON_HELMET, 1, ChatColor.GREEN + "HeadBlock", ""));
        invAppearance.setItem(23, Item.createitem(Material.PRISMARINE_CRYSTALS, 1, ChatColor.GREEN + "Badge", ""));
        invAppearance.setItem(30, Item.createitem(Material.JUKEBOX, 1, ChatColor.GREEN + "Music", ""));
        invAppearance.setItem(31, Item.createitem(Material.NAME_TAG, 1, ChatColor.GREEN + "Tag", ""));

    }
    public void setinv(Player p){
        Inventory openinv = Item.inventorycopy(invAppearance);
        Item.setInventory(p,openinv);
    }
}
