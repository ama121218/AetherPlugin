package net.oriserver.aether.aether.inventory.home.appearance.particle;

import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.particle.Particle;
import net.oriserver.aether.aether.particle.ParticleManager;
import net.oriserver.aether.aether.player.PlayerStats;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ParticleInventoryClick {
    final private InventoryManager inventoryManager;
    final private ParticleManager particleManager;
    public ParticleInventoryClick(Plugin plugin, InventoryManager inventoryManager,ParticleManager particleManager){
        this.inventoryManager = inventoryManager;
        this.particleManager = particleManager;
    }

    public void event(Player p, Material material, int slot){
        if(material == Material.IRON_DOOR && slot == 0) {inventoryManager.getHomeInventory().setinv(p);}
        else if(material == Material.BARRIER && slot == 45) {p.closeInventory();}

        else if(material == Material.TOTEM && slot == 1) {inventoryManager.getHomeInventory().setinv(p);}

        else if(material == Material.BLAZE_POWDER && slot == 3) {particleManager.setPlayerParticle(p, org.bukkit.Particle.FLAME, particleManager.getParticle().circleParticle);}
        else if(material == Material.NETHER_STAR && slot == 4) {particleManager.setPlayerParticle(p, org.bukkit.Particle.END_ROD, particleManager.getParticle().ringParticle);}
        else if(material == Material.FEATHER && slot == 5) {particleManager.setPlayerParticle(p, org.bukkit.Particle.FIREWORKS_SPARK, particleManager.getParticle().wingParticle);}
        else if(material == Material.SHULKER_SHELL && slot == 12) {particleManager.setPlayerParticle(p, org.bukkit.Particle.FIREWORKS_SPARK, particleManager.getParticle().shieldParticle);}

        else if(material == Material.IRON_HELMET && slot == 7) {
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId()));
            inventoryManager.getHeadBlockInventory().setinv(p,playerStats.getHeadblock()[0],1);
        }
        else if(material == Material.PRISMARINE_CRYSTALS && slot == 16) {
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(p.getUniqueId().toString());
            inventoryManager.getBadgeInventory().setinv(p,playerStats.getBadges(0),1);
        }
        else if(material == Material.JUKEBOX && slot == 25) {}
        else if(material == Material.BARRIER && slot == 49) {}
    }
}
