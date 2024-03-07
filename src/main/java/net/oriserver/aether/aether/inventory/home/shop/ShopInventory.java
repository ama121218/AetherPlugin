package net.oriserver.aether.aether.inventory.home.shop;

import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ShopInventory {//ショップインベントリークラス

    Inventory invshop = Bukkit.createInventory(null, 54, "Shop");
    public ShopInventory(){
        invshop.setItem(0, Item.createitem(Material.IRON_DOOR, 1, "ホーム", ""));
        invshop.setItem(45,Item.createitem(Material.BARRIER, 1, "閉じる", ""));

    }
    public void setinv(Player p){
        Inventory openinv = Item.inventorycopy(invshop);
        Item.setInventory(p,openinv);
    }
}