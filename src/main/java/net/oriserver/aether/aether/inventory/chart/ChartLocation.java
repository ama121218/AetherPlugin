package net.oriserver.aether.aether.inventory.chart;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public enum ChartLocation {
    CHART_1("草の道",657.500, 4.001, 702.500),
    CHART_2("森の隠れ家",657.500, 4.001, 768.500),
    CHART_3("寒い丘",657.500, 4.001, 834.500),
    CHART_4("麦が並ぶ家",657.500, 4.001, 900.500),
    CHART_5("不気味な釣り場",657.500, 4.001, 966.500),
    CHART_6("砂利の谷",657.500, 4.001, 1032.500),
    CHART_7("マグマの溜池",657.500, 4.001, 1098.500),
    CHART_8("谷間の川",657.500, 4.001, 1164.500),
    CHART_9("山間の集落跡",657.500, 4.001, 1230.500),
    CHART_10("朱の遺跡",657.500, 4.001, 1296.500),
    CHART_11("行水の谷",657.500, 4.001, 1362.500),
    CHART_12("金属結晶の洞窟",657.500, 4.001, 1428.500),
    CHART_13("泥中の蓮",657.500, 4.001, 1494.500),
    CHART_14("沼地の館",657.500, 4.001, 1560.500);

    private final String name;
    private final double x;
    private final double y;
    private final double z;

    ChartLocation(String name,double x, double y, double z) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Location getLocation(int chart){
        if(!(1<=chart&&chart<=14))return null;
        ChartLocation chartLocation = ChartLocation.valueOf("CHART_"+chart);
        return new Location(Bukkit.getWorld("shrine"), chartLocation.x, chartLocation.y, chartLocation.z);
    }
    public static String getName(int chart){
        ChartLocation chartLocation = ChartLocation.valueOf("CHART_"+chart);
        return chartLocation.name;
    }
}
