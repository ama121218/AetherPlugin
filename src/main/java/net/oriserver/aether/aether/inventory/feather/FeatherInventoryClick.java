package net.oriserver.aether.aether.inventory.feather;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FeatherInventoryClick {
    public void event(Player p,Material material,int slot){
        if(material == Material.SOUL_SAND && slot == 0){p.setWalkSpeed(0.1f);p.setFlySpeed(0.05f);p.sendMessage("your speed level : -1");
            for(PotionEffect effect : p.getActivePotionEffects()) {p.removePotionEffect(effect.getType());}
        }
        else if(material == Material.LEATHER_BOOTS && slot == 1){p.setWalkSpeed(0.2f);p.setFlySpeed(0.1f);p.sendMessage("your speed level : 0");
            for(PotionEffect effect : p.getActivePotionEffects()) {p.removePotionEffect(effect.getType());}
        }
        else if(material == Material.FEATHER && slot == 2){p.setWalkSpeed(0.3f);p.setFlySpeed(0.2f);p.sendMessage("your speed level : 1");
            for(PotionEffect effect : p.getActivePotionEffects()) {p.removePotionEffect(effect.getType());}
        }
        else if(material == Material.FEATHER && slot == 3){p.setWalkSpeed(0.4f);p.setFlySpeed(0.35f);p.sendMessage("your speed level : 2");
            for(PotionEffect effect : p.getActivePotionEffects()) {p.removePotionEffect(effect.getType());}
        }
        else if(material == Material.FEATHER && slot == 4){p.setWalkSpeed(0.5f);p.setFlySpeed(0.5f);p.sendMessage("your speed level : 3");
            for(PotionEffect effect : p.getActivePotionEffects()) {p.removePotionEffect(effect.getType());}
        }
        else if(material == Material.FEATHER && slot == 5){p.setWalkSpeed(0.7f);p.setFlySpeed(0.7f);p.sendMessage("your speed level : 4");
            for(PotionEffect effect : p.getActivePotionEffects()) {p.removePotionEffect(effect.getType());}
        }
        else if(material == Material.FEATHER && slot == 6){p.setWalkSpeed(0.8f);p.setFlySpeed(0.8f);p.sendMessage("your speed level : 5");
            for(PotionEffect effect : p.getActivePotionEffects()) {p.removePotionEffect(effect.getType());}
        }
        else if(material == Material.FEATHER && slot == 7){p.setWalkSpeed(1.0f);p.setFlySpeed(1.0f);p.sendMessage("your speed level : 6");
            for(PotionEffect effect : p.getActivePotionEffects()) {p.removePotionEffect(effect.getType());}
        }
        else if(material == Material.FEATHER && slot == 8){p.setWalkSpeed(1.0f);p.setFlySpeed(1.0f);p.sendMessage("your speed level : 7");
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999, 50));
        }
        if(slot!=1)p.setGameMode(GameMode.CREATIVE);
        p.closeInventory();
    }
}
