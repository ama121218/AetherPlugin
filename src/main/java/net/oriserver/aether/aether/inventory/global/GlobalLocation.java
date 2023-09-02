package net.oriserver.aether.aether.inventory.global;

import net.oriserver.aether.aether.inventory.chart.ChartLocation;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public enum GlobalLocation {

    Global_1("アクロポリス",-282.500,92.001, -234.500,0,-15),
    Global_2("ピラミッド",-969.500, 53.001, -30.500,45,-9),
    Global_3("日本",69.500, 52.001, -524.500,0,0);

    private final String name;
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;

    GlobalLocation(String name,double x, double y, double z) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = 0;
        this.pitch = 0;
    }
    GlobalLocation(String name,double x, double y, double z,float yaw,float pitch) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public static Location getGlobalLocation(int chart){
        GlobalLocation globalLocation = GlobalLocation.valueOf("Global_"+chart);
        return new Location(Bukkit.getWorld("global"), globalLocation.x, globalLocation.y, globalLocation.z, globalLocation.yaw,globalLocation.pitch);
    }
    public static String getGlobalName(int chart){
        GlobalLocation globalLocation = GlobalLocation.valueOf("Global_"+chart);
        return globalLocation.name;
    }
}
