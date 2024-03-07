package net.oriserver.aether.aether.inventory.home;

import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class HomeInventory {//ホームのインベントリークラス
    Inventory invHome = Bukkit.createInventory(null, 54, "Home");
    public HomeInventory(){
        //homeのインベントリー設定
        invHome.setItem(0,Item.createitem(Material.IRON_DOOR, 1, "ホーム", ""));
        invHome.setItem(45,Item.createitem(Material.BARRIER, 1, "閉じる", ""));

        invHome.setItem(18, Item.createitem(Material.GRASS, 1, ChatColor.GREEN + "LobbyTeleport", ""));
        invHome.setItem(3, Item.createitem(Material.NETHER_STAR, 1, ChatColor.GREEN + "AthleticTeleport", ""));
        invHome.setItem(5, Item.createitem(Material.WORKBENCH, 1, ChatColor.GREEN + "MinGame", ""));
        invHome.setItem(21, Item.createitem(Material.EMERALD, 1, ChatColor.GREEN + "Shop", ""));
        invHome.setItem(22, Item.createitem(Material.TOTEM, 1, ChatColor.GREEN + "Appearance", ""));
        invHome.setItem(23, Item.createitem(Material.SIGN, 1, ChatColor.GREEN + "Mission", ""));
        invHome.setItem(39, Item.createitem(Material.SKULL_ITEM, 1, ChatColor.GREEN + "PlayerInformation", ""));
        invHome.setItem(40, Item.createitem(Material.COMPASS, 1, ChatColor.GREEN + "Setting", ""));
        invHome.setItem(41, Item.createitem(Material.BOOK, 1, ChatColor.GREEN + "RuleBook", ""));
        invHome.setItem(53, Item.createitem(Material.PAINTING, 1, ChatColor.GREEN + "AetherPhone_Setting", ""));
    }
    public void setinv(Player p){
        Inventory openinv = Item.inventorycopy(invHome);
        openinv.setItem(39, Item.getHead(p.getName()));
        if(p.isOp()) {
            openinv.setItem(16, Item.createitem(Material.COMMAND, 1, ChatColor.GREEN + "Save_Command", ""));
            openinv.setItem(25, Item.createitem(Material.CHEST, 1, ChatColor.GREEN + "Save_Inventory", ""));
            openinv.setItem(34, Item.createitem(Material.IRON_AXE, 1, ChatColor.GREEN + "Save_Item", ""));
            openinv.setItem(43, Item.createitem(Material.ENDER_PEARL, 1, ChatColor.GREEN + "Save_Teleport", ""));
            openinv.setItem(52, Item.createitem(Material.ARMOR_STAND, 1, ChatColor.GREEN + "Give_Item", ""));
        }
        Item.setInventory(p,openinv);
    }
}
