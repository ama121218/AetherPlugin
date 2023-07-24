package net.oriserver.aether.aether;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public enum AthleticLocation {
    LOBBY("world", 123.500, 118.1, -3.500, 90, 0),
    SHRINE("shrine", -43.5, 33.001, 156.5, -90, 0),
    GLOBAL("global", 0.5, 54.001, 5.5, -180, 0),
    CHART("chart", -1572.5, 100.001, 1635.5, 0, 0),
    ORDEAL("ordeal", 0.5, 97.001, 0.5, 0, 0),
    ASURE_LOBBY("asure", -922.5, 4.001, -114.5, 0, 0),
    WATERMELON_ASURE("asure", -911.5, 87.001, -7.5, 0, 0),
    ERASER_ASURE("asure", -2731.5, 127.001, 37.5, 0, 0),
    OLD_LOBBY("world", 9008.5, 2.001, 414.5, 0, 0),
    PRACTICE_WORLD("world", -991.5, 5.001, 427.5, 0, 0),
    NETA_WORLD("neta", 438.5, 4.001, 802.5, 0, 0),
    SECTION_LOBBY("section", 1620.5, 4.001, 1633.5, 0, 0),
    AMATERASU_SECTION("section", 1000.5, 4.001, 994.5, 0, 0),
    ZEPUTO_SECTION("section", 1000.5, 15.001, 2238.5, 0, 0),
    ERASER_SECTION("section", 1000.5, 7.001, 3006.5, 0, 0),
    TNTRUN("tntrun", 789.5, 4.001, -1154.5, 0, 0);

    private final String worldName;
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;

    AthleticLocation(String worldName, double x, double y, double z, float yaw, float pitch) {
        this.worldName = worldName;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public static Location getLocation(AthleticLocation athleticLocation) {
        Location location = new Location(
                Bukkit.getWorld(athleticLocation.worldName),
                athleticLocation.x,
                athleticLocation.y,
                athleticLocation.z
        );
        location.setYaw(athleticLocation.yaw);
        location.setPitch(athleticLocation.pitch);
        return location;
    }
}
