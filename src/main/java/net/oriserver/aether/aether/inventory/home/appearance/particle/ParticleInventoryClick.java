package net.oriserver.aether.aether.inventory.home.appearance.particle;

import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.particle.Particle;
import net.oriserver.aether.aether.particle.ParticleManager;
import net.oriserver.aether.aether.player.PlayerStats;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ParticleInventoryClick {//パーティクルインベントリークラスをクリックした時に操作するクラス
    final private InventoryManager inventoryManager;
    final private ParticleManager particleManager;
    public ParticleInventoryClick(Plugin plugin, InventoryManager inventoryManager,ParticleManager particleManager){
        this.inventoryManager = inventoryManager;
        this.particleManager = particleManager;
    }

    public void event(Player p, Material material, int slot){
        if(slot == 0 && material == Material.IRON_DOOR) {inventoryManager.getHomeInventory().setinv(p);}
        else if(slot == 45 && material == Material.BARRIER) {p.closeInventory();}

        else if(slot == 1 && material == Material.TOTEM) {inventoryManager.getHomeInventory().setinv(p);}

        else if(slot == 3 && material == Material.BLAZE_POWDER) {particleManager.setPlayerParticle(p, org.bukkit.Particle.FLAME, particleManager.getParticle().circleParticle);}
        else if(slot == 4 && material == Material.NETHER_STAR) {particleManager.setPlayerParticle(p, org.bukkit.Particle.END_ROD, particleManager.getParticle().ringParticle);}
        else if(slot == 5 && material == Material.FEATHER) {particleManager.setPlayerParticle(p, org.bukkit.Particle.FIREWORKS_SPARK, particleManager.getParticle().wingParticle);}
        else if(slot == 12 && material == Material.SHULKER_SHELL) {particleManager.setPlayerParticle(p, org.bukkit.Particle.FIREWORKS_SPARK, particleManager.getParticle().shieldParticle);}

        else if (slot == 49 && material == Material.BARRIER) {
            particleManager.cancelPlayerParticle(p);
            p.sendMessage("パーティクルをオフにしました。");
        }

        else if(slot == 7 && material == Material.IRON_HELMET) {
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId()));
            inventoryManager.getHeadBlockInventory().setinv(p,playerStats.getHeadblock()[0],1);
        }
        else if(slot == 16 && material == Material.PRISMARINE_CRYSTALS) {
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(p.getUniqueId().toString());
            inventoryManager.getBadgeInventory().setinv(p,playerStats.getBadges(0),1);
        }
        else if(material == Material.JUKEBOX && slot == 25) {}
    }
}
