package net.oriserver.aether.aether.chart.stage;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public enum ChartLocation {
    CHART_1("草の道",-1579.500, 11.001, 2068.500),
    CHART_2("森の隠れ家",-1539.500, 4.001, 2425.500,90,0),//
    CHART_3("寒い丘",-1540.500, 14.001, 2671.500,45,0),//
    CHART_4("麦が並ぶ家",-1531.500, 14.001, 2998.500,90,0),//
    CHART_5("不気味な釣り場",-1580.500, 16.001, 3280.500,45,0),//
    CHART_6("砂利の谷",-1563.500, 4.001, 3557.500),
    CHART_7("マグマの溜池",-1551.500, 16.001, 3889.500,90,0),//
    CHART_8("谷間の川",-1558.500, 31.001, 4160.500,-90,0),//
    CHART_9("山間の集落跡",-1612.500, 16.001, 4644.500,-90,0),//
    CHART_10("朱の遺跡",-1568.500, 5.001, 4753.500),
    CHART_11("行水の谷",-1531.500, 23.001, 5061.500),
    CHART_12("金属結晶の洞窟",-1546.500, 60.001, 5419.500,70,5),//
    CHART_13("泥中の蓮",-1581.500, 15.001, 5664.500),
    CHART_14("沼地の館",-1591.500, 24.001, 6157.500),

    CHART_15("",-2227.5, 28, 1714.5,180,0),
    CHART_16("",-2950.500, 79.001, 1620.500,90,0),
    CHART_17("",-3629.500, 14.001, 1640.500,90,0),
    CHART_18("",-4225.5, 25, 1635.5,90,0),
    CHART_19("",-4861.500, 78.001, 1652.500,90,0),
    CHART_20("",-5446.5, 4, 1621.5,90,0),
    CHART_21("",-6259.500, 15.001, 1642.500,90,0),
    CHART_22("",-6830.5, 4.0, 1643.5,90,0),
    CHART_23("",-7415.500, 14.001, 1660.500,90,0),
    CHART_24("",-8055.5, 4.0, 1614.5,90,0),
    CHART_25("",-8719.5, 4.0, 1622.5,90,0),
    CHART_26("",-9488.500, 28.001, 1624.500,90,0),
    CHART_27("",-10093.500, 40.001, 1635.500,90,0),
    CHART_28("",-10838.500, 33.001, 1645.500,90,0),

    CHART_29("",-696.500, 73.001, 1635.500,-90,0),
    CHART_30("",3.500, 14.001, 1635.500,-90,0),
    CHART_31("",605.5, 14, 1575,0,0),
    CHART_32("",1285.5, 19, 1681.5,135,0),
    CHART_33("",1721.500, 16.001, 1554.500,-8,0),
    CHART_34("",2361.5, 19, 1670,-150,0),
    CHART_35("",3061.500, 14.001, 1635.500,-90,0),
    CHART_36("",3623.5, 13, 1498.5,-23,10),
    CHART_37("",4196.500, 14.001, 1652.500,-90,0),
    CHART_38("",4737.500, 14.001, 1634.500,-90,0),
    CHART_39("",5368.5, 14, 1561.5,-45,0),
    CHART_40("",6057.500, 90.001, 1609.500,-90,0),
    CHART_41("",6546.500, 19.001, 1656.500,-90,0),
    CHART_42("",7260.500, 14.001, 1796.500,135,0),

    CHART_43("", -1572.5, 16, 1102.5,180,0),
    CHART_44("", -1572.5, 16, 501.5,180,0),
    CHART_45("", -1572.5, 16, -99.5,180,0),
    CHART_46("", -1572.5, 16, -701,180,0),
    CHART_47("", -1572.5, 16, -1302.5,180,0),
    CHART_48("", -1572.5, 16, -1904,180,0),
    CHART_49("", -1572.5, 16, -2505.5,180,0),
    CHART_50("", -1572.5, 16, -3107,180,0),
    CHART_51("", -1572.5, 16, -3708.5,180,0),
    CHART_52("", -1572.5, 16, -4310,180,0),
    CHART_53("", -1572.5, 16, -4911.5,180,0),
    CHART_54("", -1572.5, 16, -5513,180,0),
    CHART_55("", -1572.5, 16, -6114.5,180,0),
    CHART_56("", -1572.5, 16, -6716,180,0);


    private final String name;
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;

    ChartLocation(String name,double x, double y, double z) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = 0;
        this.pitch = 0;
    }
    ChartLocation(String name,double x, double y, double z,float yaw,float pitch) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public static Location getChartLocation(int chart){
        ChartLocation chartLocation = ChartLocation.valueOf("CHART_"+chart);
        return new Location(Bukkit.getWorld("chart"), chartLocation.x, chartLocation.y, chartLocation.z, chartLocation.yaw,chartLocation.pitch);
    }
    public static String getChartName(int chart){
        ChartLocation chartLocation = ChartLocation.valueOf("CHART_"+chart);
        return chartLocation.name;
    }
}

