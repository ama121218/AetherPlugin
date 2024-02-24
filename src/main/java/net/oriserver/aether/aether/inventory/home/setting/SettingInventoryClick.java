package net.oriserver.aether.aether.inventory.home.setting;

import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.particle.ParticleManager;
import net.oriserver.aether.aether.player.PlayerStats;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SettingInventoryClick {

    private final InventoryManager inventoryManager;
    private final ParticleManager particleManager;
    public SettingInventoryClick(InventoryManager inventoryManager, ParticleManager particleManager){
        this.inventoryManager = inventoryManager;
        this.particleManager = particleManager;
    }

    public void event(Player p, Material material,int slot){
        if(material == Material.IRON_DOOR && slot == 0) {inventoryManager.getHomeInventory().setinv(p);}
        else if(material == Material.BARRIER && slot == 45) {p.closeInventory();}

        else if(material == Material.LEVER || material == Material.REDSTONE_TORCH_ON){
            boolean value = material == Material.LEVER;
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId()));
            if(slot == 12){
                playerStats.setParticleonoff(value);
                if(value)particleManager.onBlockPlayerParticle(p);
                else particleManager.offBlockPlayerParticle(p);
            }
            else if(slot == 13){
                playerStats.setMailonoff(value);
            }
            else if(slot == 14){
                playerStats.setFriendonoff(value);
            }
            else if(slot == 30){
                playerStats.setChatroomonoff(value);
            }
            else if(slot == 31){
                playerStats.setPlayersidebaronoff(value);
                if(value)playerStats.getPlayerSidebar().cancelSidebar();
                else playerStats.getPlayerSidebar().setSidebar(p);
            }else if(slot == 32){
                playerStats.setChartagainonoff(value);
            }
            inventoryManager.getSettingInventory().setinv(p,playerStats.getSetting());
        }
    }

}
