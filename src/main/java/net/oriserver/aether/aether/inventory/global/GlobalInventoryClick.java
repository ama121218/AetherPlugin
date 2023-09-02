package net.oriserver.aether.aether.inventory.global;

import net.oriserver.aether.aether.inventory.chart.ChartLocation;
import net.oriserver.aether.aether.player.PlayerManager;
import net.oriserver.aether.aether.statics.CommonMethods;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

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
    }
}
