package net.oriserver.aether.aether.inventory.home.minigame;

import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MiniGameInventory {

    Inventory invMiniGame = Bukkit.createInventory(null, 54, "MiniGame");
    public MiniGameInventory(){
        invMiniGame.setItem(0, Item.createitem(Material.IRON_DOOR, 1, "ホーム", ""));
        invMiniGame.setItem(45,Item.createitem(Material.BARRIER, 1, "閉じる", ""));

        invMiniGame.setItem(1, Item.createitem(Material.WORKBENCH, 1, ChatColor.GREEN + "MiniGame", ""));

        invMiniGame.setItem(21, Item.createitem(Material.TNT, 1, ChatColor.RED + "TNTRun", ""));
    }

    public void setinv(Player p){
        Inventory openinv = Item.inventorycopy(invMiniGame);
        Item.setInventory(p,openinv);
    }
}
