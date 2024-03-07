package net.oriserver.aether.aether.inventory.home.appearance.tag;

import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class TagInventory {//プレイヤーTagインベントリークラス
    int[] a = {3,4,5,12,13,14,21,22,23,30,31,32,39,40,41};
    ItemStack gray_dye = new ItemStack(Material.INK_SACK, 1, (short) 8);
    ArrayList<ItemStack[]> tags_list = new ArrayList<ItemStack[]>();
    Inventory invtag = Bukkit.createInventory(null, 54, "Tag");
    public TagInventory(){
        invtag.setItem(0, Item.createitem(Material.IRON_DOOR, 1, "ホーム", ""));
        invtag.setItem(45,Item.createitem(Material.BARRIER, 1, "閉じる", ""));

        invtag.setItem(1, Item.createitem(Material.TOTEM, 1, ChatColor.GREEN + "Appearance", ""));
        invtag.setItem(10, Item.createitem(Material.NAME_TAG, 1, ChatColor.GREEN + "Tag", ""));

        invtag.setItem(49, Item.createitem(Material.BARRIER, 1, ChatColor.GREEN + "称号を外す", ""));

        invtag.setItem(7, Item.createitem(Material.END_CRYSTAL, 1,ChatColor.GREEN + "Particle", ""));
        invtag.setItem(16, Item.createitem(Material.IRON_HELMET, 1,ChatColor.GREEN + "HeadBlock", ""));
        invtag.setItem(25, Item.createitem(Material.JUKEBOX, 1, ChatColor.GREEN + "Music", ""));


        //invHeadBlock.setItem(8, Item.createitem(Material.GRASS, 1, ChatColor.GREEN + "1ページ目", ""));
        //invHeadBlock.setItem(17, Item.createitem(Material.DIAMOND_BLOCK, 1, ChatColor.GREEN + "2ページ目", ""));
        //invHeadBlock.setItem(26, Item.createitem(Material.WORKBENCH, 1, ChatColor.GREEN + "3ページ目", ""));
        //invHeadBlock.setItem(35, Item.changename(new ItemStack(Material.DOUBLE_PLANT,1,(short) 5),ChatColor.GREEN +"4ページ目",""));
        iniTag();
    }
    public void setinv(Player p, String booleans, int page){
        Inventory openinv = Item.inventorycopy(invtag);
        for(int i=0;i<booleans.length();i++){
            if(booleans.charAt(i)=='1'){
                openinv.setItem(a[i],tags_list.get(page-1)[i]);
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

    public void iniTag(){
        ItemStack[] tags1 = new ItemStack[15];

        tags1[0] = Item.createitem(Material.NAME_TAG, 1, ChatColor.WHITE+"最速");
        tags1[1] = Item.createitem(Material.NAME_TAG, 1, ChatColor.WHITE+"旅人");
        tags1[2] = Item.createitem(Material.NAME_TAG, 1, ChatColor.WHITE+"努力家");
        tags1[3] = Item.createitem(Material.NAME_TAG, 1, ChatColor.WHITE+"天才");
        tags1[4] = Item.createitem(Material.NAME_TAG, 1, ChatColor.WHITE+"飛翔");
        tags1[5] = Item.createitem(Material.NAME_TAG, 1, ChatColor.WHITE+"無限");
        tags1[6] = Item.createitem(Material.NAME_TAG, 1, ChatColor.WHITE+"???");
        tags1[7] = Item.createitem(Material.NAME_TAG, 1, ChatColor.WHITE+"冷徹");
        tags1[8] = Item.createitem(Material.NAME_TAG, 1, ChatColor.WHITE+"休憩");
        tags1[9] = Item.createitem(Material.NAME_TAG, 1, ChatColor.WHITE+"バニホ");
        tags1[10] = Item.createitem(Material.NAME_TAG, 1, ChatColor.WHITE+"初老");
        tags1[11] = Item.createitem(Material.NAME_TAG, 1, ChatColor.WHITE+"・。・");
        tags1[12] = Item.createitem(Material.NAME_TAG, 1, ChatColor.WHITE+"。・。");
        tags1[13] = Item.createitem(Material.NAME_TAG, 1, ChatColor.WHITE+"(*‘ω‘ *)");
        tags1[14] = Item.createitem(Material.NAME_TAG, 1, ChatColor.WHITE+"('ω')ノ");

        //ItemStack[] headblock2 = new ItemStack[15];


        //ItemStack[] headblock3 = new ItemStack[15];


        //ItemStack[] headblock4 = new ItemStack[15];


        tags_list.add(tags1);
        //tags_list.add(headblock2);
        //tags_list.add(headblock3);
        //tags_list.add(headblock4);
    }
}
