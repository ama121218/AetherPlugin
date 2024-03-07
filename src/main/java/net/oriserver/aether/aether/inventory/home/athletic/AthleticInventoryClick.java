package net.oriserver.aether.aether.inventory.home.athletic;

import net.oriserver.aether.aether.AthleticLocation;
import net.oriserver.aether.aether.statics.CommonMethods;
import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.player.PlayerManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class AthleticInventoryClick {//アスレチックのインベントリークラスをクリックした時に操作するクラス

    final private InventoryManager inventoryManager;
    final private PlayerManager pm;
    public AthleticInventoryClick(InventoryManager inventoryManager, PlayerManager pm){
        this.inventoryManager = inventoryManager;
        this.pm = pm;
    }

    public void event(Player p, Material material, int slot){
        if(material == Material.IRON_DOOR && slot == 0) {inventoryManager.getHomeInventory().setinv(p);}
        else if(material == Material.BARRIER && slot == 45) {p.closeInventory();}

        else if(material == Material.QUARTZ_STAIRS && slot == 21){
            CommonMethods.setTeleport(p,AthleticLocation.getLocation(AthleticLocation.SHRINE),"Level_Lobby",pm.getPlayer(String.valueOf(p.getUniqueId())));
        }
        else if(material ==Material.SKULL_ITEM && slot == 22){
            CommonMethods.setTeleport(p,AthleticLocation.getLocation(AthleticLocation.GLOBAL),"Global_Lobby",pm.getPlayer(String.valueOf(p.getUniqueId())));
        }
        else if(material == Material.APPLE && slot == 23) {
            CommonMethods.setTeleport(p,AthleticLocation.getLocation(AthleticLocation.CHART),"Chart_Lobby",pm.getPlayer(String.valueOf(p.getUniqueId())));
        }
        else if(material == Material.STAINED_GLASS_PANE && slot == 30) {
            CommonMethods.setTeleport(p,AthleticLocation.getLocation(AthleticLocation.ORDEAL),"Ordeal_Lobby",pm.getPlayer(String.valueOf(p.getUniqueId())));
        }
        else if(material == Material.STONE && slot == 31) {
            CommonMethods.setTeleport(p, AthleticLocation.getLocation(AthleticLocation.ASURE_LOBBY), "Athletic_Lobby", pm.getPlayer(String.valueOf(p.getUniqueId())));
        }
    }

}
