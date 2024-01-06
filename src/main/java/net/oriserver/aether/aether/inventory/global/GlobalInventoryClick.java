package net.oriserver.aether.aether.inventory.global;

import net.oriserver.aether.aether.AthleticLocation;
import net.oriserver.aether.aether.player.PlayerManager;
import net.oriserver.aether.aether.statics.CommonMethods;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class GlobalInventoryClick {

    final private PlayerManager pm;

    public GlobalInventoryClick(PlayerManager pm){
        this.pm = pm;
    }

    public void event(Player p, Material material, int slot){
        if (material == Material.BARRIER && slot == 45) {p.closeInventory();}
        else if (material == Material.INK_SACK && slot==3){
            CommonMethods.setTeleport(p,GlobalLocation.getGlobalLocation(1),"Global_1",pm.getPlayer(p.getUniqueId().toString()));
        }
        else if (material == Material.INK_SACK && slot==4){
            CommonMethods.setTeleport(p,GlobalLocation.getGlobalLocation(2),"Global_2",pm.getPlayer(p.getUniqueId().toString()));
        }
        else if (material == Material.INK_SACK && slot==5){
            CommonMethods.setTeleport(p,GlobalLocation.getGlobalLocation(3),"Global_3",pm.getPlayer(p.getUniqueId().toString()));
        }
        else if(material == Material.CHORUS_FRUIT_POPPED && slot == 18){
            CommonMethods.setTeleport(p, AthleticLocation.getLocation(AthleticLocation.GLOBAL),"GLOBAL_Lobby",pm.getPlayer(String.valueOf(p.getUniqueId())));
        }
    }
}
