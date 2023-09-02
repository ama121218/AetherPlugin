package net.oriserver.aether.aether.inventory.home.phonesetting.partition;

import net.oriserver.aether.aether.statics.Item;
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


        else if(material == Material.IRON_FENCE && slot == 3){
            inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(1);
            Item.player_partition.put(String.valueOf(p.getUniqueId()),0);
            inventoryManager.getPhonePartitionInventory().setinv(p,1);
        }
        else if(material == Material.THIN_GLASS && slot == 4){
            inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(0);
            Item.player_partition.put(String.valueOf(p.getUniqueId()),1);
            inventoryManager.getPhonePartitionInventory().setinv(p,1);
        }
        else if(material == Material.RAILS && slot == 5){
            inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(2);
            Item.player_partition.put(String.valueOf(p.getUniqueId()),2);
            inventoryManager.getPhonePartitionInventory().setinv(p,1);
        }
        else if(material == Material.POWERED_RAIL && slot == 12){
            inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(3);
            Item.player_partition.put(String.valueOf(p.getUniqueId()),3);
            inventoryManager.getPhonePartitionInventory().setinv(p,1);
        }
        else if(material == Material.ACTIVATOR_RAIL && slot == 13){
            inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(4);
            Item.player_partition.put(String.valueOf(p.getUniqueId()),4);
            inventoryManager.getPhonePartitionInventory().setinv(p,1);
        }
        else if(material == Material.DETECTOR_RAIL && slot == 14){
            inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(5);
            Item.player_partition.put(String.valueOf(p.getUniqueId()),5);
            inventoryManager.getPhonePartitionInventory().setinv(p,1);
        }
        else if(material == Material.LADDER && slot == 21){
            inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(6);
            Item.player_partition.put(String.valueOf(p.getUniqueId()),6);
            inventoryManager.getPhonePartitionInventory().setinv(p,1);
        }
        else if(material == Material.VINE && slot == 22){
            inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(7);
            Item.player_partition.put(String.valueOf(p.getUniqueId()),7);
            inventoryManager.getPhonePartitionInventory().setinv(p,1);
        }

        else if(material == Material.ARROW && slot == 50){inventoryManager.getPhonePartitionInventory().setinv(p,2);}
    }
    public void event2(Player p, Material material,int slot){
        if(material == Material.IRON_DOOR && slot == 0) {inventoryManager.getHomeInventory().setinv(p);}
        else if(material == Material.BARRIER && slot == 45) {p.closeInventory();}

        else if(material == Material.PAINTING && slot == 1){inventoryManager.getPhoneSettingInventory().setinv(p);}

        else if(material == Material.STAINED_GLASS_PANE){
            if(slot == 3){
                inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(8);
                Item.player_partition.put(String.valueOf(p.getUniqueId()), 8);
                inventoryManager.getPhonePartitionInventory().setinv(p, 2);
            }else if(slot == 4){
                inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(9);
                Item.player_partition.put(String.valueOf(p.getUniqueId()),9);
                inventoryManager.getPhonePartitionInventory().setinv(p,2);
            }else if(slot == 5){
                inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(10);
                Item.player_partition.put(String.valueOf(p.getUniqueId()),10);
                inventoryManager.getPhonePartitionInventory().setinv(p,2);
            }else if(slot == 12){
                inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(11);
                Item.player_partition.put(String.valueOf(p.getUniqueId()),11);
                inventoryManager.getPhonePartitionInventory().setinv(p,2);
            }else if(slot == 13){
                inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(12);
                Item.player_partition.put(String.valueOf(p.getUniqueId()),12);
                inventoryManager.getPhonePartitionInventory().setinv(p,2);
            }else if(slot == 14){
                inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(13);
                Item.player_partition.put(String.valueOf(p.getUniqueId()),13);
                inventoryManager.getPhonePartitionInventory().setinv(p,2);
            }else if(slot == 21){
                inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(14);
                Item.player_partition.put(String.valueOf(p.getUniqueId()),14);
                inventoryManager.getPhonePartitionInventory().setinv(p,2);
            }else if(slot == 22){
                inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(15);
                Item.player_partition.put(String.valueOf(p.getUniqueId()),15);
                inventoryManager.getPhonePartitionInventory().setinv(p,2);
            }else if(slot == 23){
                inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(16);
                Item.player_partition.put(String.valueOf(p.getUniqueId()),16);
                inventoryManager.getPhonePartitionInventory().setinv(p,2);
            }else if(slot == 30){
                inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(17);
                Item.player_partition.put(String.valueOf(p.getUniqueId()),17);
                inventoryManager.getPhonePartitionInventory().setinv(p,2);
            }else if(slot == 31){
                inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(18);
                Item.player_partition.put(String.valueOf(p.getUniqueId()),18);
                inventoryManager.getPhonePartitionInventory().setinv(p,2);
            }else if(slot == 32){
                inventoryManager.getPlayerManager().getPlayer(String.valueOf(p.getUniqueId())).setPartition(19);
                Item.player_partition.put(String.valueOf(p.getUniqueId()),19);
                inventoryManager.getPhonePartitionInventory().setinv(p,2);
            }
        }

        else if(material == Material.ARROW && slot == 48){inventoryManager.getPhonePartitionInventory().setinv(p,1);}
    }
}
