package net.oriserver.aether.aether.inventory.home.appearance.particle;

import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.particle.Particle;
import net.oriserver.aether.aether.player.PlayerStats;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ParticleInventoryClick {
    final private InventoryManager inventoryManager;
    final private Particle particle;
    public ParticleInventoryClick(InventoryManager inventoryManager){
        this.inventoryManager = inventoryManager;
        this.particle = new Particle();
    }

    public void event(Player p, Material material, int slot){
        if(material == Material.IRON_DOOR && slot == 0) {inventoryManager.getHomeInventory().setinv(p);}
        else if(material == Material.BARRIER && slot == 45) {p.closeInventory();}

        else if(material == Material.TOTEM && slot == 1) {inventoryManager.getHomeInventory().setinv(p);}

        else if(material == Material.BLAZE_POWDER && slot == 10) {}
        else if(material == Material.NETHER_STAR && slot == 4) {}
        else if(material == Material.FEATHER && slot == 5) {}
        else if(material == Material.SHULKER_SHELL && slot == 12) {}

        else if(material == Material.IRON_HELMET && slot == 7) {
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId()));
            inventoryManager.getHeadBlockInventory().setinv(p,playerStats.getHeadblock()[0],1);
        }
        else if(material == Material.PRISMARINE_CRYSTALS && slot == 16) {
            //PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId()));
            inventoryManager.getBadgeInventory().setinv(p);
        }
        else if(material == Material.JUKEBOX && slot == 25) {}
        else if(material == Material.BARRIER && slot == 49) {}
    }
}
