package net.oriserver.aether.aether.statics;

import net.oriserver.aether.aether.player.PlayerStats;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;

public class CommonMethods {
    private static final HashSet<Integer> slotSet = new HashSet<>(Arrays.asList(3,4,5,12,13,14,21,22,23,30,31,32,39,40,41));
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


    public static void setArmorStandOption(ArmorStand stand, String setName){
        stand.setGravity(false);
        stand.setCanPickupItems(false);
        stand.setCustomName(setName);
        stand.setVisible(false);
        stand.setCustomNameVisible(true);
        stand.setSmall(true);
    }
    public static void setHologram(Location location, String displayName){
        int chunkX = (int)location.getX() >> 4;
        int chunkZ = (int)location.getZ() >> 4;
        World world = location.getWorld();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                Chunk chunk = world.getChunkAt(chunkX + dx, chunkZ + dz);
                if(!chunk.isLoaded())chunk.load();
            }
        }
        ArmorStand armorStand = world.spawn(location, ArmorStand.class);
        setArmorStandOption(armorStand,displayName);
    }
    public static void deleteHologram(Location location){
        int chunkX = (int)location.getX() >> 4;
        int chunkZ = (int)location.getZ() >> 4;
        World world = location.getWorld();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                Chunk chunk = world.getChunkAt(chunkX + dx, chunkZ + dz);
                if(!chunk.isLoaded())chunk.load();
                for(Entity entity :chunk.getEntities()){
                    if (entity.getType() == EntityType.ARMOR_STAND) {
                        Location local = entity.getLocation();
                        if(location.getX() == local.getX() && location.getZ()==local.getZ()) {
                            entity.remove();
                        }
                    }
                }
            }
        }
    }
    public static boolean isInSlotSet(int slot){
        return slotSet.contains(slot);
    }

}
