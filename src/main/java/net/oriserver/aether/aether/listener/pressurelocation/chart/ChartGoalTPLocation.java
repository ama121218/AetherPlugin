package net.oriserver.aether.aether.listener.pressurelocation.chart;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.HashMap;

public class ChartGoalTPLocation {
    private final HashMap<Integer, Local_xyz> coordinatesMap = new HashMap<Integer, Local_xyz>();

    public ChartGoalTPLocation(){
        coordinatesMap.put(1, new Local_xyz(-1575.500,100.100,1649.500,74,11));
        coordinatesMap.put(2, new Local_xyz(-1584.500,101.100,1655.500,30,21));
        coordinatesMap.put(3, new Local_xyz(-1580.500,102.100,1666.500,-24,32));
        coordinatesMap.put(4, new Local_xyz(-1570.500,100.100,1671.500,-161,16));
        coordinatesMap.put(5, new Local_xyz(-1557.500,100.100,1661.500,-3,11));
        coordinatesMap.put(6, new Local_xyz(-1554.500,101.100,1678.500,10,21));
        coordinatesMap.put(7, new Local_xyz(-1556.500,101.100,1691.500,-45,17));
        coordinatesMap.put(8, new Local_xyz(-1550.500,101.100,1699.500,42,18));
        coordinatesMap.put(9, new Local_xyz(-1559.500,108.100,1715.500,102,42));
        coordinatesMap.put(10, new Local_xyz(-1587.500,100.100,1715.500,140,43));
        coordinatesMap.put(11, new Local_xyz(-1594.500,96.100,1710.500,47,32));
        coordinatesMap.put(12, new Local_xyz(-1602.500,93.100,1718.500,108,-7));
        coordinatesMap.put(13, new Local_xyz(-1615.500,100.100,1703.500,140,26));
        coordinatesMap.put(14, new Local_xyz(-1572.500,100.100,1635.500,90,23));

        coordinatesMap.put(15, new Local_xyz(-1592.500,100.100,1624.500,135,32));
        coordinatesMap.put(16, new Local_xyz(-1598.500,100.100,1617.500,150,18));
        coordinatesMap.put(17, new Local_xyz(-1606.500,100.100,1614.500,45,19));
        coordinatesMap.put(18, new Local_xyz(-1612.500,110.100,1644.500,43,20));
        coordinatesMap.put(19, new Local_xyz(-1627.500,106.100,1653.500,50,16));
        coordinatesMap.put(20, new Local_xyz(-1643.500,92.100,1663.500,138,-16));
        coordinatesMap.put(21, new Local_xyz(-1643.500,96.100,1632.500,165,2));
        coordinatesMap.put(22, new Local_xyz(-1646.500,100.100,1614.500,-46,7));
        coordinatesMap.put(23, new Local_xyz(-1629.500,100.100,1618.500,-126,20));
        coordinatesMap.put(24, new Local_xyz(-1616.500,100.100,1607.500,173,9));
        coordinatesMap.put(25, new Local_xyz(-1621.500,100.100,1592.500,146,17));
        coordinatesMap.put(26, new Local_xyz(-1630.500,100.100,1583.500,45,25));
        coordinatesMap.put(27, new Local_xyz(-1636.500,106.100,1604.500,108,13));
        coordinatesMap.put(28, new Local_xyz(-1572.500,100.100,1635.500,-90,0));

        coordinatesMap.put(29, new Local_xyz(-1558.500,100.100,1636.500,-101,30));
        coordinatesMap.put(30, new Local_xyz(-1541.500,100.100,1624.500,174,9));
        coordinatesMap.put(31, new Local_xyz(-1538.500,101.100,1611.500,-94,18));
        coordinatesMap.put(32, new Local_xyz(-1530.500,102.100,1610.500,-83,22));
        coordinatesMap.put(33, new Local_xyz(-1519.500,101.100,1619.500,50,17));
        coordinatesMap.put(34, new Local_xyz(-1526.500,101.100,1636.500,12,18));
        coordinatesMap.put(35, new Local_xyz(-1527.500,102.100,1650.500,15,8));
        coordinatesMap.put(36, new Local_xyz(-1526.500,105.100,1660.500,-30,13));
        coordinatesMap.put(37, new Local_xyz(-1520.500,104.100,1671.500,-25,59));
        coordinatesMap.put(38, new Local_xyz(-1516.500,98.100,1668.500,-115,18));
        coordinatesMap.put(39, new Local_xyz(-1511.500,98.100,1662.500,-160,29));
        coordinatesMap.put(40, new Local_xyz(-1505.500,98.100,1644.500,-126,27));
        coordinatesMap.put(41, new Local_xyz(-1496.500,100.100,1638.500,-90,24));
        coordinatesMap.put(42, new Local_xyz(-1572.500,100.100,1635.500,180,0));
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

