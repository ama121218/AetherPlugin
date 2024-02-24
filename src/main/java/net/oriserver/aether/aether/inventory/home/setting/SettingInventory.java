package net.oriserver.aether.aether.inventory.home.setting;

import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SettingInventory {
    int[] a = {12,13,14,30,31,32};
    ItemStack[] onitems = new ItemStack[6];
    ItemStack[] offitems = new ItemStack[6];

    Inventory invSetting = Bukkit.createInventory(null, 54, "Setting");
    public SettingInventory(){
        onitems[0] = Item.createitem(Material.REDSTONE_TORCH_ON,1,ChatColor.GREEN +"on","パーティクル非表示","クリックすると表示できます");
        onitems[1] = Item.createitem(Material.REDSTONE_TORCH_ON,1,ChatColor.GREEN +"on","メール受け取りをブロックします","クリックすると解除できます");
        onitems[2] = Item.createitem(Material.REDSTONE_TORCH_ON,1,ChatColor.GREEN +"on","フレンド申請をブロックします","クリックすると解除できます");
        onitems[3] = Item.createitem(Material.REDSTONE_TORCH_ON,1,ChatColor.GREEN +"on","join時にGeneralチャンネルに入りません","クリックすると入るようにできます");
        onitems[4] = Item.createitem(Material.REDSTONE_TORCH_ON,1,ChatColor.GREEN +"on","プレイヤーサイドバー非表示","クリックすると表示にできます");
        onitems[5] = Item.createitem(Material.REDSTONE_TORCH_ON,1,ChatColor.GREEN +"on","ChartAthleticクリア時にそのステージへテレポートする","クリックするとしないにできます");
        offitems[0] = Item.createitem(Material.LEVER, 1, ChatColor.GREEN + "off", "パーティクルを表示","クリックすると非表示にできます");
        offitems[1] = Item.createitem(Material.LEVER, 1, ChatColor.GREEN + "off", "メール受け取りをブロックしません","クリックするとブロックできます");
        offitems[2] = Item.createitem(Material.LEVER, 1, ChatColor.GREEN + "off", "フレンド申請をブロックしません","クリックするとブロックにできます");
        offitems[3] = Item.createitem(Material.LEVER, 1, ChatColor.GREEN + "off", "join時にGeneralチャンネルに入ります","クリックすると入らないようにできます");
        offitems[4] = Item.createitem(Material.LEVER, 1, ChatColor.GREEN + "off", "サイドバーを表示","クリックすると非表示にできます");
        offitems[5] = Item.createitem(Material.LEVER,1,ChatColor.GREEN +"off","ChartAthleticクリア時にそのステージへテレポートしない","クリックするとするにできます");

        invSetting.setItem(0, Item.createitem(Material.IRON_DOOR, 1, "ホーム", ""));
        invSetting.setItem(45,Item.createitem(Material.BARRIER, 1, "閉じる", ""));

        invSetting.setItem(1, Item.createitem(Material.COMPASS, 1, ChatColor.GREEN + "Setting", ""));

        for(int i=0;i<offitems.length;i++)invSetting.setItem(a[i],offitems[i]);

        invSetting.setItem(3, Item.createitem(Material.END_CRYSTAL, 1, ChatColor.GREEN + "Particle", "パーティクル表示設定"));
        invSetting.setItem(4, Item.createitem(Material.STORAGE_MINECART, 1, ChatColor.GREEN + "Mail", "メール受け取り設定"));
        invSetting.setItem(5, Item.createitem(Material.SKULL_ITEM, 1, ChatColor.GREEN + "Friend", "フレンド申請設定"));
        invSetting.setItem(21, Item.createitem(Material.PAPER, 1, ChatColor.GREEN + "Chatroom", "join時にGeneralチャンネル設定"));
        invSetting.setItem(22, Item.createitem(Material.LEATHER, 1, ChatColor.GREEN + "Sidebar", "サイドバー表示設定"));
        invSetting.setItem(23, Item.createitem(Material.APPLE, 1, ChatColor.GREEN + "ChartAthletic", "クリア時のテレポート設定"));
    }
    public void setinv(Player p,boolean[] booleans){
        Inventory openinv = Item.inventorycopy(invSetting);
        for(int i=0;i<6;i++){
            if(booleans[i]){
                openinv.setItem(a[i],onitems[i]);
            }
        }
        Item.setInventory(p,openinv);
    }
}
