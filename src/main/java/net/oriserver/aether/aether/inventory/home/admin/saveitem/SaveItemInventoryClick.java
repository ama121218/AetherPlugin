package net.oriserver.aether.aether.inventory.home.admin.saveitem;

import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.sqlite.SaveItemDB;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class SaveItemInventoryClick {


    final private InventoryManager inventoryManager;

    HashMap<String, ItemStack> select = new HashMap<String, ItemStack>();

    public SaveItemInventoryClick(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public void event(Player p, Material material, int slot, InventoryClickEvent e) {
        if (slot == 3 || slot == 4 || slot == 5 || slot == 12 || slot == 13 || slot == 14 || slot == 21 || slot == 22 || slot == 23 || slot == 30 || slot == 31 || slot == 32 || slot == 39 || slot == 40 || slot == 41) {
            ItemStack item = e.getCurrentItem();
            p.getInventory().addItem(item);
        } else if (material == Material.BARRIER && slot == 7) {
            inventoryManager.getSaveItemInventory().setDeleteinv(p, 1);
        } else if (material == Material.MINECART && slot == 16) {
            inventoryManager.getSaveItemInventory().setSelectinv1(p, 1);
        } else if (material == Material.REDSTONE && slot == 52) {
            inventoryManager.getSaveItemInventory().setAdmininv(p, 1);
        } else if (material == Material.ARROW && slot == 48) {
            inventoryManager.getSaveItemInventory().setinv(p, Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        } else if (material == Material.ARROW && slot == 50) {
            inventoryManager.getSaveItemInventory().setinv(p, Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        } else if (material == Material.IRON_DOOR && slot == 0) inventoryManager.getHomeInventory().setinv(p);
        else if (material == Material.BARRIER && slot == 45) p.closeInventory();
    }

    public void deleteevent(Player p, Material material, int slot, InventoryClickEvent e) {
        if (slot == 3 || slot == 4 || slot == 5 || slot == 12 || slot == 13 || slot == 14 || slot == 21 || slot == 22 || slot == 23 || slot == 30 || slot == 31 || slot == 32 || slot == 39 || slot == 40 || slot == 41) {
            ItemStack item = e.getCurrentItem();
            if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) return;
            inventoryManager.getPlayerManager().getSqLiteManager().getSaveItemDB().deleteData(String.valueOf(p.getUniqueId()), item.getItemMeta().getDisplayName());
            p.sendMessage(ChatColor.WHITE+""+e.getCurrentItem().getItemMeta().getDisplayName()+ChatColor.DARK_RED+"を削除しました");
            p.closeInventory();
        } else if (material == Material.IRON_AXE && slot == 1) {
            inventoryManager.getSaveItemInventory().setinv(p, 1);
        } else if (material == Material.ARROW && slot == 48) {
            inventoryManager.getSaveItemInventory().setDeleteinv(p, Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        } else if (material == Material.ARROW && slot == 50) {
            inventoryManager.getSaveItemInventory().setDeleteinv(p, Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        } else if (material == Material.IRON_DOOR && e.getRawSlot() == 0) inventoryManager.getHomeInventory().setinv(p);
        else if (material == Material.BARRIER && e.getRawSlot() == 45) p.closeInventory();
    }

    public void selectevent1(Player p, Material material, int slot, InventoryClickEvent e) {
        if (slot == 3 || slot == 4 || slot == 5 || slot == 12 || slot == 13 || slot == 14 || slot == 21 || slot == 22 || slot == 23 || slot == 30 || slot == 31 || slot == 32 || slot == 39 || slot == 40 || slot == 41) {
            select.put(p.getName(), e.getCurrentItem());
            inventoryManager.getSaveItemInventory().setSelectinv2(p, 1);
        } else if (material == Material.IRON_AXE && slot == 1) {
            inventoryManager.getSaveItemInventory().setinv(p, 1);
        } else if (material == Material.ARROW && slot == 48) {
            inventoryManager.getSaveItemInventory().setSelectinv1(p, Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        } else if (material == Material.ARROW && slot == 50) {
            inventoryManager.getSaveItemInventory().setSelectinv1(p, Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        } else if (material == Material.IRON_DOOR && e.getRawSlot() == 0) inventoryManager.getHomeInventory().setinv(p);
        else if (material == Material.BARRIER && e.getRawSlot() == 45) p.closeInventory();
    }

    public void selectevent2(Player p, Material material, int slot, InventoryClickEvent e) {
        if (slot == 3 || slot == 4 || slot == 5 || slot == 12 || slot == 13 || slot == 14 || slot == 21 || slot == 22 || slot == 23 || slot == 30 || slot == 31 || slot == 32 || slot == 39 || slot == 40 || slot == 41) {
            ItemStack item1 = select.get(p.getName());
            if (item1 == null || !item1.hasItemMeta() || !item1.getItemMeta().hasDisplayName()) return;
            String item_name1 = item1.getItemMeta().getDisplayName();
            ItemStack item2 = e.getCurrentItem();
            if (item2 == null || !item2.hasItemMeta() || !item2.getItemMeta().hasDisplayName()) return;
            String item_name2 = item2.getItemMeta().getDisplayName();
            SaveItemDB saveItemDB = inventoryManager.getPlayerManager().getSqLiteManager().getSaveItemDB();
            saveItemDB.updateData(
                String.valueOf(p.getUniqueId()),
                item_name1,
                "adwaowhjorrg",
                String.valueOf(item2.getType())
            );
            saveItemDB.updateData(
                String.valueOf(p.getUniqueId()),
                item_name2,
                item_name1,
                String.valueOf(item1.getType())
            );
            saveItemDB.updateData(
                String.valueOf(p.getUniqueId()),
                "adwaowhjorrg",
                item_name2,
                String.valueOf(item2.getType())
            );
            p.sendMessage(ChatColor.GREEN+item_name1+""+ChatColor.WHITE+"と"+ChatColor.GREEN+item_name2+""+ChatColor.WHITE+"を入れ替えました");
            inventoryManager.getSaveItemInventory().setinv(p,1);
        } else if (material == Material.IRON_AXE && slot == 1) {
            select.remove(p.getName());
            inventoryManager.getSaveItemInventory().setinv(p, 1);
        } else if (material == Material.ARROW && slot == 48) {
            inventoryManager.getSaveItemInventory().setSelectinv2(p, Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        } else if (material == Material.ARROW && slot == 50) {
            inventoryManager.getSaveItemInventory().setSelectinv2(p, Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        } else if (material == Material.IRON_DOOR && e.getRawSlot() == 0) {
            select.remove(p.getName());
            inventoryManager.getHomeInventory().setinv(p);
        } else if (material == Material.BARRIER && e.getRawSlot() == 45) {
            select.remove(p.getName());
            p.closeInventory();
        }
    }
    
    public void adminevent(Player p, Material material, int slot, InventoryClickEvent e){
        if(slot == 3||slot == 4||slot == 5||slot == 12||slot == 13||slot == 14||slot == 21||slot == 22||slot == 23||slot == 30||slot == 31||slot == 32||slot == 39||slot == 40||slot == 41){
            ItemStack item = e.getCurrentItem();
            p.getInventory().addItem(item);
        }
        else if(material == Material.IRON_AXE && slot == 1){
            inventoryManager.getSaveItemInventory().setinv(p,1);
        }
        else if(material == Material.BARRIER && slot == 7){
            inventoryManager.getSaveItemInventory().setAdminDeleteinv(p,1);
        }
        else if(material == Material.ARROW && slot == 48){
            inventoryManager.getSaveItemInventory().setAdmininv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }
        else if(material == Material.ARROW && slot == 50){
            inventoryManager.getSaveItemInventory().setAdmininv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }

        else if(material == Material.IRON_DOOR && slot == 0)inventoryManager.getHomeInventory().setinv(p);
        else if(material == Material.BARRIER && slot == 45)p.closeInventory();
    }
    public void admindeleteevent(Player p, Material material, int slot, InventoryClickEvent e){
        if(slot == 3||slot == 4||slot == 5||slot == 12||slot == 13||slot == 14||slot == 21||slot == 22||slot == 23||slot == 30||slot == 31||slot == 32||slot == 39||slot == 40||slot == 41){
            ItemStack item = e.getCurrentItem();
            if(item==null||!item.hasItemMeta()||!item.getItemMeta().hasDisplayName())return;
            inventoryManager.getPlayerManager().getSqLiteManager().getSaveItemDB().deleteData("admin",item.getItemMeta().getDisplayName());
            p.sendMessage(ChatColor.WHITE+""+e.getCurrentItem().getItemMeta().getDisplayName()+ChatColor.DARK_RED+"(admin)を削除しました");
            p.closeInventory();
        }
        else if(material == Material.IRON_AXE && slot == 1){
            inventoryManager.getSaveItemInventory().setinv(p,1);
        }
        else if(material == Material.REDSTONE && slot == 10){
            inventoryManager.getSaveItemInventory().setAdmininv(p,1);
        }
        else if(material == Material.ARROW && slot == 48){
            inventoryManager.getSaveItemInventory().setAdminDeleteinv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }
        else if(material == Material.ARROW && slot == 50){
            inventoryManager.getSaveItemInventory().setAdminDeleteinv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }

        else if(material == Material.IRON_DOOR && e.getRawSlot() == 0)inventoryManager.getHomeInventory().setinv(p);
        else if(material == Material.BARRIER && e.getRawSlot() == 45)p.closeInventory();
    }

}
