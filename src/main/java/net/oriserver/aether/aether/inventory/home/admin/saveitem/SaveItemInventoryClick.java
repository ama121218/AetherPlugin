package net.oriserver.aether.aether.inventory.home.admin.saveitem;

import net.oriserver.aether.aether.inventory.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

public class SaveItemInventoryClick {
    final private InventoryManager inventoryManager;
    public SaveItemInventoryClick(InventoryManager inventoryManager){this.inventoryManager = inventoryManager;}
    public void event(Player p, Material material, int slot, InventoryClickEvent e){
        if (material == Material.IRON_DOOR && slot == 0) {inventoryManager.getHomeInventory().setinv(p);}
        else if (material == Material.BARRIER && slot == 45) {p.closeInventory();}

        else if(material == Material.CHORUS_FRUIT_POPPED && slot == 18){p.teleport(p.getLocation().getWorld().getSpawnLocation());}

        else if(slot == 3||slot == 4||slot == 5||slot == 12||slot == 13||slot == 14||slot == 21||slot == 22||slot == 23||slot == 30||slot == 31||slot == 32||slot == 39||slot == 40||slot == 41){
            List<String> list = e.getCurrentItem().getItemMeta().getLore();
            Location location = new Location(Bukkit.getWorld(list.get(0)),Float.parseFloat(list.get(1)),Float.parseFloat(list.get(2)),Float.parseFloat(list.get(3)),Float.parseFloat(list.get(4)),Float.parseFloat(list.get(5)));
            p.teleport(location);
        }

        else if(material == Material.ARROW && slot == 48){inventoryManager.getSaveItemInventory().setinv(
                p,
                Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0))
                );
        }
        else if(material == Material.ARROW && slot == 50){inventoryManager.getSaveItemInventory().setinv(
                p,
                Character.getNumericValue(e.getCurrentItem().getItemMeta().getDisplayName().charAt(0))
                );
        }

        else if(material == Material.BARRIER && slot == 7){}
        else if(material == Material.MINECART && slot == 16){}




    }
}
