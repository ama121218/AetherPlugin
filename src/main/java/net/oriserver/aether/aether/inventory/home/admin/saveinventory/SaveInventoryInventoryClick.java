package net.oriserver.aether.aether.inventory.home.admin.saveinventory;

import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.sqlite.adminDB.SaveInventoryDB;
import net.oriserver.aether.aether.statics.CommonMethods;
import net.oriserver.aether.aether.statics.Item;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class SaveInventoryInventoryClick {


    final private InventoryManager inventoryManager;

    HashMap<String, String> select = new HashMap<String, String>();
    public SaveInventoryInventoryClick(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public void event(Player p, Material material, int slot, InventoryClickEvent e) {
        if (CommonMethods.isInSlotSet(slot)) {
            ItemStack item = e.getCurrentItem();
            if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) return;
            ArrayList<Object[]> item_list = inventoryManager.getPlayerManager().getSqLiteManager().getSaveInventoryDB().getDataInInventory(p.getUniqueId().toString(),item.getItemMeta().getDisplayName());
            for(int i=0;i<9;i++)p.getInventory().clear(i);
            for(Object[] objects:item_list){
                p.getInventory().setItem((int)objects[0], Item.createitem(Material.getMaterial((String)objects[1]),1,(String)objects[2]));
            }
            p.closeInventory();
        } else if (slot == 7 && material == Material.BARRIER) {
            inventoryManager.getSaveInventoryInventory().setDeleteinv(p, 1);
        } else if (slot == 16 && material == Material.MINECART) {
            inventoryManager.getSaveInventoryInventory().setSelectinv1(p, 1);
        } else if (slot == 52 && material == Material.REDSTONE) {
            inventoryManager.getSaveInventoryInventory().setAdmininv(p, 1);
        } else if (slot == 48 && material == Material.ARROW) {
            inventoryManager.getSaveInventoryInventory().setinv(p, Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        } else if (slot == 50 && material == Material.ARROW) {
            inventoryManager.getSaveInventoryInventory().setinv(p, Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        } else if (slot == 0 && material == Material.IRON_DOOR) inventoryManager.getHomeInventory().setinv(p);
        else if (slot == 45 && material == Material.BARRIER) p.closeInventory();
    }

    public void deleteevent(Player p, Material material, int slot, InventoryClickEvent e) {
        if (CommonMethods.isInSlotSet(slot)) {
            ItemStack item = e.getCurrentItem();
            if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) return;
            inventoryManager.getPlayerManager().getSqLiteManager().getSaveInventoryDB().deleteData(String.valueOf(p.getUniqueId()), item.getItemMeta().getDisplayName());
            p.sendMessage(ChatColor.WHITE+""+e.getCurrentItem().getItemMeta().getDisplayName()+ChatColor.DARK_RED+"を削除しました");
            p.closeInventory();
        } else if (material == Material.CHEST && slot == 1) {
            inventoryManager.getSaveInventoryInventory().setinv(p, 1);
        } else if (material == Material.ARROW && slot == 48) {
            inventoryManager.getSaveInventoryInventory().setDeleteinv(p, Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        } else if (material == Material.ARROW && slot == 50) {
            inventoryManager.getSaveInventoryInventory().setDeleteinv(p, Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        } else if (material == Material.IRON_DOOR && slot == 0) inventoryManager.getHomeInventory().setinv(p);
        else if (material == Material.BARRIER && slot == 45) p.closeInventory();
    }

    public void selectevent1(Player p, Material material, int slot, InventoryClickEvent e) {
        if (CommonMethods.isInSlotSet(slot)) {
            select.put(p.getName(), e.getCurrentItem().getItemMeta().getDisplayName());
            inventoryManager.getSaveInventoryInventory().setSelectinv2(p, 1);
        } else if (material == Material.CHEST && slot == 1) {
            inventoryManager.getSaveInventoryInventory().setinv(p, 1);
        } else if (material == Material.ARROW && slot == 48) {
            inventoryManager.getSaveInventoryInventory().setSelectinv1(p, Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        } else if (material == Material.ARROW && slot == 50) {
            inventoryManager.getSaveInventoryInventory().setSelectinv1(p, Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        } else if (material == Material.IRON_DOOR && slot == 0) inventoryManager.getHomeInventory().setinv(p);
        else if (material == Material.BARRIER && slot == 45) p.closeInventory();
    }

    public void selectevent2(Player p, Material material, int slot, InventoryClickEvent e) {
        if (CommonMethods.isInSlotSet(slot)) {
            String inventory_name1 = select.get(p.getName());
            ItemStack item2 = e.getCurrentItem();
            if (item2 == null || !item2.hasItemMeta() || !item2.getItemMeta().hasDisplayName()) return;
            String inventory_name2 = item2.getItemMeta().getDisplayName();
            SaveInventoryDB saveInventoryDB = inventoryManager.getPlayerManager().getSqLiteManager().getSaveInventoryDB();
            saveInventoryDB.updateData(String.valueOf(p.getUniqueId()), inventory_name1, "adwaowhjorrg");
            saveInventoryDB.updateData(String.valueOf(p.getUniqueId()), inventory_name2, inventory_name1);
            saveInventoryDB.updateData(String.valueOf(p.getUniqueId()), "adwaowhjorrg", inventory_name2);
            p.sendMessage(ChatColor.GREEN+inventory_name1+""+ChatColor.WHITE+"と"+ChatColor.GREEN+inventory_name2+""+ChatColor.WHITE+"を入れ替えました");
            inventoryManager.getSaveInventoryInventory().setinv(p,1);
        } else if (material == Material.CHEST && slot == 1) {
            select.remove(p.getName());
            inventoryManager.getSaveInventoryInventory().setinv(p, 1);
        } else if (material == Material.ARROW && slot == 48) {
            inventoryManager.getSaveInventoryInventory().setSelectinv2(p, Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        } else if (material == Material.ARROW && slot == 50) {
            inventoryManager.getSaveInventoryInventory().setSelectinv2(p, Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        } else if (material == Material.IRON_DOOR && slot == 0) {
            select.remove(p.getName());
            inventoryManager.getHomeInventory().setinv(p);
        } else if (material == Material.BARRIER && slot == 45) {
            select.remove(p.getName());
            p.closeInventory();
        }
    }

    public void adminevent(Player p, Material material, int slot, InventoryClickEvent e){
        if(CommonMethods.isInSlotSet(slot)){
            ItemStack item = e.getCurrentItem();
            p.getInventory().addItem(item);
        }
        else if(material == Material.CHEST && slot == 1){
            inventoryManager.getSaveInventoryInventory().setinv(p,1);
        }
        else if(material == Material.BARRIER && slot == 7){
            inventoryManager.getSaveInventoryInventory().setAdminDeleteinv(p,1);
        }
        else if(material == Material.ARROW && slot == 48){
            inventoryManager.getSaveInventoryInventory().setAdmininv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }
        else if(material == Material.ARROW && slot == 50){
            inventoryManager.getSaveInventoryInventory().setAdmininv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }

        else if(material == Material.IRON_DOOR && slot == 0)inventoryManager.getHomeInventory().setinv(p);
        else if(material == Material.BARRIER && slot == 45)p.closeInventory();
    }
    public void admindeleteevent(Player p, Material material, int slot, InventoryClickEvent e){
        if(CommonMethods.isInSlotSet(slot)){
            ItemStack item = e.getCurrentItem();
            if(item==null||!item.hasItemMeta()||!item.getItemMeta().hasDisplayName())return;
            inventoryManager.getPlayerManager().getSqLiteManager().getSaveInventoryDB().deleteData("admin",item.getItemMeta().getDisplayName());
            p.sendMessage(ChatColor.WHITE+""+e.getCurrentItem().getItemMeta().getDisplayName()+ChatColor.DARK_RED+"(admin)を削除しました");
            p.closeInventory();
        }
        else if(material == Material.CHEST && slot == 1){
            inventoryManager.getSaveInventoryInventory().setinv(p,1);
        }
        else if(material == Material.REDSTONE && slot == 10){
            inventoryManager.getSaveInventoryInventory().setAdmininv(p,1);
        }
        else if(material == Material.ARROW && slot == 48){
            inventoryManager.getSaveInventoryInventory().setAdminDeleteinv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }
        else if(material == Material.ARROW && slot == 50){
            inventoryManager.getSaveInventoryInventory().setAdminDeleteinv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }

        else if(material == Material.IRON_DOOR && slot == 0)inventoryManager.getHomeInventory().setinv(p);
        else if(material == Material.BARRIER && slot == 45)p.closeInventory();
    }

}

