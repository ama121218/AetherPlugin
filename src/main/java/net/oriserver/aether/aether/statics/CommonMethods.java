package net.oriserver.aether.aether.statics;

import net.oriserver.aether.aether.player.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class CommonMethods {

    static public void setTeleport(Player p, Location location, String string_location, PlayerStats playerStats){
        p.teleport(location);
        playerStats.setLocation(string_location);

        p.getInventory().remove(Material.PRISMARINE_SHARD);

        if(string_location.equals("Chart_Lobby")||string_location.equals("Level_Lobby")||string_location.equals("Global_Lobby")){
            boolean b = false;
            for(int i=0;i<35;i++) {
                if (p.getInventory().getItem(i) == null) continue;
                if (p.getInventory().getItem(i).getItemMeta().hasDisplayName() && p.getInventory().getItem(i).getItemMeta().getDisplayName().equals(ChatColor.AQUA+"Teleport option")){
                    p.getInventory().setItem(i, Item.createitem(Material.IRON_INGOT,1, ChatColor.AQUA+"Teleport option"));
                    b = true;
                }
            }
            if(!b) {
                if (p.getInventory().getItem(7) == null)p.getInventory().setItem(7, Item.createitem(Material.IRON_INGOT, 1, ChatColor.AQUA + "Teleport option"));
                else p.getInventory().addItem(Item.createitem(Material.IRON_INGOT, 1, ChatColor.AQUA + "Teleport option"));
            }
        }
        else if(string_location.equals("Lobby")||string_location.equals("Athletic_Lobby")){
            p.getInventory().remove(Material.IRON_INGOT);
        }
    }

}
