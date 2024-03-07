package net.oriserver.aether.aether.inventory.home.phonesetting;

import net.oriserver.aether.aether.inventory.InventoryManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class PhoneSettingInventoryClick {//スマホ装飾設定のインベントリーをクリックした時に操作するクラス
    final private InventoryManager inventoryManager;
    public PhoneSettingInventoryClick(InventoryManager inventoryManager){
        this.inventoryManager = inventoryManager;
    }

    public void event(Player p, Material material, int slot){
        if(material == Material.IRON_DOOR && slot == 0) {inventoryManager.getHomeInventory().setinv(p);}
        else if(material == Material.BARRIER && slot == 45) {p.closeInventory();}

        else if(material==Material.WOOD_DOOR && slot==21){inventoryManager.getPhoneAppearanceInventory().setinv(p);}
        else if(material==Material.THIN_GLASS && slot==22){inventoryManager.getPhonePartitionInventory().setinv(p,1);}
        else if(material==Material.IRON_BARDING && slot==23){}
    }
}
