package net.oriserver.aether.aether.chart.listener;

import net.oriserver.aether.aether.AthleticLocation;
import net.oriserver.aether.aether.chart.events.ChartInventoryClickEvent;
import net.oriserver.aether.aether.chart.inventory.ChartInventory;
import net.oriserver.aether.aether.chart.stage.ChartStageInfo;
import net.oriserver.aether.aether.player.PlayerManager;
import net.oriserver.aether.aether.statics.CommonMethods;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ChartInventoryClickListener implements Listener {//チャートアスレチックのインベントリーをクリックしたときのEventを取得

    private final ChartInventory chartInventory; 
    private final PlayerManager pm;
    private final ChartStageInfo chartStageInfo;
    public ChartInventoryClickListener(PlayerManager pm,ChartInventory chartInventory,ChartStageInfo chartStageInfo){
        this.chartInventory = chartInventory;
        this.pm = pm;
        this.chartStageInfo = chartStageInfo;
        map_number.put(3, 1);
        map_number.put(4, 2);
        map_number.put(5, 3);
        map_number.put(12, 4);
        map_number.put(13, 5);
        map_number.put(14, 6);
        map_number.put(21, 7);
        map_number.put(22, 8);
        map_number.put(23, 9);
        map_number.put(30, 10);
        map_number.put(31, 11);
        map_number.put(32, 12);
        map_number.put(39, 13);
        map_number.put(40, 14);
    }
    HashMap<Integer,Integer> map_number = new HashMap<>();

    @EventHandler
    public void onClickChartInventory(ChartInventoryClickEvent e){
        Material material = e.getMaterial();
        Player p = e.getPlayer();
        int slot = e.getSlot();
        try {
            if (material != null && map_number.get(slot) != null) {
                int number = map_number.get(slot);
                ItemStack pageItem = e.getInventory().getItem(1);
                if (!pageItem.hasItemMeta() || !pageItem.getItemMeta().hasLore()) return;
                int main_page = Integer.parseInt(ChatColor.stripColor(pageItem.getItemMeta().getLore().get(0))) - 1;
                CommonMethods.setTeleport(p, chartStageInfo.getStageTP(number + (main_page * 14)), "Chart" + (main_page + 1) + "_" + number, pm.getPlayer(String.valueOf(p.getUniqueId())));
            } else if (material == Material.GRASS && slot == 7) {
                chartInventory.setinv(p, 1, pm.getPlayer(String.valueOf(p.getUniqueId())).getChart());
                pm.getPlayer(String.valueOf(p.getUniqueId())).setChart_page(1);
            } else if (material == Material.SAND && slot == 16) {
                chartInventory.setinv(p, 2, pm.getPlayer(String.valueOf(p.getUniqueId())).getChart());
                pm.getPlayer(String.valueOf(p.getUniqueId())).setChart_page(2);
            } else if (material == Material.SNOW_BLOCK && slot == 25) {
                chartInventory.setinv(p, 3, pm.getPlayer(String.valueOf(p.getUniqueId())).getChart());
                pm.getPlayer(String.valueOf(p.getUniqueId())).setChart_page(3);
            } else if (material == Material.QUARTZ_BLOCK && slot == 34) {
                chartInventory.setinv(p, 4, pm.getPlayer(String.valueOf(p.getUniqueId())).getChart());
                pm.getPlayer(String.valueOf(p.getUniqueId())).setChart_page(4);
            } else if (material == Material.CHORUS_FRUIT_POPPED && slot == 18) {
                CommonMethods.setTeleport(p, AthleticLocation.getLocation(AthleticLocation.CHART), "CHART_Lobby", pm.getPlayer(String.valueOf(p.getUniqueId())));
            }
        }catch (NullPointerException | IllegalArgumentException error){
            p.sendMessage("エラーが発生しました、運営に問い合わせてください");
        }
    }
    public String getChartNumber(int number){
        return "Chart"+((number - 1) / 14 + 1)+"_"+((number - 1) % 14 + 1);
    }
}
