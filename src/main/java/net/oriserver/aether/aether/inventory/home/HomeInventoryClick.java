package net.oriserver.aether.aether.inventory.home;

import net.oriserver.aether.aether.AthleticLocation;
import net.oriserver.aether.aether.statics.CommonMethods;
import net.oriserver.aether.aether.inventory.InventoryManager;
import net.oriserver.aether.aether.player.PlayerManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class HomeInventoryClick {
    final private InventoryManager inventoryManager;
    final private PlayerManager pm;
    public HomeInventoryClick(InventoryManager inventoryManager, PlayerManager pm){
        this.pm = pm;
        this.inventoryManager = inventoryManager;
    }

    public void event(Player p, Material material, int slot){
        if (material == Material.IRON_DOOR && slot == 0){inventoryManager.getHomeInventory().setinv(p);}
        else if (material == Material.BARRIER && slot == 45){p.closeInventory();}

        else if (material == Material.GRASS && slot == 18){CommonMethods.setTeleport(p,AthleticLocation.getLocation(AthleticLocation.LOBBY),"Lobby", pm.getPlayer(String.valueOf(p.getUniqueId())));}

        else if (material == Material.NETHER_STAR && slot == 3){inventoryManager.getAthleticInventory().setinv(p);}
        else if (material == Material.WORKBENCH && slot == 5){inventoryManager.getMiniGameInventory().setinv(p);}
        else if (material == Material.EMERALD && slot == 21){inventoryManager.getShopInventory().setinv(p);}
        else if (material == Material.TOTEM && slot == 22){inventoryManager.getAppearanceInventory().setinv(p);}
        else if (material == Material.SIGN && slot == 23){}
        else if (material == Material.SKULL_ITEM && slot == 39){}
        else if (material == Material.COMPASS && slot == 40){
            inventoryManager.getSettingInventory().setinv(p, inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).getSetting());
        }
        else if (material == Material.BOOK && slot == 41){}
        else if (material == Material.IRON_AXE && slot == 34){inventoryManager.getSaveItemInventory().setinv(p,1);}
        else if (material == Material.ENDER_PEARL && slot == 43){inventoryManager.getSaveTeleportInventory().setinv(p,1);}
        else if (material == Material.ARMOR_STAND && slot == 52){inventoryManager.getGiveItemInventory().setinv(p);}
        else if (material == Material.PAINTING && slot == 53){inventoryManager.getPhoneSettingInventory().setinv(p);}


    }

}
