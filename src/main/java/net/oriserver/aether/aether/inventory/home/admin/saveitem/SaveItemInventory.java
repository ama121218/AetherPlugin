package net.oriserver.aether.aether.inventory.home.admin.saveitem;

import net.oriserver.aether.aether.statics.Item;
import net.oriserver.aether.aether.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class SaveItemInventory {//アイテムを保存するのインベントリークラス
    final private PlayerManager pm;
    int[] a = {3,4,5,12,13,14,21,22,23,30,31,32,39,40,41};
    Inventory invSaveItem = Bukkit.createInventory(null, 54, "Save Item");
    public SaveItemInventory(PlayerManager playerManager){
        this.pm = playerManager;
        invSaveItem.setItem(0, Item.createitem(Material.IRON_DOOR, 1, "ホーム", ""));
        invSaveItem.setItem(45,Item.createitem(Material.BARRIER, 1, "閉じる", ""));

        invSaveItem.setItem(1,Item.createitem(Material.IRON_AXE, 1, "Save_Item", ""));
    }
    public void setinv(Player p,int page){
        Inventory openinv = getSaveItem(String.valueOf(p.getUniqueId()),page);
        openinv.setItem(7,Item.createitem(Material.BARRIER,1, ChatColor.DARK_RED+"Delete_Save_Item",""));
        openinv.setItem(16,Item.createitem(Material.MINECART,1,ChatColor.YELLOW+"Select_Save_Item",""));

        openinv.setItem(52,Item.createitem(Material.REDSTONE,1,ChatColor.LIGHT_PURPLE+"Admin_Save_Item",""));
        Item.setInventory(p,openinv);
    }
    public void setDeleteinv(Player p,int page){
        Inventory inv = getSaveItem(String.valueOf(p.getUniqueId()),page);
        Inventory openinv = Bukkit.createInventory(null,54,ChatColor.DARK_RED+"Delete Save Item");
        ItemStack[] allitem = inv.getContents();
        openinv.setContents(allitem);
        openinv.setItem(10, Item.createitem(Material.BARRIER, 1, ChatColor.GREEN + "Delete_Save_Item", ""));
        Item.setInventory(p,openinv);
    }
    public void setSelectinv1(Player p,int page){
        Inventory inv = getSaveItem(String.valueOf(p.getUniqueId()),page);
        Inventory openinv = Bukkit.createInventory(null,54,ChatColor.YELLOW+"Select Save Item 1");
        ItemStack[] allitem = inv.getContents();
        openinv.setContents(allitem);
        openinv.setItem(10, Item.createitem(Material.MINECART, 1, ChatColor.GREEN + "Select_Save_Item_1", ""));
        Item.setInventory(p,openinv);
    }
    public void setSelectinv2(Player p,int page){
        Inventory inv = getSaveItem(String.valueOf(p.getUniqueId()),page);
        Inventory openinv = Bukkit.createInventory(null,54,ChatColor.YELLOW+"Select Save Item 2");
        ItemStack[] allitem = inv.getContents();
        openinv.setContents(allitem);
        openinv.setItem(10, Item.createitem(Material.MINECART, 2, ChatColor.GREEN + "Select_Save_Item_2", ""));
        Item.setInventory(p,openinv);
    }
    public void setAdmininv(Player p,int page){
        Inventory inv = getSaveItem("admin",page);
        Inventory openinv = Bukkit.createInventory(null,54,ChatColor.LIGHT_PURPLE+"Admin Save Item");
        ItemStack[] allitem = inv.getContents();
        openinv.setContents(allitem);
        openinv.setItem(10, Item.createitem(Material.REDSTONE, 1, ChatColor.GREEN + "Admin_Save_Item", ""));
        openinv.setItem(7,Item.createitem(Material.BARRIER,1, ChatColor.DARK_RED+"Admin_Delete_Save_Item",""));
        Item.setInventory(p,openinv);
    }
    public void setAdminDeleteinv(Player p,int page){
        Inventory inv = getSaveItem("admin",page);
        Inventory openinv = Bukkit.createInventory(null,54,ChatColor.DARK_RED+"Admin Delete Save Item");
        ItemStack[] allitem = inv.getContents();
        openinv.setContents(allitem);
        openinv.setItem(10, Item.createitem(Material.REDSTONE, 1, ChatColor.GREEN + "Admin_Save_Item", ""));
        openinv.setItem(19, Item.createitem(Material.BARRIER, 1, ChatColor.DARK_RED + "Admin_Delete_Save_Item", ""));
        Item.setInventory(p,openinv);
    }

    public Inventory getSaveItem(String uuid,int page){
        Inventory openinv = Item.inventorycopy(invSaveItem);
        ArrayList<Object[]> list_objects = pm.getSqLiteManager().getSaveItemDB().getData(uuid);
        int size = list_objects.size();
        if(size==0){
            return openinv;
        }
        for(int i=0;i<size-(15*(page-1))&&i<15;i++){
            Object[] objects = list_objects.get(i+15*(page-1));
            String item = String.valueOf(objects[0]);
            String item_name = String.valueOf(objects[1]);
            openinv.setItem(a[i], Item.createitem(Material.getMaterial(item), 1, item_name,""));
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
