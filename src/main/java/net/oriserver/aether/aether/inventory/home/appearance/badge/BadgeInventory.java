package net.oriserver.aether.aether.inventory.home.appearance.badge;

import net.oriserver.aether.aether.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class BadgeInventory {
    int[] a = {3,4,5,12,13,14,21,22,23,30,31,32,39,40,41};
    ItemStack gray_dye = new ItemStack(Material.INK_SACK, 1, (short) 8);
    ArrayList<ItemStack[]> headblock = new ArrayList<ItemStack[]>();
    Inventory invBadge = Bukkit.createInventory(null, 54, "HeadBlock");
    public BadgeInventory(){
        invBadge.setItem(0, Item.createitem(Material.IRON_DOOR, 1, "ホーム", ""));
        invBadge.setItem(45,Item.createitem(Material.BARRIER, 1, "閉じる", ""));

        invBadge.setItem(1, Item.createitem(Material.TOTEM, 1, ChatColor.GREEN + "Appearance", ""));
        invBadge.setItem(10, Item.createitem(Material.PRISMARINE_CRYSTALS, 1, ChatColor.GREEN + "Badge", ""));

        invBadge.setItem(7, Item.createitem(Material.END_CRYSTAL, 1,ChatColor.GREEN + "Particle", ""));
        invBadge.setItem(16, Item.createitem(Material.IRON_HELMET, 1,ChatColor.GREEN + "HeadBlock", ""));
        invBadge.setItem(25, Item.createitem(Material.JUKEBOX, 1, ChatColor.GREEN + "Music", ""));

        //invHeadBlock.setItem(8, Item.createitem(Material.GRASS, 1, ChatColor.GREEN + "1ページ目", ""));
        //invHeadBlock.setItem(17, Item.createitem(Material.DIAMOND_BLOCK, 1, ChatColor.GREEN + "2ページ目", ""));
        //invHeadBlock.setItem(26, Item.createitem(Material.WORKBENCH, 1, ChatColor.GREEN + "3ページ目", ""));
        //invHeadBlock.setItem(35, Item.changename(new ItemStack(Material.DOUBLE_PLANT,1,(short) 5),ChatColor.GREEN +"4ページ目",""));
        //iniBadge();
    }
    public void setinv(Player p){
        Inventory openinv = Item.inventorycopy(invBadge);
        Item.setInventory(p,openinv);
    }
    /*public void setinv(Player p, int partition, String booleans, int page){
        Inventory openinv = Item.inventorycopy(invBadge);
        for(int i=0;i<booleans.length();i++){
            if(booleans.charAt(i)=='1'){
                openinv.setItem(a[i],headblock.get(page)[i]);
            }else{
                openinv.setItem(a[i],gray_dye);
            }
        }
        if(page==1){
            openinv.setItem(48, Item.createitem(Material.STRUCTURE_VOID, 1, "", ""));
            openinv.setItem(50, Item.createitem(Material.ARROW, 1, "2ページ目へ", ""));
        }else if(page==2){
            openinv.setItem(48, Item.createitem(Material.ARROW, 1, "1ページ目へ", ""));
            openinv.setItem(50, Item.createitem(Material.ARROW, 1, "3ページ目へ", ""));
        }else if(page==3){
            openinv.setItem(48, Item.createitem(Material.ARROW, 1, "2ページ目へ", ""));
            openinv.setItem(50, Item.createitem(Material.ARROW, 1, "4ページ目へ", ""));
        }else if(page==4){
            openinv.setItem(48, Item.createitem(Material.ARROW, 1, "3ページ目へ", ""));
            openinv.setItem(50, Item.createitem(Material.STRUCTURE_VOID, 1, "", ""));
        }
        Item.setInventory(p,openinv,partition);
    }

    public void iniBadge(){
        ItemStack[] headblock1 = new ItemStack[15];


        ItemStack[] headblock2 = new ItemStack[15];


        ItemStack[] headblock3 = new ItemStack[15];


        ItemStack[] headblock4 = new ItemStack[15];


        headblock.add(headblock1);
        headblock.add(headblock2);
        headblock.add(headblock3);
        headblock.add(headblock4);
    }*/
}
