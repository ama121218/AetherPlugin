package net.oriserver.aether.aether.inventory.home.appearance.badge;

import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.player.PlayerStats;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class BadgeInventoryClick {

    final private InventoryManager inventoryManager;
    public BadgeInventoryClick(InventoryManager inventoryManager){
        this.inventoryManager = inventoryManager;
    }

    public void event(Player p, Material material, int slot){
        if(material == Material.IRON_DOOR && slot == 0) {inventoryManager.getHomeInventory().setinv(p);}
        else if(material == Material.BARRIER && slot == 45) {p.closeInventory();}

        else if(material == Material.END_CRYSTAL && slot == 21) {inventoryManager.getParticleInventory().setinv(p);}
        else if(material == Material.IRON_HELMET && slot == 7) {
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId()));
            inventoryManager.getHeadBlockInventory().setinv(p,playerStats.getHeadblock()[0],1);
        }
        else if(material == Material.JUKEBOX && slot == 30) {}
    }
}
