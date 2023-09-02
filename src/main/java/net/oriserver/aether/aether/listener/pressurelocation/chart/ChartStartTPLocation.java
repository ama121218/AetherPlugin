package net.oriserver.aether.aether.listener.pressurelocation.chart;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.HashMap;

public class ChartStartTPLocation {
    private final HashMap<Integer, Local_xyz> coordinatesMap = new HashMap<Integer,Local_xyz>();

    public ChartStartTPLocation(){
        coordinatesMap.put(1, new Local_xyz(-1579.500, 11.001, 2078.500,0,20));
        coordinatesMap.put(2, new Local_xyz(-1548.500, 4.001, 2425.500, 90,0));
        coordinatesMap.put(3, new Local_xyz(-1554.500, 9.001, 2683.500, 90,0));
        coordinatesMap.put(4, new Local_xyz(-1537.500, 14.001, 2998.500, 90,0));
        coordinatesMap.put(5, new Local_xyz(-1587.500, 16.001, 3287.500, 49,0));
        coordinatesMap.put(6, new Local_xyz(-1563.500, 4.001, 3564.500));
        coordinatesMap.put(7, new Local_xyz(-1558.500, 16.001, 3889.500, 90, 30));
        coordinatesMap.put(8, new Local_xyz(-1548.500, 31.001, 4160.500, -63, 20));
        coordinatesMap.put(9, new Local_xyz(-1605.500, 16.001, 4645.500, -90, 10));
        coordinatesMap.put(10, new Local_xyz(-1568.500, 4.001, 4767.500,0, 0));
        coordinatesMap.put(11, new Local_xyz(-1531.500, 23.001, 5070.500,0, 20));
        coordinatesMap.put(12, new Local_xyz(-1553.500, 60.001, 5421.500, 76, 9));
        coordinatesMap.put(13, new Local_xyz(-1581.500, 15.001, 5675.500, 7, 26));
        coordinatesMap.put(14, new Local_xyz(-1591.500, 24.001, 6165.500,0, -16));

        coordinatesMap.put(15, new Local_xyz( -2227.5, 28.001, 1705.0, 175, 10));
        coordinatesMap.put(16, new Local_xyz( -2988.500, 79.001, 1620.500, 90, 20));
        coordinatesMap.put(17, new Local_xyz( -3638.500, 14.001, 1640.500, 90, 10));
        coordinatesMap.put(18, new Local_xyz( -4243.500, 25.001, 1635.500, 90, 20));
        coordinatesMap.put(19, new Local_xyz( -4874.500, 80.001, 1650.500, 90, 20));
        coordinatesMap.put(20, new Local_xyz( -5457.500, 4.001, 1621.0, 90, 20));
        coordinatesMap.put(21, new Local_xyz( -6267.500, 15.001, 1642.500, 90, 20));
        coordinatesMap.put(22, new Local_xyz( -6838.500, 4.001, 1643.5, 90, 20));
        coordinatesMap.put(23, new Local_xyz( -7422.500, 14.001, 1660.500, 90, 20));
        coordinatesMap.put(24, new Local_xyz( -8065.500, 4.001, 1614.500, 90, 20));
        coordinatesMap.put(25, new Local_xyz( -8733.500, 4.001, 1622.0, 90, 20));
        coordinatesMap.put(26, new Local_xyz( -9495.500, 28.001, 1624.500, 90, 20));
        coordinatesMap.put(27, new Local_xyz( -10106.500, 40.001, 1635.500, 90, 20));
        coordinatesMap.put(28, new Local_xyz( -10844.500, 33.001, 1645.500, 90, 20));

        coordinatesMap.put(29, new Local_xyz( -685.500, 68.001, 1635.500, -90, 20));
        coordinatesMap.put(30, new Local_xyz( 11.500, 14.001, 1635.500, -90, 20));
        coordinatesMap.put(31, new Local_xyz( 611.500, 11.001, 1587.500, 0, 10));
        coordinatesMap.put(32, new Local_xyz( 1240.500, 49.001, 1636.500, -17, 12));
        coordinatesMap.put(33, new Local_xyz( 1728.500, 16.001, 1600.500, 0, 20));
        coordinatesMap.put(34, new Local_xyz( 2380.500, 14.001, 1650.500, -128, 5));
        coordinatesMap.put(35, new Local_xyz( 3074.500, 14.001, 1635.500, -90, 20));
        coordinatesMap.put(36, new Local_xyz( 3629.500, 13.001, 1509.500, -70, 18));
        coordinatesMap.put(37, new Local_xyz( 4203.500, 14.001, 1652.500, -90, 20));
        coordinatesMap.put(38, new Local_xyz( 4743.500, 14.001, 1634.500, -90, 20));
        coordinatesMap.put(39, new Local_xyz( 5395.500, 12.001, 1564.500, 50, 12));
        coordinatesMap.put(40, new Local_xyz( 6064.500, 90.001, 1609.500, -90, 20));
        coordinatesMap.put(41, new Local_xyz( 6558.500, 21.001, 1659.500, -90, 20));
        coordinatesMap.put(42, new Local_xyz( 7253.500, 14.001, 1791.500, 120, 10));
    }
    
    public class Local_xyz{
        double x,y,z;
        float yaw,pitch;
        Local_xyz(double x,double y,double z){
            this.x = x;
            this.y = y;
            this.z = z;
            this.yaw = 0;
            this.pitch = 0;
        }
        Local_xyz(double x,double y,double z,float yaw,float pitch){
            this.x = x;
            this.y = y;
            this.z = z;
            this.yaw = yaw;
            this.pitch = pitch;
        }
    }
    public Location getLocation(int chart){
        Local_xyz l = coordinatesMap.get(chart);
        return new Location(Bukkit.getWorld("chart"),l.x,l.y,l.z,l.yaw,l.pitch);
    }

}
