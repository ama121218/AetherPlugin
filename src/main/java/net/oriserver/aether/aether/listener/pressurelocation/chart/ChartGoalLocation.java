package net.oriserver.aether.aether.listener.pressurelocation.chart;

import org.bukkit.Location;

import java.util.HashMap;

public class ChartGoalLocation {
    private final HashMap<String,Integer> coordinatesMap = new HashMap<String,Integer>();
    public ChartGoalLocation(){
        coordinatesMap.put("-1558.0,11.0,2268.0", 1);
        coordinatesMap.put("-1615.0,12.0,2461.0", 2);
        coordinatesMap.put("-1577.0,27.0,2707.0", 3);
        coordinatesMap.put("-1546.0,18.0,3055.0", 4);
        coordinatesMap.put("-1569.0,11.0,3326.0", 5);
        coordinatesMap.put("-1564.0,8.0,3717.0", 6);
        coordinatesMap.put("-1522.0,4.0,3976.0", 7);
        coordinatesMap.put("-1636.0,32.0,4420.0", 8);
        coordinatesMap.put("-1514.0,34.0,4641.0", 9);
        coordinatesMap.put("-1565.0,6.0,4947.0", 10);
        coordinatesMap.put("-1529.0,18.0,5284.0", 11);
        coordinatesMap.put("-1543.0,11.0,5452.0", 12);
        coordinatesMap.put("-1542.0,18.0,5865.0", 13);
        coordinatesMap.put("-1592.0,30.0,6204.0", 14);

        coordinatesMap.put("-2492.0,15.0,1577.0", 15);
        coordinatesMap.put("-3162.0,57.0,1622.0", 16);
        coordinatesMap.put("0.0,0.0,0.0", 17); // Empty input
        coordinatesMap.put("0.0,0.0,0.0", 18); // Empty input
        coordinatesMap.put("-5071.0,24.0,1655.0", 19);
        coordinatesMap.put("0.0,0.0,0.0", 20); // Empty input
        coordinatesMap.put("-6453.0,18.0,1642.0", 21);
        coordinatesMap.put("-6843.0,26.0,1643.0", 22);
        coordinatesMap.put("-7597.0,23.0,1660.0", 23);
        coordinatesMap.put("0.0,0.0,0.0", 24); // Empty input
        coordinatesMap.put("0.0,0.0,0.0", 25); // Empty input
        coordinatesMap.put("-9634.0,15.0,1624.0", 26);
        coordinatesMap.put("-10322.0,40.0,1639.0", 27);
        coordinatesMap.put("-10840.0,68.0,1645.0", 28);

        coordinatesMap.put("-398.0,74.0,1635.0", 29);
        coordinatesMap.put("177.0,37.0,1604.0", 30);
        coordinatesMap.put("732.0,35.0,1644.0", 31);
        coordinatesMap.put("1236.0,103.0,1627.0", 32);
        coordinatesMap.put("1912.0,18.0,1633.0", 33);
        coordinatesMap.put("0.0,0.0,0.0", 34); // Empty input
        coordinatesMap.put("3107.0,32.0,1635.0", 35);
        coordinatesMap.put("0.0,0.0,0.0", 36); // Empty input
        coordinatesMap.put("4361.0,16.0,1642.0", 37);
        coordinatesMap.put("5156.0,18.0,1584.0", 38);
        coordinatesMap.put("0.0,0.0,0.0", 39); // Empty input
        coordinatesMap.put("6166.0,28.0,1637.0", 40);
        coordinatesMap.put("6166.0,28.0,1637.0", 41);
        coordinatesMap.put("0.0,0.0,0.0", 42); // Empty input
    }
    public int getChart(Location location){
        Integer result = coordinatesMap.get(String.format("%.1f,%.1f,%.1f", location.getX(), location.getY(), location.getZ()));
        if (result == null) {
            return -1;
        }
        return result;
    }
}
