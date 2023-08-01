package net.oriserver.aether.aether.inventory.home.minigame;

import net.oriserver.aether.aether.AthleticLocation;
import net.oriserver.aether.aether.inventory.InventoryManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class MiniGameInventoryClick {

    final private InventoryManager inventoryManager;
    public MiniGameInventoryClick(InventoryManager inventoryManager){
        this.inventoryManager = inventoryManager;
    }

    public void event(Player p, Material material, int slot){
        if(material == Material.IRON_DOOR && slot == 0) {inventoryManager.getHomeInventory().setinv(p);}
        else if(material == Material.BARRIER && slot == 45) {p.closeInventory();}

        else if(material == Material.TNT && slot == 21) {p.teleport(AthleticLocation.getLocation(AthleticLocation.TNTRUN));}
    }
}