package net.oriserver.aether.aether.inventory.home.phonesetting.partition;

import net.oriserver.aether.aether.inventory.InventoryManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class PhonePartitionInventoryClick {
    final private InventoryManager inventoryManager;
    public PhonePartitionInventoryClick(InventoryManager inventoryManager){
        this.inventoryManager = inventoryManager;
    }
    public void event1(Player p, Material material,int slot){
        if(material == Material.IRON_DOOR && slot == 0) {inventoryManager.getHomeInventory().setinv(p);}
        else if(material == Material.BARRIER && slot == 45) {p.closeInventory();}

        else if(material == Material.PAINTING && slot == 1){inventoryManager.getPhoneSettingInventory().setinv(p);}

        else if(material == Material.THIN_GLASS && slot == 3){inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(0);}
        else if(material == Material.IRON_FENCE && slot == 4){inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(1);}
        else if(material == Material.RAILS && slot == 5){inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(2);}
        else if(material == Material.POWERED_RAIL && slot == 12){inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(3);}
        else if(material == Material.ACTIVATOR_RAIL && slot == 13){inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(4);}
        else if(material == Material.DETECTOR_RAIL && slot == 14){inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(5);}
        else if(material == Material.LADDER && slot == 21){inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(6);}
        else if(material == Material.VINE && slot == 22){inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(7);}

        else if(material == Material.ARROW && slot == 50){inventoryManager.getPhonePartitionInventory().setinv(p,2);}
    }
    public void event2(Player p, Material material,int slot){
        if(material == Material.IRON_DOOR && slot == 0) {inventoryManager.getHomeInventory().setinv(p);}
        else if(material == Material.BARRIER && slot == 45) {p.closeInventory();}

        else if(material == Material.PAINTING && slot == 1){inventoryManager.getPhoneSettingInventory().setinv(p);}

        else if(material == Material.STAINED_GLASS_PANE && slot == 3){inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(8);}
        else if(material == Material.STAINED_GLASS_PANE && slot == 4){inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(9);}
        else if(material == Material.STAINED_GLASS_PANE && slot == 5){inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(10);}
        else if(material == Material.STAINED_GLASS_PANE && slot == 12){inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(11);}
        else if(material == Material.STAINED_GLASS_PANE && slot == 13){inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(12);}
        else if(material == Material.STAINED_GLASS_PANE && slot == 14){inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(13);}
        else if(material == Material.STAINED_GLASS_PANE && slot == 21){inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(14);}
        else if(material == Material.STAINED_GLASS_PANE && slot == 22){inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(15);}
        else if(material == Material.STAINED_GLASS_PANE && slot == 23){inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(16);}
        else if(material == Material.STAINED_GLASS_PANE && slot == 30){inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(17);}
        else if(material == Material.STAINED_GLASS_PANE && slot == 31){inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(18);}
        else if(material == Material.STAINED_GLASS_PANE && slot == 32){inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(19);}

        else if(material == Material.ARROW && slot == 48){inventoryManager.getPhonePartitionInventory().setinv(p,1);}
    }
}
