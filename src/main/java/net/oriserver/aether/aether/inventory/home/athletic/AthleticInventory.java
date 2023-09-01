package net.oriserver.aether.aether.inventory.home.athletic;

import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AthleticInventory {
    Inventory invAthletic = Bukkit.createInventory(null, 54, "Athletic Teleport");
    public AthleticInventory(){
        invAthletic.setItem(0,Item.createitem(Material.IRON_DOOR, 1, "ホーム", ""));
        invAthletic.setItem(45,Item.createitem(Material.BARRIER, 1, "閉じる", ""));

        invAthletic.setItem(1, Item.createitem(Material.NETHER_STAR, 1, ChatColor.GREEN + "Athletic_Teleport", ""));

        invAthletic.setItem(21, Item.createitem(Material.QUARTZ_STAIRS, 1, ChatColor.RED + "Level Athletic", ""));
        ItemStack earth = Item.getHead2("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODc5ZTU0Y2JlODc4NjdkMTRiMmZiZGYzZjE4NzA4OTQzNTIwNDhkZmVjZDk2Mjg0NmRlYTg5M2IyMTU0Yzg1In19fQ==");
        invAthletic.setItem(22, Item.changename(earth, ChatColor.BLUE + "Global Athletic", ""));
        invAthletic.setItem(23, Item.createitem(Material.APPLE, 1, ChatColor.YELLOW + "Chart Athletic", ""));
        invAthletic.setItem(30, Item.createitem(Material.STAINED_GLASS_PANE, 1, ChatColor.DARK_PURPLE + "Ordeal Athletic", ""));
        invAthletic.setItem(31, Item.createitem(Material.STONE, 1, ChatColor.WHITE + "Athletic", ""));
    }
    public void setinv(Player p){
        Inventory openinv = Item.inventorycopy(invAthletic);
        Item.setInventory(p,openinv);
    }
}
