package net.oriserver.aether.aether.statics;

import net.oriserver.aether.aether.player.PlayerStats;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;

public class CommonMethods {//どのクラスからも共通で使われるメソッドのまとめ
    private static final HashSet<Integer> slotSet = new HashSet<>(Arrays.asList(3,4,5,12,13,14,21,22,23,30,31,32,39,40,41));
    private static final String[] colorString = {"WHITE","GOLD","DARK_PURPLE","AQUA","YELLOW","GREEN","LIGHT_PURPLE","DARK_GRAY","GRAY","DARK_AQUA","DARK_PURPLE","BLUE","GOLD","DARK_GREEN","DARK_RED","BLACK"};
    static public void setTeleport(Player p, Location location, String string_location, PlayerStats playerStats){//テレポートする際に、テレポートの場所を記録する
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


    public static void setArmorStandOption(ArmorStand stand, String setName){//アーマースタンドの設定を行う
        stand.setGravity(false);//重力
        stand.setCanPickupItems(false);//アイテムの取り外し禁止
        stand.setCustomName(setName);//名前をセット
        stand.setVisible(false);//見えなくする
        stand.setCustomNameVisible(true);//名前の表示
        stand.setSmall(true);//小さいアーマースタンドに設定
    }
    public static void setHologram(Location location, String displayName){//指定の位置にアーマースタンドを設置
        int chunkX = (int)location.getX() >> 4;//チャンク読み込み
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
    public static void deleteHologram(Location location){//指定された座標のアーマースタンドの削除
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
    public static void deleteChartHologram(double x,double z){//指定されたx,z座標のアーマースタンドの削除
        int chunkX = (int)x >> 4;
        int chunkZ = (int)z >> 4;
        World world = Bukkit.getWorld("chart");
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                Chunk chunk = world.getChunkAt(chunkX + dx, chunkZ + dz);
                if(!chunk.isLoaded())chunk.load();
                for(Entity entity :chunk.getEntities()){
                    if (entity.getType() == EntityType.ARMOR_STAND) {
                        Location local = entity.getLocation();
                        if(x == local.getX() && z == local.getZ()) {
                            entity.remove();
                        }
                    }
                }
            }
        }
    }
    public static boolean isInSlotSet(int slot){
        return slotSet.contains(slot);
    }//slot番号が含まれているか
    public static String getColorString(int slot){return colorString[slot];}//slotから色を設定

}
