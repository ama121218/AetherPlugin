package net.oriserver.aether.aether.inventory.chart;

import net.md_5.bungee.api.ChatColor;

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
    CHART_14(180*1000, 120*1000, 60*1000);

    private final long[] times;

    ChartTimeStandard(long time1, long time2, long time3) {
        this.times = new long[]{time1, time2, time3};
    }

    public long[] getTimes() {
        return times;
    }

    static public long[] getChartstandard(int number){
        return ChartTimeStandard.values()[number-1].getTimes();
    }

    static public int getStarRating(int number, long time) {
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

}
