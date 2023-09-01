package net.oriserver.aether.aether.inventory.chart;

import org.bukkit.ChatColor;

public enum ChartTimeStandard {
    CHART_1(120*1000, 60*1000, 40*1000),
    CHART_2(90*1000, 50*1000, 30*1000),
    CHART_3(90*1000, 50*1000, 23*1000),
    CHART_4(120*1000, 50*1000, 28*1000),
    CHART_5(1300*1000, 100*1000, 50*1000),
    CHART_6(120*1000, 60*1000, 30*1000),
    CHART_7(150*1000, 120*1000, 40*1000),
    CHART_8(180*1000, 120*1000, 70*1000),
    CHART_9(150*1000, 90*1000, 50*1000),
    CHART_10(150*1000, 90*1000, 45*1000),
    CHART_11(150*1000, 90*1000, 50*1000),
    CHART_12(170*1000, 100*1000, 55*1000),
    CHART_13(150*1000, 90*1000, 55*1000),
    CHART_14(180*1000, 120*1000, 60*1000),

    CHART_15(180*1000, 120*1000, 60*1000),
    CHART_16(180*1000, 120*1000, 60*1000),
    CHART_17(180*1000, 120*1000, 60*1000),
    CHART_18(180*1000, 120*1000, 60*1000),
    CHART_19(180*1000, 120*1000, 60*1000),
    CHART_20(180*1000, 120*1000, 60*1000),
    CHART_21(180*1000, 120*1000, 60*1000),
    CHART_22(180*1000, 120*1000, 60*1000),
    CHART_23(180*1000, 120*1000, 60*1000),
    CHART_24(180*1000, 120*1000, 60*1000),
    CHART_25(180*1000, 120*1000, 60*1000),
    CHART_26(180*1000, 120*1000, 60*1000),
    CHART_27(180*1000, 120*1000, 60*1000),
    CHART_28(180*1000, 120*1000, 60*1000),

    CHART_29(180*1000, 120*1000, 60*1000),
    CHART_30(180*1000, 120*1000, 60*1000),
    CHART_31(180*1000, 120*1000, 60*1000),
    CHART_32(180*1000, 120*1000, 60*1000),
    CHART_33(180*1000, 120*1000, 60*1000),
    CHART_34(180*1000, 120*1000, 60*1000),
    CHART_35(180*1000, 120*1000, 60*1000),
    CHART_36(180*1000, 120*1000, 60*1000),
    CHART_37(180*1000, 120*1000, 60*1000),
    CHART_38(180*1000, 120*1000, 60*1000),
    CHART_39(180*1000, 120*1000, 60*1000),
    CHART_40(180*1000, 120*1000, 60*1000),
    CHART_41(180*1000, 120*1000, 60*1000),
    CHART_42(180*1000, 120*1000, 60*1000),

    CHART_43(180*1000, 120*1000, 60*1000),
    CHART_44(180*1000, 120*1000, 60*1000),
    CHART_45(180*1000, 120*1000, 60*1000),
    CHART_46(180*1000, 120*1000, 60*1000),
    CHART_47(180*1000, 120*1000, 60*1000),
    CHART_48(180*1000, 120*1000, 60*1000),
    CHART_49(180*1000, 120*1000, 60*1000),
    CHART_50(180*1000, 120*1000, 60*1000),
    CHART_51(180*1000, 120*1000, 60*1000),
    CHART_52(180*1000, 120*1000, 60*1000),
    CHART_53(180*1000, 120*1000, 60*1000),
    CHART_54(180*1000, 120*1000, 60*1000),
    CHART_55(180*1000, 120*1000, 60*1000),
    CHART_56(180*1000, 120*1000, 60*1000);

    private final long[] times;

    ChartTimeStandard(long time1, long time2, long time3) {
        this.times = new long[]{time1, time2, time3};
    }

    public long[] getTimes() {
        return times;
    }

    static public long[] getChartStandard(int number){
        return ChartTimeStandard.values()[number-1].getTimes();
    }

    public static String[] getStringTimes(long[] longs){
        String[] strings = new String[3];
        for(int i=0;i<3;i++){
            strings[i] = ChatColor.WHITE + String.format("%02d:%02d:%03d", (longs[i] / (1000 * 60)) % 60, (longs[i] / 1000) % 60, longs[i] % 1000);
        }
        return strings;
    }
    public static String getStringTime(long time){
        return String.format("%02d:%02d:%03d", (time / (1000 * 60)) % 60, (time / 1000) % 60, time % 1000);
    }
    public static int getStarRating(int number, long time) {
        long[] timeStandard = ChartTimeStandard.values()[number-1].getTimes();
        if(time==0)return 0;
        if (time <= timeStandard[2]) {
            return 3; // スター3
        } else if (time <= timeStandard[1]) {
            return 2; // スター2
        } else if (time <= timeStandard[0]) {
            return 1; // スター1
        } else {
            return 0; // スター0 (基準タイムを全て超えた場合)
        }
    }
    public static String getStarString(int star){
        if(star==0)       return org.bukkit.ChatColor.YELLOW + "✧✧✧";
        else if (star==1) return org.bukkit.ChatColor.YELLOW + "✦✧✧";
        else if (star==2) return org.bukkit.ChatColor.YELLOW + "✦✦✧";
        else              return ChatColor.YELLOW + "✦✦✦";
    }
}
