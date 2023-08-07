package net.oriserver.aether.aether.inventory.chart;


import net.oriserver.aether.aether.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChartInventory {
    Inventory invChart = Bukkit.createInventory(null, 54, "Chart_Athletic");
    int[] a = {3,4,5,12,13,14,21,22,23,30,31,32,39,40};
    int[] c = {7,16,25,34};
    Material[] materials = {
            Material.GRASS,
            Material.SAND,
            Material.SNOW_BLOCK,
            Material.QUARTZ_BLOCK
    };
    ItemStack gray_dye = new ItemStack(Material.INK_SACK,1, (short) 8);
    public ChartInventory(){
        invChart.setItem(0, Item.createitem(Material.IRON_DOOR, 1, "ホーム", ""));
        invChart.setItem(45,Item.createitem(Material.BARRIER, 1, "閉じる", ""));

        invChart.setItem(1,Item.createitem(Material.APPLE, 1, "Chart_Athletic", ChatColor.WHITE+"1"));

        invChart.setItem(7, Item.createitem(Material.GRASS, 1, "1ステージ", ""));
        invChart.setItem(16, Item.createitem(Material.SAND, 1, "2ステージ", ""));
        invChart.setItem(43, Item.createitem(Material.SNOW_BLOCK, 1, "3ステージ", ""));
        invChart.setItem(52, Item.createitem(Material.QUARTZ_BLOCK, 1, "4ステージ", ""));
    }
    public void setinv(Player p,int page,int chart){
        Inventory openinv = Item.inventorycopy(invChart);
        for(int i = 0; i < chart+1; i++){
            /*if(i + base < level + 1) {
                openinv.setItem(slots[i], Item.createitem(material, i + 1 + itemCountBase, "level " + (i + 1 + base), ""));
            } else {
                openinv.setItem(slots[i], gray_dye);
            }*/
        }
        openinv.setItem(1,Item.createitem(Material.QUARTZ_STAIRS,1,"",ChatColor.WHITE+""+main_page));

        if (page % 2 != 0) openinv.setItem(48, Item.createitem(Material.ARROW, 1, "2ページ目へ", ""));
        else openinv.setItem(50, Item.createitem(Material.ARROW, 1, "1ページ目へ", ""));

        openinv.setItem(c[page-1],Item.enachantitem(openinv.getItem(c[page-1])));
        Item.setInventory(p,openinv);
    }
}

