package net.oriserver.aether.aether.inventory.feather;

import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class FeatherInventory {//スピード変化インベントリークラス
    private final Inventory speed_select = Bukkit.createInventory(null,9,"Speed Select");
    public FeatherInventory(){
        speed_select.setItem(0, Item.createitem(Material.SOUL_SAND,1,"level -1"));
        speed_select.setItem(1, Item.createitem(Material.LEATHER_BOOTS,1,"normal speed"));
        speed_select.setItem(2, Item.createitem(Material.FEATHER,1,"level 1"));
        speed_select.setItem(3, Item.createitem(Material.FEATHER,2,"level 2"));
        speed_select.setItem(4, Item.createitem(Material.FEATHER,3,"level 3"));
        speed_select.setItem(5, Item.createitem(Material.FEATHER,4,"level 4"));
        speed_select.setItem(6, Item.createitem(Material.FEATHER,5,"level 5"));
        speed_select.setItem(7, Item.createitem(Material.FEATHER,6,"level 6"));
        speed_select.setItem(8, Item.createitem2(Material.FEATHER,7,"level 7"));
    }
    public void openinv(Player p){
        p.openInventory(speed_select);
    }
}