package net.oriserver.aether.aether.inventory.home;

import net.oriserver.aether.aether.AthleticLocation;
import net.oriserver.aether.aether.inventory.InventoryManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class HomeInventoryClick {
    final private InventoryManager inventoryManager;
    public HomeInventoryClick(InventoryManager inventoryManager){
        this.inventoryManager = inventoryManager;
    }

    public void event(Player p, Material material, int slot){
        if (material == Material.IRON_DOOR && slot == 0){inventoryManager.getHomeInventory().setinv(p);}
        else if (material == Material.BARRIER && slot == 45){p.closeInventory();}

        else if (material == Material.GRASS && slot == 18){p.teleport(AthleticLocation.getLocation(AthleticLocation.LOBBY));}

        else if (material == Material.NETHER_STAR && slot == 3){inventoryManager.getAthleticInventory().setinv(p);}
        else if (material == Material.WORKBENCH && slot == 5){inventoryManager.getMiniGameInventory().setinv(p);}
        else if (material == Material.EMERALD && slot == 21){inventoryManager.getShopInventory().setinv(p);}
        else if (material == Material.TOTEM && slot == 22){inventoryManager.getAppearanceInventory().setinv(p);}
        else if (material == Material.SIGN && slot == 23){}
        else if (material == Material.SKULL_ITEM && slot == 39){}
        else if (material == Material.COMPASS && slot == 40){inventoryManager.getSettingInventory().setinv(p,
                inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).getSetting()
                );
        }
        else if (material == Material.BOOK && slot == 41){}
        else if (material == Material.PAINTING && slot == 53){}

    }

}
