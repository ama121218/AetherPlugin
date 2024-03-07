package net.oriserver.aether.aether.chart.listener;

import net.oriserver.aether.aether.chart.events.ChartIronIngotClickEvent;
import net.oriserver.aether.aether.chart.inventory.ChartInventory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChartItemClickListener implements Listener {//チャートアスレチック専用のアイテムをクリックしたときのEventを取得
    ChartInventory chartInventory;
    public ChartItemClickListener(ChartInventory chartInventory){
        this.chartInventory = chartInventory;
    }
    @EventHandler
    public void onItemClick(ChartIronIngotClickEvent e){
        chartInventory.setinv(e.getPlayer(),e.getPage(),e.getChart());
    }
}
