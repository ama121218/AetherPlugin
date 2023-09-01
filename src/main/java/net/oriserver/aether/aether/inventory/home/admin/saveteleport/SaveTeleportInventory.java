package net.oriserver.aether.aether.inventory.home.admin.saveteleport;

import net.oriserver.aether.aether.statics.Item;
import net.oriserver.aether.aether.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class SaveTeleportInventory {
    final private PlayerManager pm;
    int[] a = {3,4,5,12,13,14,21,22,23,30,31,32,39,40,41};
    Inventory invSaveTeleport = Bukkit.createInventory(null, 54, "Save Teleport");
    public SaveTeleportInventory(PlayerManager playerManager){
        this.pm = playerManager;
        invSaveTeleport.setItem(0, Item.createitem(Material.IRON_DOOR, 1, "ホーム", ""));
        invSaveTeleport.setItem(45,Item.createitem(Material.BARRIER, 1, "閉じる", ""));

        invSaveTeleport.setItem(1,Item.createitem(Material.ENDER_PEARL, 1, "Save_Teleport", ""));
    }
    public void setinv(Player p,int page){
        Inventory openinv = getSaveTeleport(String.valueOf(p.getUniqueId()),page);
        openinv.setItem(7,Item.createitem(Material.BARRIER,1, ChatColor.DARK_RED+"Delete_Save_Teleport",""));
        openinv.setItem(16,Item.createitem(Material.MINECART,1,ChatColor.YELLOW+"Select_Save_Teleport",""));

        openinv.setItem(8,Item.createitem(Material.SADDLE,1,"現在地を保存する",""));

        openinv.setItem(18,Item.createitem(Material.CHORUS_FRUIT_POPPED,1,ChatColor.GREEN+"Teleport_Spawn",""));

        openinv.setItem(52,Item.createitem(Material.REDSTONE,1,ChatColor.LIGHT_PURPLE+"Admin_Save_Teleport",""));

        Item.setInventory(p,openinv);
    }
    public void setDeleteinv(Player p,int page){
        Inventory inv = getSaveTeleport(String.valueOf(p.getUniqueId()),page);
        Inventory openinv = Bukkit.createInventory(null,54,ChatColor.DARK_RED+"Delete Save Teleport");
        ItemStack[] allitem = inv.getContents();
        openinv.setContents(allitem);
        openinv.setItem(10, Item.createitem(Material.BARRIER, 1, ChatColor.GREEN + "Delete_Save_Teleport", ""));
        Item.setInventory(p,openinv);
    }
    public void setSelectinv1(Player p,int page){
        Inventory inv = getSaveTeleport(String.valueOf(p.getUniqueId()),page);
        Inventory openinv = Bukkit.createInventory(null,54,ChatColor.YELLOW+"Select Save Teleport 1");
        ItemStack[] allitem = inv.getContents();
        openinv.setContents(allitem);
        openinv.setItem(10, Item.createitem(Material.MINECART, 1, ChatColor.GREEN + "Select_Save_Teleport_1", ""));
        Item.setInventory(p,openinv);
    }
    public void setSelectinv2(Player p,int page){
        Inventory inv = getSaveTeleport(String.valueOf(p.getUniqueId()),page);
        Inventory openinv = Bukkit.createInventory(null,54,ChatColor.YELLOW+"Select Save Teleport 2");
        ItemStack[] allitem = inv.getContents();
        openinv.setContents(allitem);
        openinv.setItem(10, Item.createitem(Material.MINECART, 2, ChatColor.GREEN + "Select_Save_Teleport_2", ""));
        Item.setInventory(p,openinv);
    }
    public void setAdmininv(Player p,int page){
        Inventory inv = getSaveTeleport("admin",page);
        Inventory openinv = Bukkit.createInventory(null,54,ChatColor.LIGHT_PURPLE+"Admin Save Teleport");
        ItemStack[] allitem = inv.getContents();
        openinv.setContents(allitem);
        openinv.setItem(10, Item.createitem(Material.REDSTONE, 1, ChatColor.GREEN + "Admin_Save_Teleport", ""));
        openinv.setItem(7,Item.createitem(Material.BARRIER,1, ChatColor.DARK_RED+"Admin_Delete_Save_Teleport",""));
        Item.setInventory(p,openinv);
    }
    public void setAdminDeleteinv(Player p,int page){
        Inventory inv = getSaveTeleport("admin",page);
        Inventory openinv = Bukkit.createInventory(null,54,ChatColor.DARK_RED+"Admin Delete Save Teleport");
        ItemStack[] allitem = inv.getContents();
        openinv.setContents(allitem);
        openinv.setItem(10, Item.createitem(Material.REDSTONE, 1, ChatColor.GREEN + "Admin_Save_Teleport", ""));
        openinv.setItem(19, Item.createitem(Material.BARRIER, 1, ChatColor.GREEN + "Admin_Delete_Save_Teleport", ""));
        Item.setInventory(p,openinv);
    }

    public Inventory getSaveTeleport(String uuid,int page){
        Inventory openinv = Item.inventorycopy(invSaveTeleport);
        ArrayList<Object[]> list_objects = pm.getSqLiteManager().getSaveTeleportDB().getData(uuid);
        int size = list_objects.size();
        if(size==0){
            return openinv;
        }
        for(int i=0;i<size-(15*(page-1))&&i<15;i++){
            Object[] objects = list_objects.get(i+15*(page-1));
            String st_name = String.valueOf(objects[0]);
            String block = String.valueOf(objects[1]);
            String world = String.valueOf(objects[2]);
            double x = (double) objects[3];
            double y = (double) objects[4];
            double z = (double) objects[5];
            double yaw = (double) objects[6];
            double pitch = (double) objects[7];
            openinv.setItem(a[i], Item.createitem(Material.getMaterial(block), 1, st_name, world,String.valueOf(x),String.valueOf(y),String.valueOf(z),String.valueOf(yaw),String.valueOf(pitch)));
        }
        if(page==1){
            if(size>15){
                openinv.setItem(48, Item.createitem(Material.STRUCTURE_VOID, 1, "", ""));
                openinv.setItem(50, Item.createitem(Material.ARROW, 1, "2ページ目へ", ""));
            }
        }
        else if(page>=2){
            if(size>15*page){
                openinv.setItem(48, Item.createitem(Material.ARROW, 1, (page-1)+"ページ目へ", ""));
                openinv.setItem(50, Item.createitem(Material.ARROW, 1, (page+1)+"ページ目へ", ""));
            }else{
                openinv.setItem(48, Item.createitem(Material.ARROW, 1, (page-1)+"ページ目へ", ""));
                openinv.setItem(50, Item.createitem(Material.STRUCTURE_VOID, 1, "", ""));
            }
        }
        return openinv;
    }
}
