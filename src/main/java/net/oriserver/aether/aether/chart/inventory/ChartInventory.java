package net.oriserver.aether.aether.chart.inventory;

import net.oriserver.aether.aether.chart.stage.ChartStageInfo;
import net.oriserver.aether.aether.statics.Item;
import net.oriserver.aether.aether.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

public class ChartInventory {
    Inventory invChart = Bukkit.createInventory(null, 54, "Chart Athletic");
    private final PlayerManager playerManager;
    private final ChartStageInfo chartStageInfo;
    int[] a = {3,4,5,12,13,14,21,22,23,30,31,32,39,40};
    int[] c = {7,16,25,34};
    String[] starStrings = {
        ChatColor.YELLOW + " ✦",
        ChatColor.YELLOW + " ✦✦",
        ChatColor.YELLOW + " ✦✦✦"
    };
    String eq = ChatColor.GOLD+"======================";
    String teleport = ChatColor.WHITE+""+ChatColor.BOLD+"➩Click to teleport";
    ItemStack gray_dye = new ItemStack(Material.INK_SACK,1, (short) 8);

    public ChartInventory(PlayerManager playerManager, ChartStageInfo chartStageInfo){
        this.playerManager = playerManager;
        this.chartStageInfo = chartStageInfo;
        invChart.setItem(0, Item.createitem(Material.IRON_INGOT, 1, ChatColor.WHITE+"Teleport_option", ""));
        invChart.setItem(45,Item.createitem(Material.BARRIER, 1, "閉じる", ""));

        invChart.setItem(1,Item.createitem(Material.APPLE, 1, "Chart_Athletic", ChatColor.WHITE+"1"));

        invChart.setItem(7, Item.createitem(Material.GRASS, 1, "1ステージ", ""));
        invChart.setItem(16, Item.createitem(Material.SAND, 1, "2ステージ", ""));
        invChart.setItem(25, Item.createitem(Material.SNOW_BLOCK, 1, "3ステージ", ""));
        invChart.setItem(34, Item.createitem(Material.QUARTZ_BLOCK, 1, "4ステージ", ""));

        invChart.setItem(18,Item.createitem(Material.CHORUS_FRUIT_POPPED,1,ChatColor.GREEN+"Teleport_Spawn",""));
    }
    public void setinv(Player p,int page,int chart){
        ArrayList<Object[]> objects = playerManager.getSqLiteManager().getChartDBManagerP().getDatas(String.valueOf(p.getUniqueId()),1 + (page - 1) *14,chart);
        Inventory openinv = Item.inventorycopy(invChart);
        for(int i = 0; i < chart+1-((page-1)*14) && i < 14; i++){
            try{
            String map_name = ChatColor.WHITE+""+ChatColor.BOLD+"chart "+page+"_"+(i+1)+" "+ chartStageInfo.getStageName(i+1+((page-1)*14));
            long time = 0L;
            int count = 0;
            if(i < objects.size() && objects.get(i) != null){
                time = (long) objects.get(i)[0];
                count = (int) objects.get(i)[1];
            }
            String you_time = ChatColor.AQUA+"Your Time"+ChatColor.WHITE+": "+chartStageInfo.getStringTime(time);
            int star = chartStageInfo.getStarRating(i+1+((page-1)*14),time);
            String string_star = "       " + chartStageInfo.getStarString(star);

            String[] standardtimes = chartStageInfo.getStringTimes(chartStageInfo.getStandardTime(i+1+((page-1)*14)));
            if(star==0){openinv.setItem(a[i],Item.changename(gray_dye,eq,map_name,"",you_time,string_star,"",standardtimes[0]+starStrings[0],standardtimes[1]+starStrings[1],standardtimes[2]+starStrings[2],"",teleport,eq));}
            else if(star==1){openinv.setItem(a[i],Item.createitem(Material.APPLE,1,eq,map_name,"",you_time,string_star,"",standardtimes[0]+starStrings[0],standardtimes[1]+starStrings[1],standardtimes[2]+starStrings[2],"",teleport,eq));}
            else if(star==2){openinv.setItem(a[i],Item.createitem(Material.GOLDEN_APPLE,1,eq,map_name,"",you_time,string_star,"",standardtimes[0]+starStrings[0],standardtimes[1]+starStrings[1],standardtimes[2]+starStrings[2],"",teleport,eq));}
            else{openinv.setItem(a[i],Item.createitem2(Material.GOLDEN_APPLE,1,eq,map_name,"",you_time,string_star,"",standardtimes[0]+starStrings[0],standardtimes[1]+starStrings[1],standardtimes[2]+starStrings[2],"",teleport,eq));}
            }
            catch (NullPointerException e){
                openinv.setItem(a[i],Item.changename(gray_dye,eq,ChatColor.WHITE+""+ChatColor.BOLD+"chart "+page+"_"+(i+1),eq));
            }
        }
        openinv.setItem(1,Item.createitem(Material.APPLE,1,"",ChatColor.WHITE+""+page));

        openinv.setItem(c[page-1],Item.enachantitem(openinv.getItem(c[page-1])));
        Item.setInventory(p,openinv);
    }
}

