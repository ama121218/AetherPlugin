package net.oriserver.aether.aether.inventory.home.admin.saveteleport;

import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.sqlite.SaveTeleportDB;
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

public class SaveTeleportInventoryClick {
    final private InventoryManager inventoryManager;

    HashMap<String,ItemStack> select = new HashMap<String,ItemStack>();

    public SaveTeleportInventoryClick(InventoryManager inventoryManager){this.inventoryManager = inventoryManager;}
    public void event(Player p, Material material, int slot, InventoryClickEvent e){
        if(slot == 3||slot == 4||slot == 5||slot == 12||slot == 13||slot == 14||slot == 21||slot == 22||slot == 23||slot == 30||slot == 31||slot == 32||slot == 39||slot == 40||slot == 41){
            List<String> list = e.getCurrentItem().getItemMeta().getLore();
            Location location = new Location(Bukkit.getWorld(list.get(0)),Float.parseFloat(list.get(1)),Float.parseFloat(list.get(2)),Float.parseFloat(list.get(3)),Float.parseFloat(list.get(4)),Float.parseFloat(list.get(5)));
            p.teleport(location);
        }
        else if(material == Material.BARRIER && slot == 7){
            inventoryManager.getSaveTeleportInventory().setDeleteinv(p,1);
        }
        else if(material == Material.MINECART && slot == 16){
            inventoryManager.getSaveTeleportInventory().setSelectinv1(p,1);
        }
        else if(material == Material.CHORUS_FRUIT_POPPED && slot == 18){
            p.teleport(p.getLocation().getWorld().getSpawnLocation());
        }
        else if(material == Material.REDSTONE && slot == 52) {
            inventoryManager.getSaveTeleportInventory().setAdmininv(p, 1);
        }
        else if(material == Material.SADDLE && slot == 8){
            Random random = new Random();
            String st_name = String.format("%06d", random.nextInt(1000000));
            String item = String.valueOf(Material.STONE);
            Location l = p.getLocation();
            inventoryManager.getPlayerManager().getSqLiteManager().getSaveTeleportDB().insertData(String.valueOf(p.getUniqueId()),st_name,item,l.getWorld().getName(),l.getX(),l.getY(),l.getZ(),l.getYaw(),l.getPitch());
            p.sendMessage(ChatColor.GOLD + "現在地を" + ChatColor.WHITE + st_name + ChatColor.GOLD + "として設定しました");
            p.closeInventory();
        }
        else if(material == Material.ARROW && slot == 48){
            inventoryManager.getSaveTeleportInventory().setinv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }
        else if(material == Material.ARROW && slot == 50){
            inventoryManager.getSaveTeleportInventory().setinv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }

        else if(material == Material.IRON_DOOR && slot == 0)inventoryManager.getHomeInventory().setinv(p);
        else if(material == Material.BARRIER && slot == 45)p.closeInventory();
    }
    public void deleteevent(Player p, Material material, int slot, InventoryClickEvent e){
        if(slot == 3||slot == 4||slot == 5||slot == 12||slot == 13||slot == 14||slot == 21||slot == 22||slot == 23||slot == 30||slot == 31||slot == 32||slot == 39||slot == 40||slot == 41){
            ItemStack item = e.getCurrentItem();
            if(item==null||!item.hasItemMeta()||!item.getItemMeta().hasDisplayName())return;
            inventoryManager.getPlayerManager().getSqLiteManager().getSaveTeleportDB().deleteData(String.valueOf(p.getUniqueId()),item.getItemMeta().getDisplayName());
            p.sendMessage(ChatColor.WHITE+""+e.getCurrentItem().getItemMeta().getDisplayName()+ChatColor.DARK_RED+"を削除しました");
            p.closeInventory();
        }
        else if(material == Material.ENDER_PEARL && slot == 1){
            inventoryManager.getSaveTeleportInventory().setinv(p,1);
        }

        else if(material == Material.ARROW && slot == 48){
            inventoryManager.getSaveTeleportInventory().setDeleteinv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }
        else if(material == Material.ARROW && slot == 50){
            inventoryManager.getSaveTeleportInventory().setDeleteinv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }

        else if(material == Material.IRON_DOOR && e.getRawSlot() == 0)inventoryManager.getHomeInventory().setinv(p);
        else if(material == Material.BARRIER && e.getRawSlot() == 45)p.closeInventory();
    }
    public void selectevent1(Player p, Material material, int slot, InventoryClickEvent e){
        if(slot == 3||slot == 4||slot == 5||slot == 12||slot == 13||slot == 14||slot == 21||slot == 22||slot == 23||slot == 30||slot == 31||slot == 32||slot == 39||slot == 40||slot == 41){
            select.put(p.getName(),e.getCurrentItem());
            inventoryManager.getSaveTeleportInventory().setSelectinv2(p,1);
        }
        else if(material == Material.ENDER_PEARL && slot == 1){
            inventoryManager.getSaveTeleportInventory().setinv(p,1);
        }

        else if(material == Material.ARROW && slot == 48){
            inventoryManager.getSaveTeleportInventory().setSelectinv1(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }
        else if(material == Material.ARROW && slot == 50){
            inventoryManager.getSaveTeleportInventory().setSelectinv1(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }

        else if(material == Material.IRON_DOOR && e.getRawSlot() == 0)inventoryManager.getHomeInventory().setinv(p);
        else if(material == Material.BARRIER && e.getRawSlot() == 45)p.closeInventory();
    }
    public void selectevent2(Player p, Material material, int slot, InventoryClickEvent e){
        if(slot == 3||slot == 4||slot == 5||slot == 12||slot == 13||slot == 14||slot == 21||slot == 22||slot == 23||slot == 30||slot == 31||slot == 32||slot == 39||slot == 40||slot == 41){
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
        else if(material == Material.ENDER_PEARL && slot == 1){
            select.remove(p.getName());
            inventoryManager.getSaveTeleportInventory().setinv(p,1);
        }

        else if(material == Material.ARROW && slot == 48){
            inventoryManager.getSaveTeleportInventory().setSelectinv2(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }
        else if(material == Material.ARROW && slot == 50){
            inventoryManager.getSaveTeleportInventory().setSelectinv2(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }

        else if(material == Material.IRON_DOOR && e.getRawSlot() == 0){
            select.remove(p.getName());
            inventoryManager.getHomeInventory().setinv(p);
        }
        else if(material == Material.BARRIER && e.getRawSlot() == 45){
            select.remove(p.getName());
            p.closeInventory();
        }
    }
    public void adminevent(Player p, Material material, int slot, InventoryClickEvent e){
        if(slot == 3||slot == 4||slot == 5||slot == 12||slot == 13||slot == 14||slot == 21||slot == 22||slot == 23||slot == 30||slot == 31||slot == 32||slot == 39||slot == 40||slot == 41){
            List<String> list = e.getCurrentItem().getItemMeta().getLore();
            Location location = new Location(Bukkit.getWorld(list.get(0)),Float.parseFloat(list.get(1)),Float.parseFloat(list.get(2)),Float.parseFloat(list.get(3)),Float.parseFloat(list.get(4)),Float.parseFloat(list.get(5)));
            p.teleport(location);
        }
        else if(material == Material.ENDER_PEARL && slot == 1){
            inventoryManager.getSaveTeleportInventory().setinv(p,1);
        }
        else if(material == Material.BARRIER && slot == 7){
            inventoryManager.getSaveTeleportInventory().setAdminDeleteinv(p,1);
        }
        else if(material == Material.ARROW && slot == 48){
            inventoryManager.getSaveTeleportInventory().setAdmininv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }
        else if(material == Material.ARROW && slot == 50){
            inventoryManager.getSaveTeleportInventory().setAdmininv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }

        else if(material == Material.IRON_DOOR && slot == 0)inventoryManager.getHomeInventory().setinv(p);
        else if(material == Material.BARRIER && slot == 45)p.closeInventory();
    }
    public void admindeleteevent(Player p, Material material, int slot, InventoryClickEvent e){
        if(slot == 3||slot == 4||slot == 5||slot == 12||slot == 13||slot == 14||slot == 21||slot == 22||slot == 23||slot == 30||slot == 31||slot == 32||slot == 39||slot == 40||slot == 41){
            ItemStack item = e.getCurrentItem();
            if(item==null||!item.hasItemMeta()||!item.getItemMeta().hasDisplayName())return;
            inventoryManager.getPlayerManager().getSqLiteManager().getSaveTeleportDB().deleteData("admin",item.getItemMeta().getDisplayName());
            p.sendMessage(ChatColor.WHITE+""+e.getCurrentItem().getItemMeta().getDisplayName()+ChatColor.DARK_RED+"(admin)を削除しました");
            p.closeInventory();
        }
        else if(material == Material.ENDER_PEARL && slot == 1){
            inventoryManager.getSaveTeleportInventory().setinv(p,1);
        }
        else if(material == Material.REDSTONE && slot == 10){
            inventoryManager.getSaveTeleportInventory().setAdmininv(p,1);
        }
        else if(material == Material.ARROW && slot == 48){
            inventoryManager.getSaveTeleportInventory().setAdminDeleteinv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }
        else if(material == Material.ARROW && slot == 50){
            inventoryManager.getSaveTeleportInventory().setAdminDeleteinv(p,Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0)));
        }

        else if(material == Material.IRON_DOOR && e.getRawSlot() == 0)inventoryManager.getHomeInventory().setinv(p);
        else if(material == Material.BARRIER && e.getRawSlot() == 45)p.closeInventory();
    }
}
