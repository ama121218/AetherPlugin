package net.oriserver.aether.aether.chart.inventory;

import net.oriserver.aether.aether.chart.stage.ChartStageInfo;
import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

public class ChartStageInventory {
    int[] a = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32};
    final private ChartStageInfo chartStageInfo;
    Inventory invChart = Bukkit.createInventory(null, 54, "Chart Stage Create");
    ItemStack gray_dye = new ItemStack(Material.INK_SACK,1, (short) 8);

    public ChartStageInventory(ChartStageInfo chartStageInfo){this.chartStageInfo = chartStageInfo;}

    public void setinv(Player p, int page){
        HashMap<Integer,String> stageNameMap = chartStageInfo.getStageNameMap();
        Inventory openinv = Item.inventorycopy(invChart);
        if(page==1){
            for(int i = 1; i <= 14; i++) {
                String stageName = stageNameMap.getOrDefault(i,"");
                String name = "1_" + i;
                if(stageName.equals("")) {
                    openinv.setItem(a[i-1], Item.changename(gray_dye.clone(), name));
                }
                else{
                    openinv.setItem(a[i-1],Item.createitem(Material.GRASS,1,name +" "+ stageName));
                }
            }
            for(int i = 15; i <= 28; i++) {
                String stageName = stageNameMap.getOrDefault(i,"");
                String name = "2_" + (i-14);
                if(stageName.equals("")) {
                    openinv.setItem(a[i-1], Item.changename(gray_dye.clone(), name));
                }else{
                    openinv.setItem(a[i-1],Item.createitem(Material.SAND,1,name +" "+ stageName));
                }
            }
            openinv.setItem(45, Item.createitem(Material.STRUCTURE_VOID, 1, "", ""));
            openinv.setItem(53, Item.createitem(Material.ARROW, 1, "2ページ目へ", ""));
        }else if(page==2){
            for(int i = 29; i <= 42; i++) {
                String stageName = stageNameMap.getOrDefault(i,"");
                String name = "3_" + (i-28);
                if(stageName.equals("")) {
                    openinv.setItem(a[i-28-1], Item.changename(gray_dye.clone(), name));
                }
                else{
                    openinv.setItem(a[i-28-1],Item.createitem(Material.SNOW_BLOCK,1,name +" "+ stageName));
                }
            }
            for(int i = 43; i <= 56; i++) {
                String stageName = stageNameMap.getOrDefault(i,"");
                String name = "4_" + (i-42);
                if(stageName.equals("")) {
                    openinv.setItem(a[i-28-1], Item.changename(gray_dye.clone(), name));
                }else{
                    openinv.setItem(a[i-28-1],Item.createitem(Material.QUARTZ_BLOCK,1,name +" "+ stageName));
                }
            }
            openinv.setItem(45, Item.createitem(Material.ARROW, 1,  "1ページ目へ", ""));
            openinv.setItem(53, Item.createitem(Material.STRUCTURE_VOID, 1, "", ""));
        }
        p.openInventory(openinv);
    }
}
