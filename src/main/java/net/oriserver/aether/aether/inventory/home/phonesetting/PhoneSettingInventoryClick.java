package net.oriserver.aether.aether.inventory.home.phonesetting;

import net.oriserver.aether.aether.inventory.InventoryManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class PhoneSettingInventoryClick {
    final private InventoryManager inventoryManager;
    public PhoneSettingInventoryClick(InventoryManager inventoryManager){
        this.inventoryManager = inventoryManager;
    }

    public void event(Player p, Material material, int slot){

    }
}
