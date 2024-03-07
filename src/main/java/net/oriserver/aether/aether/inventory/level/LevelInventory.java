package net.oriserver.aether.aether.inventory.level;

import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class LevelInventory {//レベルアスレチックのインベントリークラス
    Inventory invLevel = Bukkit.createInventory(null, 54, "Level Athletic");
    int[] a = {3,4,5,12,13,14,21,22,23,30,31,32,39,40,41};
    int[] b = {3,4,5,12,13};
    int[] c = {7,16,25,34,43,52};
    Material[] materials = {
            Material.GRASS,
            Material.LOG,
            Material.SMOOTH_BRICK,
            Material.LAPIS_BLOCK,
            Material.MAGMA,
            Material.QUARTZ_BLOCK
    };
    ItemStack gray_dye = new ItemStack(Material.INK_SACK,1, (short) 8);
    public LevelInventory(){
        invLevel.setItem(0, Item.createitem(Material.IRON_INGOT, 1, ChatColor.WHITE+"Teleport_Option", ""));
        invLevel.setItem(45,Item.createitem(Material.BARRIER, 1, "閉じる", ""));

        invLevel.setItem(18,Item.createitem(Material.CHORUS_FRUIT_POPPED,1,ChatColor.GREEN+"Teleport_Spawn",""));

        invLevel.setItem(1,Item.createitem(Material.QUARTZ_STAIRS, 1, "Level_Athletic", ChatColor.WHITE+"1"));

        invLevel.setItem(7, Item.createitem(Material.GRASS, 1, "草原ステージ", ""));
        invLevel.setItem(16, Item.createitem(Material.LOG, 1, "原木ステージ", ""));
        invLevel.setItem(25, Item.createitem(Material.SMOOTH_BRICK, 1, "遺跡ステージ", ""));
        invLevel.setItem(34, Item.createitem(Material.LAPIS_BLOCK, 1, "海洋ステージ", ""));
        invLevel.setItem(43, Item.createitem(Material.MAGMA, 1, "溶岩ステージ", ""));
        invLevel.setItem(52, Item.createitem(Material.QUARTZ_BLOCK, 1, "天界ステージ", ""));

        invLevel.setItem(48,Item.createitem(Material.STRUCTURE_VOID,1,"",""));
        invLevel.setItem(50,Item.createitem(Material.STRUCTURE_VOID,1,"",""));
    }
    public void setinv(Player p,int page,int level){
        int main_page = (page + 1) / 2;
        int sub_page = page % 2 == 0 ? 2 : 1;
        Inventory openinv = Item.inventorycopy(invLevel);
        int[] slots = sub_page == 1 ? a : b;
        int base = (main_page - 1) * 20 + (sub_page - 1) * 15;
        Material material = materials[main_page - 1];
        int itemCountBase = sub_page == 1 ? 0 : 15;
        for(int i = 0; i < slots.length; i++){
            if(i + base < level + 1) {
                openinv.setItem(slots[i], Item.createitem(material, i + 1 + itemCountBase, "level " + (i + 1 + base), ""));
            } else {
                openinv.setItem(slots[i], gray_dye);
            }
        }
        openinv.setItem(1,Item.createitem(Material.QUARTZ_STAIRS,1,"Level_Athletic",ChatColor.WHITE+""+main_page));

        if (page % 2 != 0) openinv.setItem(50, Item.createitem(Material.ARROW, 1, "2ページ目へ", ""));
        else openinv.setItem(48, Item.createitem(Material.ARROW, 1, "1ページ目へ", ""));

        openinv.setItem(c[main_page-1],Item.enachantitem(openinv.getItem(c[main_page-1])));
        Item.setInventory(p,openinv);
    }
}
