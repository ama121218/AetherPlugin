package net.oriserver.aether.aether.inventory.home.appearance;

import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.player.PlayerStats;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class AppearanceInventoryClick {//プレイヤー装飾のインベントリークラスをクリックした時に操作するクラス

    final private InventoryManager inventoryManager;
    public AppearanceInventoryClick(InventoryManager inventoryManager){
        this.inventoryManager = inventoryManager;
    }

    public void event(Player p, Material material, int slot){
        if(material == Material.IRON_DOOR && slot == 0) {inventoryManager.getHomeInventory().setinv(p);}
        else if(material == Material.BARRIER && slot == 45) {p.closeInventory();}

        else if(material == Material.END_CRYSTAL && slot == 21) {inventoryManager.getParticleInventory().setinv(p);}
        else if(material == Material.IRON_HELMET && slot == 22) {
            inventoryManager.getHeadBlockInventory().setinv(p,
                    inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).getHeadblock()[0],
                    1
            );
        }
        else if(material == Material.PRISMARINE_CRYSTALS && slot == 23) {
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(p.getUniqueId().toString());
            inventoryManager.getBadgeInventory().setinv(p, playerStats.getBadges(0), 1);
        }
        else if(material == Material.JUKEBOX && slot == 30) {}
        else if(slot == 31 && material == Material.NAME_TAG){
            PlayerStats playerStats = inventoryManager.getPlayerManager().getPlayer(p.getUniqueId().toString());
            inventoryManager.getTagInventory().setinv(p, playerStats.getTags(0), 1);
        }
    }
}
