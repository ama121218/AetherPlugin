package net.oriserver.aether.aether.inventory.home.admin.giveitem;

import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GiveItemInventory {
    Inventory invGiveItem = Bukkit.createInventory(null, 54, "Give Item");
    public GiveItemInventory(){
        invGiveItem.setItem(0, Item.createitem(Material.IRON_DOOR, 1, "ホーム", ""));
        invGiveItem.setItem(45,Item.createitem(Material.BARRIER, 1, "閉じる", ""));

        invGiveItem.setItem(1,Item.createitem(Material.ARMOR_STAND, 1, "Give_Item", ""));

        invGiveItem.setItem(1, Item.createitem(Material.ARMOR_STAND, 1, ChatColor.GREEN + "Give_Item", ""));
        invGiveItem.setItem(3, Item.createitem(Material.COMMAND, 1, ChatColor.GREEN + "command_block", ""));
        invGiveItem.setItem(4, Item.createitem(Material.BARRIER, 1, ChatColor.GREEN + "barrier", ""));
        invGiveItem.setItem(5, Item.createitem(Material.STRUCTURE_VOID, 1, ChatColor.GREEN + "structure_void", ""));
        invGiveItem.setItem(12, Item.createitem(Material.STICK, 1, ChatColor.GOLD + "ReplaceTool", ""));
        invGiveItem.setItem(13, Item.createitem(Material.BONE, 1, ChatColor.GOLD + "MetaDataChanger", ""));
        invGiveItem.setItem(21, Item.createitem(Material.STRUCTURE_BLOCK, 1, ChatColor.GREEN + "structure_block", ""));
        invGiveItem.setItem(30, Item.createitem(Material.COMPASS, 1, ChatColor.GREEN + "COMPASS", ""));
        invGiveItem.setItem(31, Item.createitem(Material.WOOD_AXE, 1, ChatColor.GREEN + "WOOD_AXE", ""));
        invGiveItem.setItem(39, Item.createitem(Material.FEATHER, 1, ChatColor.GREEN + "Speed_Changer", ""));
        invGiveItem.setItem(40, Item.createitem(Material.NETHER_STAR, 1, ChatColor.BLUE + "Athletic Teleport", ""));
        invGiveItem.setItem(41, Item.createitem(Material.IRON_BARDING, 1, ChatColor.BLUE + "CheckPoint", ""));
        invGiveItem.setItem(48, Item.createitem(Material.SPECTRAL_ARROW, 1, ChatColor.WHITE+"left:back,right:next", ""));

    }
    public void setinv(Player p){
        if(!p.isOp())return;
        Inventory openinv = Item.inventorycopy(invGiveItem);
        Item.setInventory(p,openinv);
    }
}
