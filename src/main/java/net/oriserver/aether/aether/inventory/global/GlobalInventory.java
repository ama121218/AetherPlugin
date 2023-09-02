package net.oriserver.aether.aether.inventory.global;

import net.oriserver.aether.aether.inventory.chart.ChartLocation;
import net.oriserver.aether.aether.inventory.chart.ChartTimeStandard;
import net.oriserver.aether.aether.player.PlayerManager;
import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static net.oriserver.aether.aether.inventory.chart.ChartTimeStandard.*;
import static net.oriserver.aether.aether.inventory.chart.ChartTimeStandard.getChartStandard;

public class GlobalInventory {
    Inventory invGlobal = Bukkit.createInventory(null, 54, "Global Athletic");
    int[] a = {3,4,5/*,12,13,14,21,22,23,30,31,32,39,40*/};
    ItemStack gray_dye = new ItemStack(Material.INK_SACK,1, (short) 8);

    public GlobalInventory(){
        invGlobal.setItem(0, Item.createitem(Material.IRON_INGOT, 1, ChatColor.WHITE+"Teleport_option", ""));
        invGlobal.setItem(45,Item.createitem(Material.BARRIER, 1, "閉じる", ""));

        ItemStack earth = Item.getHead2("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODc5ZTU0Y2JlODc4NjdkMTRiMmZiZGYzZjE4NzA4OTQzNTIwNDhkZmVjZDk2Mjg0NmRlYTg5M2IyMTU0Yzg1In19fQ==");
        invGlobal.setItem(1, Item.changename(earth, ChatColor.BLUE + "Global Athletic", ""));

        invGlobal.setItem(3,Item.changename(gray_dye,"アクロポリス"));
        invGlobal.setItem(4,Item.changename(gray_dye,"ピラミッド"));
        invGlobal.setItem(5,Item.changename(gray_dye,"日本"));

    }
    public void setinv(Player p, int global){
        Inventory openinv = Item.inventorycopy(invGlobal);
        Item.setInventory(p,openinv);
    }
}

