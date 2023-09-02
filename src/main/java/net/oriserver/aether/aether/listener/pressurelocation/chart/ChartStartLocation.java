package net.oriserver.aether.aether.listener.pressurelocation.chart;

import org.bukkit.Location;

import java.util.HashMap;

public class ChartStartLocation {
    private final HashMap<String,Integer> coordinatesMap = new HashMap<String,Integer>();

    public ChartStartLocation(){
        coordinatesMap.put("-1580.0,11.0,2074.0", 1);
        coordinatesMap.put("-1546.0,4.0,2425.0", 2);
        coordinatesMap.put("-1544.0,14.0,2674.0", 3);
        coordinatesMap.put("-1536.0,14.0,2998.0", 4);
        coordinatesMap.put("-1585.0,16.0,3284.0", 5);
        coordinatesMap.put("-1564.0,4.0,3561.0", 6);
        coordinatesMap.put("-1557.0,16.0,3889.0", 7);
        coordinatesMap.put("-1554.0,31.0,4160.0", 8);
        coordinatesMap.put("-1609.0,16.0,4644.0", 9);
        coordinatesMap.put("-1569.0,5.0,4756.0", 10);
        coordinatesMap.put("-1532.0,23.0,5065.0", 11);
        coordinatesMap.put("-1552.0,60.0,5421.0", 12);
        coordinatesMap.put("-1582.0,15.0,5672.0", 13);
        coordinatesMap.put("-1592.0,24.0,6162.0", 14);

        coordinatesMap.put("-2228.0,28.0,1708.0", 15);
        coordinatesMap.put("-2956.0,79.0,1620.0", 16);
        coordinatesMap.put("-3635.0,14.0,1640.0", 17);
        coordinatesMap.put("-4232.0,25.0,1635.0", 18);
        coordinatesMap.put("-4872.0,80.0,1650.0", 19);
        coordinatesMap.put("-5450.0,4.0,1621.0", 20);
        coordinatesMap.put("-6265.0,15.0,1642.0", 21);
        coordinatesMap.put("-6835.0,4.0,1643.0", 22);
        coordinatesMap.put("-7420.0,14.0,1660.0", 23);
        coordinatesMap.put("-8058.0,4.0,1614.0", 24);
        coordinatesMap.put("-8724.0,4.0,1622.0", 25);
        coordinatesMap.put("-9493.0,28.0,1624.0", 26);
        coordinatesMap.put("-10104.0,40.0,1635.0", 27);
        coordinatesMap.put("-10843.0,33.0,1645.0", 28);

        coordinatesMap.put("-692.0,72.0,1635.0", 29);
        coordinatesMap.put("9.0,14.0,1635.0", 30);
        coordinatesMap.put("605.0,14.0,1580.0", 31);
        coordinatesMap.put("1278.0,19.0,1674.0", 32);
        coordinatesMap.put("1722.0,16.0,1561.0", 33);
        coordinatesMap.put("2363.0,19.0,1666.0", 34);
        coordinatesMap.put("3071.0,14.0,1635.0", 35);
        coordinatesMap.put("3627.0,13.0,1507.0", 36);
        coordinatesMap.put("4201.0,14.0,1652.0", 37);
        coordinatesMap.put("4741.0,14.0,1634.0", 38);
        coordinatesMap.put("5373.0,14.0,1566.0", 39);
        coordinatesMap.put("6062.0,90.0,1609.0", 40);
        coordinatesMap.put("6556.0,21.0,1656.0", 41);
        coordinatesMap.put("7256.0,14.0,1792.0", 42);


    }
    public int getChart(Location location){
        String string_location = String.format("%.1f,%.1f,%.1f", location.getX(), location.getY(), location.getZ());
        Integer result = coordinatesMap.get(string_location);
        if (result == null) {
            return -1;
        }
        return result;
    }
}