package net.oriserver.aether.aether.inventory.home.admin.savecommand;


import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.sqlite.adminDB.SaveCommandDB;
import net.oriserver.aether.aether.statics.CommonMethods;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class SaveCommandInventoryClick {//コマンドを保存するのインベントリークラスをクリックした時に操作するクラス


    final private InventoryManager inventoryManager;

    HashMap<String, ItemStack> select = new HashMap<String, ItemStack>();
    public SaveCommandInventoryClick(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public void event(Player p, Material material, int slot, InventoryClickEvent e) {
        if (CommonMethods.isInSlotSet(slot)) {
            ItemStack item = e.getCurrentItem();
            if(item == null || !item.hasItemMeta() || !item.getItemMeta().hasDisplayName())return;
            String commandString = item.getItemMeta().getDisplayName().substring(1);
            p.performCommand(commandString);
            //
        } else if (slot == 7 && material == Material.BARRIER) {
            inventoryManager.getSaveCommandInventory().setDeleteinv(p, 1);
        } else if (slot == 16 && material == Material.MINECART) {
            inventoryManager.getSaveCommandInventory().setSelectinv1(p, 1);
        } else if (slot == 52 && material == Material.REDSTONE) {
            inventoryManager.getSaveCommandInventory().setAdmininv(p, 1);
        } else if (slot == 48 && material == Material.ARROW) {
            inventoryManager.getSaveCommandInventory().setinv(p, Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        } else if (slot == 50 && material == Material.ARROW) {
            inventoryManager.getSaveCommandInventory().setinv(p, Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        } else if (slot == 0 && material == Material.IRON_DOOR) inventoryManager.getHomeInventory().setinv(p);
        else if (slot == 45 && material == Material.BARRIER) p.closeInventory();
    }

    public void deleteevent(Player p, Material material, int slot, InventoryClickEvent e) {
        if (CommonMethods.isInSlotSet(slot)) {
            ItemStack item = e.getCurrentItem();
            if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) return;
            inventoryManager.getPlayerManager().getSqLiteManager().getSaveCommandDB().deleteData(String.valueOf(p.getUniqueId()), item.getItemMeta().getDisplayName());
            p.sendMessage(ChatColor.WHITE+""+e.getCurrentItem().getItemMeta().getDisplayName()+ChatColor.DARK_RED+"を削除しました");
            p.closeInventory();
        } else if (material == Material.COMMAND && slot == 1) {
            inventoryManager.getSaveCommandInventory().setinv(p, 1);
        } else if (material == Material.ARROW && slot == 48) {
            inventoryManager.getSaveCommandInventory().setDeleteinv(p, Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        } else if (material == Material.ARROW && slot == 50) {
            inventoryManager.getSaveCommandInventory().setDeleteinv(p, Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        } else if (material == Material.IRON_DOOR && e.getRawSlot() == 0) inventoryManager.getHomeInventory().setinv(p);
        else if (material == Material.BARRIER && e.getRawSlot() == 45) p.closeInventory();
    }

    public void selectevent1(Player p, Material material, int slot, InventoryClickEvent e) {
        if (CommonMethods.isInSlotSet(slot)) {
            select.put(p.getName(), e.getCurrentItem());
            inventoryManager.getSaveCommandInventory().setSelectinv2(p, 1);
        } else if (material == Material.COMMAND && slot == 1) {
            inventoryManager.getSaveCommandInventory().setinv(p, 1);
        } else if (material == Material.ARROW && slot == 48) {
            inventoryManager.getSaveCommandInventory().setSelectinv1(p, Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        } else if (material == Material.ARROW && slot == 50) {
            inventoryManager.getSaveCommandInventory().setSelectinv1(p, Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        } else if (material == Material.IRON_DOOR && e.getRawSlot() == 0) inventoryManager.getHomeInventory().setinv(p);
        else if (material == Material.BARRIER && e.getRawSlot() == 45) p.closeInventory();
    }

    public void selectevent2(Player p, Material material, int slot, InventoryClickEvent e) {
        if (CommonMethods.isInSlotSet(slot)) {
            ItemStack item1 = select.get(p.getName());
            if (item1 == null || !item1.hasItemMeta() || !item1.getItemMeta().hasDisplayName()) return;
            String item_name1 = item1.getItemMeta().getDisplayName();
            ItemStack item2 = e.getCurrentItem();
            if (item2 == null || !item2.hasItemMeta() || !item2.getItemMeta().hasDisplayName()) return;
            String item_name2 = item2.getItemMeta().getDisplayName();
            SaveCommandDB saveCommandDB = inventoryManager.getPlayerManager().getSqLiteManager().getSaveCommandDB();
            saveCommandDB.updateData(
                    String.valueOf(p.getUniqueId()),
                    item_name1,
                    "adwaowhjorrg",
                    String.valueOf(item2.getType())
            );
            saveCommandDB.updateData(
                    String.valueOf(p.getUniqueId()),
                    item_name2,
                    item_name1,
                    String.valueOf(item1.getType())
            );
            saveCommandDB.updateData(
                    String.valueOf(p.getUniqueId()),
                    "adwaowhjorrg",
                    item_name2,
                    String.valueOf(item2.getType())
            );
            p.sendMessage(ChatColor.GREEN+item_name1+""+ChatColor.WHITE+"と"+ChatColor.GREEN+item_name2+""+ChatColor.WHITE+"を入れ替えました");
            inventoryManager.getSaveCommandInventory().setinv(p,1);
        } else if (material == Material.COMMAND && slot == 1) {
            select.remove(p.getName());
            inventoryManager.getSaveCommandInventory().setinv(p, 1);
        } else if (material == Material.ARROW && slot == 48) {
            inventoryManager.getSaveCommandInventory().setSelectinv2(p, Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        } else if (material == Material.ARROW && slot == 50) {
            inventoryManager.getSaveCommandInventory().setSelectinv2(p, Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        } else if (material == Material.IRON_DOOR && e.getRawSlot() == 0) {
            select.remove(p.getName());
            inventoryManager.getHomeInventory().setinv(p);
        } else if (material == Material.BARRIER && e.getRawSlot() == 45) {
            select.remove(p.getName());
            p.closeInventory();
        }
    }

    public void adminevent(Player p, Material material, int slot, InventoryClickEvent e){
        if(CommonMethods.isInSlotSet(slot)){
            ItemStack item = e.getCurrentItem();
            p.getInventory().addItem(item);
        }
        else if(material == Material.COMMAND && slot == 1){
            inventoryManager.getSaveCommandInventory().setinv(p,1);
        }
        else if(material == Material.BARRIER && slot == 7){
            inventoryManager.getSaveCommandInventory().setAdminDeleteinv(p,1);
        }
        else if(material == Material.ARROW && slot == 48){
            inventoryManager.getSaveCommandInventory().setAdmininv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }
        else if(material == Material.ARROW && slot == 50){
            inventoryManager.getSaveCommandInventory().setAdmininv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }

        else if(material == Material.IRON_DOOR && slot == 0)inventoryManager.getHomeInventory().setinv(p);
        else if(material == Material.BARRIER && slot == 45)p.closeInventory();
    }
    public void admindeleteevent(Player p, Material material, int slot, InventoryClickEvent e){
        if(CommonMethods.isInSlotSet(slot)){
            ItemStack item = e.getCurrentItem();
            if(item==null||!item.hasItemMeta()||!item.getItemMeta().hasDisplayName())return;
            inventoryManager.getPlayerManager().getSqLiteManager().getSaveCommandDB().deleteData("admin",item.getItemMeta().getDisplayName());
            p.sendMessage(ChatColor.WHITE+""+e.getCurrentItem().getItemMeta().getDisplayName()+ChatColor.DARK_RED+"(admin)を削除しました");
            p.closeInventory();
        }
        else if(material == Material.COMMAND && slot == 1){
            inventoryManager.getSaveCommandInventory().setinv(p,1);
        }
        else if(material == Material.REDSTONE && slot == 10){
            inventoryManager.getSaveCommandInventory().setAdmininv(p,1);
        }
        else if(material == Material.ARROW && slot == 48){
            inventoryManager.getSaveCommandInventory().setAdminDeleteinv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }
        else if(material == Material.ARROW && slot == 50){
            inventoryManager.getSaveCommandInventory().setAdminDeleteinv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }

        else if(material == Material.IRON_DOOR && e.getRawSlot() == 0)inventoryManager.getHomeInventory().setinv(p);
        else if(material == Material.BARRIER && e.getRawSlot() == 45)p.closeInventory();
    }

}

