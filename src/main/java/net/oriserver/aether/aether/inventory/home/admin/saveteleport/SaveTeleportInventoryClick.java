package net.oriserver.aether.aether.inventory.home.admin.saveteleport;

import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.sqlite.adminDB.SaveTeleportDB;
import net.oriserver.aether.aether.statics.CommonMethods;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SaveTeleportInventoryClick {//テレポート位置を保存するのインベントリークラスをクリックした時に操作するクラス
    final private InventoryManager inventoryManager;

    HashMap<String,ItemStack> select = new HashMap<String,ItemStack>();

    public SaveTeleportInventoryClick(InventoryManager inventoryManager){this.inventoryManager = inventoryManager;}
    public void event(Player p, Material material, int slot, InventoryClickEvent e){
        if(CommonMethods.isInSlotSet(slot)){
            List<String> list = e.getCurrentItem().getItemMeta().getLore();
            Location location = new Location(Bukkit.getWorld(list.get(0)),Float.parseFloat(list.get(1)),Float.parseFloat(list.get(2)),Float.parseFloat(list.get(3)),Float.parseFloat(list.get(4)),Float.parseFloat(list.get(5)));
            p.teleport(location);
        }
        else if(slot == 7 && material == Material.BARRIER){
            inventoryManager.getSaveTeleportInventory().setDeleteinv(p,1);
        }
        else if(slot == 16 && material == Material.MINECART){
            inventoryManager.getSaveTeleportInventory().setSelectinv1(p,1);
        }
        else if(slot == 18 && material == Material.CHORUS_FRUIT_POPPED){
            p.teleport(p.getLocation().getWorld().getSpawnLocation());
        }
        else if(slot == 52 && material == Material.REDSTONE) {
            inventoryManager.getSaveTeleportInventory().setAdmininv(p, 1);
        }
        else if(slot == 8 && material == Material.SADDLE){
            Random random = new Random();
            String st_name = String.format("%06d", random.nextInt(1000000));
            String item = String.valueOf(Material.STONE);
            Location l = p.getLocation();
            inventoryManager.getPlayerManager().getSqLiteManager().getSaveTeleportDB().insertData(String.valueOf(p.getUniqueId()),st_name,item,l.getWorld().getName(),l.getX(),l.getY(),l.getZ(),l.getYaw(),l.getPitch());
            p.sendMessage(ChatColor.GOLD + "現在地を" + ChatColor.WHITE + st_name + ChatColor.GOLD + "として設定しました");
            p.closeInventory();
        }
        else if(slot == 48 && material == Material.ARROW){
            inventoryManager.getSaveTeleportInventory().setinv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }
        else if(slot == 50 && material == Material.ARROW){
            inventoryManager.getSaveTeleportInventory().setinv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }

        else if(slot == 0 && material == Material.IRON_DOOR)inventoryManager.getHomeInventory().setinv(p);
        else if(slot == 45 && material == Material.BARRIER)p.closeInventory();
    }
    public void deleteevent(Player p, Material material, int slot, InventoryClickEvent e){
        if(CommonMethods.isInSlotSet(slot)){
            ItemStack item = e.getCurrentItem();
            if(item==null||!item.hasItemMeta()||!item.getItemMeta().hasDisplayName())return;
            inventoryManager.getPlayerManager().getSqLiteManager().getSaveTeleportDB().deleteData(String.valueOf(p.getUniqueId()),item.getItemMeta().getDisplayName());
            p.sendMessage(ChatColor.WHITE+""+e.getCurrentItem().getItemMeta().getDisplayName()+ChatColor.DARK_RED+"を削除しました");
            p.closeInventory();
        }
        else if(slot == 1 && material == Material.ENDER_PEARL){
            inventoryManager.getSaveTeleportInventory().setinv(p,1);
        }

        else if(slot == 48 && material == Material.ARROW){
            inventoryManager.getSaveTeleportInventory().setDeleteinv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }
        else if(slot == 50 && material == Material.ARROW){
            inventoryManager.getSaveTeleportInventory().setDeleteinv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }

        else if(slot == 0 && material == Material.IRON_DOOR)inventoryManager.getHomeInventory().setinv(p);
        else if(slot == 45 && material == Material.BARRIER)p.closeInventory();
    }
    public void selectevent1(Player p, Material material, int slot, InventoryClickEvent e){
        if(CommonMethods.isInSlotSet(slot)){
            select.put(p.getName(),e.getCurrentItem());
            inventoryManager.getSaveTeleportInventory().setSelectinv2(p,1);
        }
        else if(slot == 1 && material == Material.ENDER_PEARL){
            inventoryManager.getSaveTeleportInventory().setinv(p,1);
        }

        else if(slot == 48 && material == Material.ARROW){
            inventoryManager.getSaveTeleportInventory().setSelectinv1(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }
        else if(slot == 50 && material == Material.ARROW){
            inventoryManager.getSaveTeleportInventory().setSelectinv1(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }

        else if(slot == 0 && material == Material.IRON_DOOR)inventoryManager.getHomeInventory().setinv(p);
        else if(slot == 45 && material == Material.BARRIER)p.closeInventory();
    }
    public void selectevent2(Player p, Material material, int slot, InventoryClickEvent e){
        if(CommonMethods.isInSlotSet(slot)){
            ItemStack item2 = e.getCurrentItem();
            if(item2==null||!item2.hasItemMeta()||!item2.getItemMeta().hasDisplayName())return;
            String ts_name2 = item2.getItemMeta().getDisplayName();
            List<String> item_lore2 = item2.getItemMeta().getLore();
            ItemStack item1 = select.get(p.getName());
            if(item1==null||!item1.hasItemMeta()||!item1.getItemMeta().hasDisplayName())return;
            String ts_name1 = item1.getItemMeta().getDisplayName();
            List<String> item_lore1 = item1.getItemMeta().getLore();

            SaveTeleportDB saveTeleportDB = inventoryManager.getPlayerManager().getSqLiteManager().getSaveTeleportDB();
            saveTeleportDB.updateData(
                String.valueOf(p.getUniqueId()),
                ts_name1,
                "opjorheosho",
                String.valueOf(item2.getType()),
                item_lore2.get(0),
                Double.parseDouble(item_lore2.get(1)),
                Double.parseDouble(item_lore2.get(2)),
                Double.parseDouble(item_lore2.get(3)),
                Double.parseDouble(item_lore2.get(4)),
                Double.parseDouble(item_lore2.get(5))
            );
            saveTeleportDB.updateData(
                String.valueOf(p.getUniqueId()),
                ts_name2,
                ts_name1,
                String.valueOf(item1.getType()),
                item_lore1.get(0),
                Double.parseDouble(item_lore1.get(1)),
                Double.parseDouble(item_lore1.get(2)),
                Double.parseDouble(item_lore1.get(3)),
                Double.parseDouble(item_lore1.get(4)),
                Double.parseDouble(item_lore1.get(5))
            );
            saveTeleportDB.updateData(
                String.valueOf(p.getUniqueId()),
                "opjorheosho",
                ts_name2,
                String.valueOf(item2.getType()),
                item_lore2.get(0),
                Double.parseDouble(item_lore2.get(1)),
                Double.parseDouble(item_lore2.get(2)),
                Double.parseDouble(item_lore2.get(3)),
                Double.parseDouble(item_lore2.get(4)),
                Double.parseDouble(item_lore2.get(5))
            );
            p.sendMessage(ChatColor.GREEN+ts_name1+""+ChatColor.WHITE+"と"+ChatColor.GREEN+ts_name2+""+ChatColor.WHITE+"を入れ替えました");
            inventoryManager.getSaveTeleportInventory().setinv(p,1);
        }
        else if(slot == 1 && material == Material.ENDER_PEARL){
            select.remove(p.getName());
            inventoryManager.getSaveTeleportInventory().setinv(p,1);
        }

        else if(slot == 48 && material == Material.ARROW){
            inventoryManager.getSaveTeleportInventory().setSelectinv2(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }
        else if(slot == 50 && material == Material.ARROW){
            inventoryManager.getSaveTeleportInventory().setSelectinv2(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }

        else if(slot == 0 && material == Material.IRON_DOOR){
            select.remove(p.getName());
            inventoryManager.getHomeInventory().setinv(p);
        }
        else if(slot == 45 && material == Material.BARRIER){
            select.remove(p.getName());
            p.closeInventory();
        }
    }
    public void adminevent(Player p, Material material, int slot, InventoryClickEvent e){
        if(CommonMethods.isInSlotSet(slot)){
            List<String> list = e.getCurrentItem().getItemMeta().getLore();
            Location location = new Location(Bukkit.getWorld(list.get(0)),Float.parseFloat(list.get(1)),Float.parseFloat(list.get(2)),Float.parseFloat(list.get(3)),Float.parseFloat(list.get(4)),Float.parseFloat(list.get(5)));
            p.teleport(location);
        }
        else if(slot == 1 && material == Material.ENDER_PEARL){
            inventoryManager.getSaveTeleportInventory().setinv(p,1);
        }
        else if(slot == 7 && material == Material.BARRIER){
            inventoryManager.getSaveTeleportInventory().setAdminDeleteinv(p,1);
        }
        else if(slot == 48 && material == Material.ARROW){
            inventoryManager.getSaveTeleportInventory().setAdmininv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }
        else if(slot == 50 && material == Material.ARROW){
            inventoryManager.getSaveTeleportInventory().setAdmininv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }

        else if(slot == 0 && material == Material.IRON_DOOR)inventoryManager.getHomeInventory().setinv(p);
        else if(slot == 45 && material == Material.BARRIER)p.closeInventory();
    }
    public void admindeleteevent(Player p, Material material, int slot, InventoryClickEvent e){
        if(CommonMethods.isInSlotSet(slot)){
            ItemStack item = e.getCurrentItem();
            if(item==null||!item.hasItemMeta()||!item.getItemMeta().hasDisplayName())return;
            inventoryManager.getPlayerManager().getSqLiteManager().getSaveTeleportDB().deleteData("admin",item.getItemMeta().getDisplayName());
            p.sendMessage(ChatColor.WHITE+""+e.getCurrentItem().getItemMeta().getDisplayName()+ChatColor.DARK_RED+"(admin)を削除しました");
            p.closeInventory();
        }
        else if(slot == 1 && material == Material.ENDER_PEARL){
            inventoryManager.getSaveTeleportInventory().setinv(p,1);
        }
        else if(slot == 10 && material == Material.REDSTONE){
            inventoryManager.getSaveTeleportInventory().setAdmininv(p,1);
        }
        else if(slot == 48 && material == Material.ARROW){
            inventoryManager.getSaveTeleportInventory().setAdminDeleteinv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }
        else if(slot == 50 && material == Material.ARROW){
            inventoryManager.getSaveTeleportInventory().setAdminDeleteinv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }

        else if(slot == 0 && material == Material.IRON_DOOR)inventoryManager.getHomeInventory().setinv(p);
        else if(slot == 45 && material == Material.BARRIER)p.closeInventory();
    }
}
