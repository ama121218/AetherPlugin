package net.oriserver.aether.aether.inventory.home.athletic;

import net.oriserver.aether.aether.AthleticLocation;
import net.oriserver.aether.aether.inventory.InventoryManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class AthleticInventoryClick {

    final private InventoryManager inventoryManager;
    public AthleticInventoryClick(InventoryManager inventoryManager){
        this.inventoryManager = inventoryManager;
    }

    public void event(Player p, Material material, int slot){
        if(material == Material.IRON_DOOR && slot == 0) {inventoryManager.getHomeInventory().setinv(p);}
        else if(material == Material.BARRIER && slot == 45) {p.closeInventory();}

        else if(material == Material.QUARTZ_STAIRS && slot == 21) {p.teleport(AthleticLocation.getLocation(AthleticLocation.SHRINE));}
        else if(material ==Material.SKULL_ITEM && slot == 22){p.teleport(AthleticLocation.getLocation(AthleticLocation.GLOBAL));}
        else if(material == Material.APPLE && slot == 23) {p.teleport(AthleticLocation.getLocation(AthleticLocation.CHART));}
        else if(material == Material.STAINED_GLASS_PANE && slot == 30) {p.teleport(AthleticLocation.getLocation(AthleticLocation.ORDEAL));}
        else if(material == Material.STONE && slot == 31) {p.teleport(AthleticLocation.getLocation(AthleticLocation.ASURE_LOBBY));}
    }

}
