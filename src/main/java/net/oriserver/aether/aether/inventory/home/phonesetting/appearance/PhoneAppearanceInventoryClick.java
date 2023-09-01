package net.oriserver.aether.aether.inventory.home.phonesetting.appearance;

import net.oriserver.aether.aether.statics.Item;
import net.oriserver.aether.aether.inventory.InventoryManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class PhoneAppearanceInventoryClick {
    final private InventoryManager inventoryManager;
    public PhoneAppearanceInventoryClick(InventoryManager inventoryManager){this.inventoryManager = inventoryManager;}
    public void event(Player p, Material material, int slot){
        if(material == Material.IRON_DOOR && slot == 0) {inventoryManager.getHomeInventory().setinv(p);}
        else if(material == Material.BARRIER && slot == 45) {p.closeInventory();}

        else if(material == Material.PAINTING && slot == 1){inventoryManager.getPhoneSettingInventory().setinv(p);}

        else if(material == Material.IRON_DOOR && slot == 12){
            inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPhone(0);
            Item.getItemPhone(p,0);
        }
        else if(material == Material.WOOD_DOOR && slot == 13){
            inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPhone(1);
            Item.getItemPhone(p,1);
        }
        else if(material == Material.BIRCH_DOOR_ITEM && slot == 14){
            inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPhone(2);
            Item.getItemPhone(p,2);
        }
        else if(material == Material.ACACIA_DOOR_ITEM && slot == 21){
            inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPhone(3);
            Item.getItemPhone(p,3);
        }
        else if(material == Material.DARK_OAK_DOOR_ITEM && slot == 22){
            inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPhone(4);
            Item.getItemPhone(p,4);
        }
        else if(material == Material.JUNGLE_DOOR_ITEM && slot == 23){
            inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPhone(5);
            Item.getItemPhone(p,5);
        }
        else if(material == Material.PAINTING && slot == 30){
            inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPhone(6);
            Item.getItemPhone(p,6);
        }


        else if(material == Material.THIN_GLASS && slot == 7){inventoryManager.getPhonePartitionInventory().setinv(p,1);}
    }
}
