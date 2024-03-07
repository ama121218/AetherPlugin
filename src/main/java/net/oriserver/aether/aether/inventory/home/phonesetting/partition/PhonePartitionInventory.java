package net.oriserver.aether.aether.inventory.home.phonesetting.partition;

import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PhonePartitionInventory {//インベントリー装飾のインベントリークラス
    Inventory invPhonePartition1 = Bukkit.createInventory(null, 54, "Phone Partition 1");
    Inventory invPhonePartition2 = Bukkit.createInventory(null, 54, "Phone Partition 2");
    public PhonePartitionInventory(){
        {
            invPhonePartition1.setItem(0, Item.createitem(Material.IRON_DOOR, 1, "ホーム", ""));
            invPhonePartition1.setItem(45, Item.createitem(Material.BARRIER, 1, "閉じる", ""));

            invPhonePartition1.setItem(1, Item.createitem(Material.PAINTING, 1, ChatColor.GREEN +"Phone_Setting", ""));
            invPhonePartition1.setItem(10, Item.createitem(Material.THIN_GLASS, 1, ChatColor.GREEN +"Phone_Partition", ""));

            invPhonePartition1.setItem(3, Item.createitem(Material.IRON_FENCE, 1, ChatColor.GREEN +"Phone_Partition", ""));
            invPhonePartition1.setItem(4, Item.createitem(Material.THIN_GLASS, 1, ChatColor.GREEN +"Phone_Partition", ""));
            invPhonePartition1.setItem(5, Item.createitem(Material.RAILS, 1, ChatColor.GREEN +"Phone_Partition", ""));
            invPhonePartition1.setItem(12, Item.createitem(Material.POWERED_RAIL, 1, ChatColor.GREEN +"Phone_Partition", ""));
            invPhonePartition1.setItem(13, Item.createitem(Material.ACTIVATOR_RAIL, 1, ChatColor.GREEN +"Phone_Partition", ""));
            invPhonePartition1.setItem(14, Item.createitem(Material.DETECTOR_RAIL, 1, ChatColor.GREEN +"Phone_Partition", ""));
            invPhonePartition1.setItem(21, Item.createitem(Material.LADDER, 1, ChatColor.GREEN +"Phone_Partition", ""));
            invPhonePartition1.setItem(22, Item.createitem(Material.VINE, 1, ChatColor.GREEN +"Phone_Partition", ""));

            invPhonePartition1.setItem(48,Item.createitem(Material.STRUCTURE_VOID,1,"",""));
            invPhonePartition1.setItem(50,Item.createitem(Material.ARROW,1,"2ページ目へ",""));
        }
        {
            invPhonePartition2.setItem(0, Item.createitem(Material.IRON_DOOR, 1, "ホーム", ""));
            invPhonePartition2.setItem(45, Item.createitem(Material.BARRIER, 1, "閉じる", ""));

            invPhonePartition2.setItem(1, Item.createitem(Material.PAINTING, 1, ChatColor.GREEN +"Phone_Setting", ""));
            invPhonePartition2.setItem(10, Item.createitem(Material.THIN_GLASS, 1, ChatColor.GREEN +"Phone_Partition", ""));

            invPhonePartition2.setItem(3,Item.changename(new ItemStack(Material.STAINED_GLASS_PANE,1, (short) 14),"",""));
            invPhonePartition2.setItem(4,Item.changename(new ItemStack(Material.STAINED_GLASS_PANE,1, (short) 13),"",""));
            invPhonePartition2.setItem(5,Item.changename(new ItemStack(Material.STAINED_GLASS_PANE,1, (short) 11),"",""));
            invPhonePartition2.setItem(12,Item.changename(new ItemStack(Material.STAINED_GLASS_PANE,1, (short) 4),"",""));
            invPhonePartition2.setItem(13,Item.changename(new ItemStack(Material.STAINED_GLASS_PANE,1, (short) 2),"",""));
            invPhonePartition2.setItem(14,Item.changename(new ItemStack(Material.STAINED_GLASS_PANE,1, (short) 3),"",""));
            invPhonePartition2.setItem(21,Item.changename(new ItemStack(Material.STAINED_GLASS_PANE,1, (short) 6),"",""));
            invPhonePartition2.setItem(22,Item.changename(new ItemStack(Material.STAINED_GLASS_PANE,1, (short) 1),"",""));
            invPhonePartition2.setItem(23,Item.changename(new ItemStack(Material.STAINED_GLASS_PANE,1, (short) 5),"",""));
            invPhonePartition2.setItem(30,Item.changename(new ItemStack(Material.STAINED_GLASS_PANE,1, (short) 15),"",""));
            invPhonePartition2.setItem(31,Item.changename(new ItemStack(Material.STAINED_GLASS_PANE,1, (short) 0),"",""));
            invPhonePartition2.setItem(32,Item.changename(new ItemStack(Material.STAINED_GLASS_PANE,1, (short) 12),"",""));

            invPhonePartition2.setItem(50, Item.createitem(Material.STRUCTURE_VOID,1,"",""));
            invPhonePartition2.setItem(48, Item.createitem(Material.ARROW, 1, "1ページ目へ", ""));
        }
    }

    public void setinv(Player p,int page){
        Inventory openinv;
        if(page==1)openinv = Item.inventorycopy(invPhonePartition1);
        else openinv = Item.inventorycopy(invPhonePartition2);
        Item.setInventory(p,openinv);
    }
}
