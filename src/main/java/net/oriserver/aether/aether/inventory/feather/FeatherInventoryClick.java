package net.oriserver.aether.aether.inventory.feather;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class FeatherInventoryClick {
    public void event(Player p,Material material,int slot){
        if(material == Material.SOUL_SAND && slot == 0){p.setWalkSpeed(0.1f);p.setFlySpeed(0.05f);}
        else if(material == Material.LEATHER_BOOTS && slot == 1){p.setWalkSpeed(0.2f);p.setFlySpeed(0.1f);}
        else if(material == Material.FEATHER && slot == 2){p.setWalkSpeed(0.3f);p.setFlySpeed(0.2f);}
        else if(material == Material.FEATHER && slot == 3){p.setWalkSpeed(0.4f);p.setFlySpeed(0.35f);}
        else if(material == Material.FEATHER && slot == 4){p.setWalkSpeed(0.5f);p.setFlySpeed(0.5f);}
        else if(material == Material.FEATHER && slot == 5){p.setWalkSpeed(0.7f);p.setFlySpeed(0.7f);}
        else if(material == Material.FEATHER && slot == 6){p.setWalkSpeed(0.8f);p.setFlySpeed(0.8f);}
        else if(material == Material.FEATHER && slot == 7){p.setWalkSpeed(0.9f);p.setFlySpeed(0.9f);}
        else if(material == Material.FEATHER && slot == 8){p.setWalkSpeed(1.0f);p.setFlySpeed(1.0f);}
        p.closeInventory();
    }
}
