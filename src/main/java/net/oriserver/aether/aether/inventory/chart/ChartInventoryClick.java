package net.oriserver.aether.aether.inventory.chart;

import net.oriserver.aether.aether.inventory.InventoryManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ChartInventoryClick {
    final private InventoryManager inventoryManager;
    public ChartInventoryClick(InventoryManager inventoryManager){this.inventoryManager = inventoryManager;}
    public void event(Player p, Material material, int slot){

    }
}