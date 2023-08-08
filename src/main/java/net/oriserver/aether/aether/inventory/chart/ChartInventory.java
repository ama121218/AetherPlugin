package net.oriserver.aether.aether.inventory.chart;


import net.oriserver.aether.aether.Item;
import net.oriserver.aether.aether.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChartInventory {
    Inventory invChart = Bukkit.createInventory(null, 54, "Chart_Athletic");
    private final PlayerManager pm;
    int[] a = {3,4,5,12,13,14,21,22,23,30,31,32,39,40};
    int[] c = {7,16,25,34};
    String[] starstrings = {
            ChatColor.YELLOW + "✦",
            ChatColor.YELLOW + "✦✦",
            ChatColor.YELLOW + "✦✦✦"
    };
    Material[] materials = {
            Material.GRASS,
            Material.SAND,
            Material.SNOW_BLOCK,
            Material.QUARTZ_BLOCK
    };
    String eq = ChatColor.GOLD+"======================";
    String teleport = ChatColor.WHITE+""+ChatColor.BOLD+"➩Click to teleport";
    ItemStack gray_dye = new ItemStack(Material.INK_SACK,1, (short) 8);
    
    public ChartInventory(PlayerManager pm){
        this.pm = pm;
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


        for(int i = (page-1)*14; i < chart+1 && i < 14*page; i++){
            String string_map = ChatColor.WHITE+""+ChatColor.BOLD+"chart "+(i+1)+" "+ChartLocation.getName(i);
            long time = 1000;//sql
            String string_time = ChatColor.AQUA+"Your Time :"+ChatColor.WHITE+time;
            int star = ChartTimeStandard.getStarRating(i+1,time);
            String string_star = getstarstrings(star);
            if(star==0){openinv.setItem(a[i],Item.changename(gray_dye,eq,string_map,"",string_time,string_star,"",starstrings[0],starstrings[1],starstrings[2],"",teleport,eq));}
            else if(star==1){openinv.setItem(a[i],Item.createitem(Material.APPLE,1,eq,string_map,"",string_time,string_star,"",starstrings[0],starstrings[1],starstrings[2],"",teleport,eq));}
            else if(star==2){openinv.setItem(a[i],Item.createitem(Material.GOLDEN_APPLE,1,eq,string_map,"",string_time,string_star,"",starstrings[0],starstrings[1],starstrings[2],"",teleport,eq));}
            else{openinv.setItem(a[i],Item.createitem2(Material.GOLDEN_APPLE,1,eq,string_map,"",string_time,string_star,"",starstrings[0],starstrings[1],starstrings[2],"",teleport,eq));}
        }
        openinv.setItem(1,Item.createitem(Material.APPLE,1,"",ChatColor.WHITE+""+page));

        openinv.setItem(c[page-1],Item.enachantitem(openinv.getItem(c[page-1])));
        Item.setInventory(p,openinv);
    }
    public String getstarstrings(int star){
        if(star==0)       return ChatColor.YELLOW + "✧✧✧";
        else if (star==1) return ChatColor.YELLOW + "✦✧✧";
        else if (star==2) return ChatColor.YELLOW + "✦✦✧";
        else              return ChatColor.YELLOW + "✦✦✦";
    }
}

