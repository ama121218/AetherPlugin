package net.oriserver.aether.aether.chart.listener;

import net.oriserver.aether.aether.chart.events.ChartStageInventoryEvent;
import net.oriserver.aether.aether.chart.inventory.ChartStageInventory;
import net.oriserver.aether.aether.chart.stage.ChartStageInfo;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChartStageInventoryClick implements Listener{
    private final ChartStageInventory chartStageInventory;
    private final ChartStageInfo chartStageInfo;
    public ChartStageInventoryClick(ChartStageInventory chartStageInventory, ChartStageInfo chartStageInfo){
            this.chartStageInventory = chartStageInventory;
            this.chartStageInfo = chartStageInfo;
    }

    @EventHandler
    public void onItemClick(ChartStageInventoryEvent e) {
        if(!e.getPlayer().isOp())return;
        if(e.getMaterial() == Material.ARROW){
            chartStageInventory.setinv(e.getPlayer(),Character.getNumericValue(e.getItem_name().charAt(0)));
        }else if(e.getMaterial() == Material.STRUCTURE_VOID){
            return;
        }
        else if (e.getMaterial() == Material.INK_SACK) {
            String stageName = e.getItem_name();
            e.getPlayer().performCommand("chart create " + stageName);
        }else{
            String stageName = e.getItem_name();
            String[] strings = stageName.split(" ");
            String[] chart = strings[0].split("_");
            int chartNumber = (Integer.parseInt(chart[0]) - 1) * 14 + Integer.parseInt(chart[1]);
            Location location = chartStageInfo.getStageTP(chartNumber);
            if(location != null)e.getPlayer().teleport(location);
            e.getPlayer().performCommand("chart rework " + strings[0]);
        }
    }

}
