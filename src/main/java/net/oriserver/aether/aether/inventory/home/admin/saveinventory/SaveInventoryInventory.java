package net.oriserver.aether.aether.inventory.home.admin.saveinventory;

import net.oriserver.aether.aether.player.PlayerManager;
import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class SaveInventoryInventory {
    final private PlayerManager pm;
    int[] a = {3,4,5,12,13,14,21,22,23,30,31,32,39,40,41};
    Inventory invSaveInventory = Bukkit.createInventory(null, 54, "Save Inventory");

    public SaveInventoryInventory(PlayerManager playerManager) {
        this.pm = playerManager;
        invSaveInventory.setItem(0, Item.createitem(Material.IRON_DOOR, 1, "ホーム", ""));
        invSaveInventory.setItem(45, Item.createitem(Material.BARRIER, 1, "閉じる", ""));

        invSaveInventory.setItem(1, Item.createitem(Material.CHEST, 1, "Save_Inventory", ""));
    }

    public void setinv(Player p, int page) {
        Inventory openinv = getSaveInventory(String.valueOf(p.getUniqueId()), page);
        openinv.setItem(7, Item.createitem(Material.BARRIER, 1, ChatColor.DARK_RED + "Delete_Save_Inventory", ""));
        openinv.setItem(16, Item.createitem(Material.MINECART, 1, ChatColor.YELLOW + "Select_Save_Inventory", ""));

        openinv.setItem(52, Item.createitem(Material.REDSTONE, 1, ChatColor.LIGHT_PURPLE + "Admin_Save_Inventory", ""));
        Item.setInventory(p, openinv);
    }

    public void setDeleteinv(Player p, int page) {
        Inventory inv = getSaveInventory(String.valueOf(p.getUniqueId()), page);
        Inventory openinv = Bukkit.createInventory(null, 54, ChatColor.DARK_RED + "Delete Save Inventory");
        openinv.setContents(inv.getContents());
        openinv.setItem(10, Item.createitem(Material.BARRIER, 1, ChatColor.GREEN + "Delete_Save_Inventory", ""));
        Item.setInventory(p, openinv);
    }

    public void setSelectinv1(Player p, int page) {
        Inventory inv = getSaveInventory(String.valueOf(p.getUniqueId()), page);
        Inventory openinv = Bukkit.createInventory(null, 54, ChatColor.YELLOW + "Select Save Inventory 1");
        openinv.setContents(inv.getContents());
        openinv.setItem(10, Item.createitem(Material.MINECART, 1, ChatColor.GREEN + "Select_Save_Inventory_1", ""));
        Item.setInventory(p, openinv);
    }

    public void setSelectinv2(Player p, int page) {
        Inventory inv = getSaveInventory(String.valueOf(p.getUniqueId()), page);
        Inventory openinv = Bukkit.createInventory(null, 54, ChatColor.YELLOW + "Select Save Inventory 2");
        openinv.setContents(inv.getContents());
        openinv.setItem(10, Item.createitem(Material.MINECART, 2, ChatColor.GREEN + "Select_Save_Inventory_2", ""));
        Item.setInventory(p, openinv);
    }

    public void setAdmininv(Player p, int page) {
        Inventory inv = getSaveInventory("admin", page);
        Inventory openinv = Bukkit.createInventory(null, 54, ChatColor.LIGHT_PURPLE + "Admin Save Inventory");
        openinv.setContents(inv.getContents());
        openinv.setItem(10, Item.createitem(Material.REDSTONE, 1, ChatColor.GREEN + "Admin_Save_Inventory", ""));
        openinv.setItem(7, Item.createitem(Material.BARRIER, 1, ChatColor.DARK_RED + "Admin_Delete_Save_Inventory", ""));
        Item.setInventory(p, openinv);
    }

    public void setAdminDeleteinv(Player p, int page) {
        Inventory inv = getSaveInventory("admin", page);
        Inventory openinv = Bukkit.createInventory(null, 54, ChatColor.DARK_RED + "Admin Delete Save Inventory");
        openinv.setContents(inv.getContents());
        openinv.setItem(10, Item.createitem(Material.REDSTONE, 1, ChatColor.GREEN + "Admin_Save_Inventory", ""));
        openinv.setItem(19, Item.createitem(Material.BARRIER, 1, ChatColor.GREEN + "Admin_Delete_Save_Teleport", ""));
        Item.setInventory(p, openinv);
    }

    public Inventory getSaveInventory(String uuid, int page) {
        Inventory openinv = Item.inventorycopy(invSaveInventory);
        ArrayList<String> inventory_name_list = pm.getSqLiteManager().getSaveInventoryDB().getInventoryData(uuid);
        int size = inventory_name_list.size();
        if (size == 0) {
            return openinv;
        }
        for (int i = 0; i < size - (15 * (page - 1)) && i < 15; i++) {
            String inventory_name = inventory_name_list.get(i + 15 * (page - 1));
            openinv.setItem(a[i], Item.createitem(Material.CHEST, 1, inventory_name));
        }
        if (page == 1) {
            if (size > 15) {
                openinv.setItem(48, Item.createitem(Material.STRUCTURE_VOID, 1, "", ""));
                openinv.setItem(50, Item.createitem(Material.ARROW, 1, "2ページ目へ", ""));
            }
        } else if (page >= 2) {
            if (size > 15 * page) {
                openinv.setItem(48, Item.createitem(Material.ARROW, 1, (page - 1) + "ページ目へ", ""));
                openinv.setItem(50, Item.createitem(Material.ARROW, 1, (page + 1) + "ページ目へ", ""));
            } else {
                openinv.setItem(48, Item.createitem(Material.ARROW, 1, (page - 1) + "ページ目へ", ""));
                openinv.setItem(50, Item.createitem(Material.STRUCTURE_VOID, 1, "", ""));
            }
        }
        return openinv;
    }

}
