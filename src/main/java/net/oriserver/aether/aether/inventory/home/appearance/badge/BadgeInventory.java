package net.oriserver.aether.aether.inventory.home.appearance.badge;

import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class BadgeInventory {//Badgeインベントリークラス
    int[] a = {3,4,5,12,13,14,21,22,23,30,31,32,39,40,41};
    ItemStack gray_dye = new ItemStack(Material.INK_SACK, 1, (short) 8);
    ArrayList<ItemStack[]> badges_list = new ArrayList<ItemStack[]>();
    Inventory invBadge = Bukkit.createInventory(null, 54, "Badge");
    public BadgeInventory(){
        invBadge.setItem(0, Item.createitem(Material.IRON_DOOR, 1, "ホーム", ""));
        invBadge.setItem(45,Item.createitem(Material.BARRIER, 1, "閉じる", ""));

        invBadge.setItem(1, Item.createitem(Material.TOTEM, 1, ChatColor.GREEN + "Appearance", ""));
        invBadge.setItem(10, Item.createitem(Material.PRISMARINE_CRYSTALS, 1, ChatColor.GREEN + "Badge", ""));

        invBadge.setItem(49, Item.createitem(Material.BARRIER, 1, ChatColor.GREEN + "バッジを外す", ""));

        invBadge.setItem(7, Item.createitem(Material.END_CRYSTAL, 1,ChatColor.GREEN + "Particle", ""));
        invBadge.setItem(16, Item.createitem(Material.IRON_HELMET, 1,ChatColor.GREEN + "HeadBlock", ""));
        invBadge.setItem(25, Item.createitem(Material.JUKEBOX, 1, ChatColor.GREEN + "Music", ""));

        //invHeadBlock.setItem(8, Item.createitem(Material.GRASS, 1, ChatColor.GREEN + "1ページ目", ""));
        //invHeadBlock.setItem(17, Item.createitem(Material.DIAMOND_BLOCK, 1, ChatColor.GREEN + "2ページ目", ""));
        //invHeadBlock.setItem(26, Item.createitem(Material.WORKBENCH, 1, ChatColor.GREEN + "3ページ目", ""));
        //invHeadBlock.setItem(35, Item.changename(new ItemStack(Material.DOUBLE_PLANT,1,(short) 5),ChatColor.GREEN +"4ページ目",""));
        iniBadge();
    }
    public void setinv(Player p,String booleans, int page){
        Inventory openinv = Item.inventorycopy(invBadge);
        for(int i=0;i<booleans.length();i++){
            if(booleans.charAt(i)=='1'){
                openinv.setItem(a[i],badges_list.get(page-1)[i]);
            }else{
                openinv.setItem(a[i],gray_dye);
            }
        }
        if(page==1){
            openinv.setItem(48, Item.createitem(Material.STRUCTURE_VOID, 1, "", ""));
            //openinv.setItem(50, Item.createitem(Material.ARROW, 1, "2ページ目へ", ""));
        }/*else if(page==2){
            openinv.setItem(48, Item.createitem(Material.ARROW, 1, "1ページ目へ", ""));
            openinv.setItem(50, Item.createitem(Material.ARROW, 1, "3ページ目へ", ""));
        }else if(page==3){
            openinv.setItem(48, Item.createitem(Material.ARROW, 1, "2ページ目へ", ""));
            openinv.setItem(50, Item.createitem(Material.ARROW, 1, "4ページ目へ", ""));
        }else if(page==4){
            openinv.setItem(48, Item.createitem(Material.ARROW, 1, "3ページ目へ", ""));
            openinv.setItem(50, Item.createitem(Material.STRUCTURE_VOID, 1, "", ""));
        }*/
        Item.setInventory(p,openinv);
    }

    public void iniBadge(){
        ItemStack[] badges1 = new ItemStack[15];

        badges1[0] = Item.createitem(Material.PRISMARINE_CRYSTALS, 1, ChatColor.WHITE+"----");
        badges1[1] = Item.createitem(Material.PRISMARINE_CRYSTALS, 1, ChatColor.WHITE+"*");
        badges1[2] = Item.createitem(Material.PRISMARINE_CRYSTALS, 1, ChatColor.WHITE+"+");
        badges1[3] = Item.createitem(Material.PRISMARINE_CRYSTALS, 1, ChatColor.WHITE+".");
        badges1[4] = Item.createitem(Material.PRISMARINE_CRYSTALS, 1, ChatColor.WHITE+"()");
        badges1[5] = Item.createitem(Material.PRISMARINE_CRYSTALS, 1, ChatColor.WHITE+"$");
        badges1[6] = Item.createitem(Material.PRISMARINE_CRYSTALS, 1, ChatColor.WHITE+"=");
        badges1[7] = Item.createitem(Material.PRISMARINE_CRYSTALS, 1, ChatColor.WHITE+"!");
        badges1[8] = Item.createitem(Material.PRISMARINE_CRYSTALS, 1, ChatColor.WHITE+"~");
        badges1[9] = Item.createitem(Material.PRISMARINE_CRYSTALS, 1, ChatColor.WHITE+":");
        badges1[10] = Item.createitem(Material.PRISMARINE_CRYSTALS, 1, ChatColor.WHITE+"#");
        badges1[11] = Item.createitem(Material.PRISMARINE_CRYSTALS, 1, ChatColor.WHITE+"&");
        badges1[12] = Item.createitem(Material.PRISMARINE_CRYSTALS, 1, ChatColor.WHITE+"v");
        badges1[13] = Item.createitem(Material.PRISMARINE_CRYSTALS, 1, ChatColor.WHITE+"w");
        badges1[14] = Item.createitem(Material.PRISMARINE_CRYSTALS, 1, ChatColor.WHITE+"T");

        //ItemStack[] headblock2 = new ItemStack[15];


        //ItemStack[] headblock3 = new ItemStack[15];


        //ItemStack[] headblock4 = new ItemStack[15];


        badges_list.add(badges1);
        //badges_list.add(headblock2);
        //badges_list.add(headblock3);
        //badges_list.add(headblock4);
    }
}
