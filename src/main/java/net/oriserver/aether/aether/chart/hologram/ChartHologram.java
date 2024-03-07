package net.oriserver.aether.aether.chart.hologram;

import net.oriserver.aether.aether.chart.stage.ChartStage;
import net.oriserver.aether.aether.sqlite.SQLiteManager;
import net.oriserver.aether.aether.sqlite.chartDB.ChartRankingDB;
import net.oriserver.aether.aether.chart.stage.ChartStageInfo;
import net.oriserver.aether.aether.statics.CommonMethods;
import org.bukkit.*;

import java.util.*;

public class ChartHologram {//チャートのホログラムを扱うクラス

    private final ChartRankingDB chartRankingDB;
    private final ChartStageInfo chartStageInfo;

    public ChartHologram(SQLiteManager sqLiteManager, ChartStageInfo chartStageInfo){
        this.chartStageInfo = chartStageInfo;
        this.chartRankingDB = sqLiteManager.getChartRankingDB();
        setAllHologram();
    }

    public void setChartTime(int stage_id){
        ChartStage chartStage = chartStageInfo.getChartStage(stage_id);
        if(chartStage.getStageName()!=null){
            CommonMethods.deleteChartHologram(chartStage.getHologram_time_x()+0.5,chartStage.getHologram_time_z()+0.5);
            List<ChartRankingDB.RankingDataName> list = chartRankingDB.getTop5ScoreName(stage_id);
            Location location = new Location(Bukkit.getWorld("chart"),chartStage.getHologram_time_x()+0.5,chartStage.getHologram_time_y()+1.1,chartStage.getHologram_time_z()+0.5);
            for(int i=0;i<5;i++){
                location.setY(location.getY()-(0.3));
                if(i<list.size()){
                    CommonMethods.setHologram(location,ChatColor.BOLD+"#"+(i+1)+" "+list.get(i).getplayer_name()+" "+getStringTime(list.get(i).getTime()));
                }else{
                    CommonMethods.setHologram(location,ChatColor.BOLD+"#"+(i+1)+" "+"--"+" "+getStringTime(0));
                }
            }
        }
    }

    public String getStringTime(long time){
        return String.format("%02d:%02d:%03d", (time / (1000 * 60)) % 60, (time / 1000) % 60, time % 1000);
    }

    public void setAllHologram(){
        for(int i=1;i<=56;i++){
            ChartStage chartStage = chartStageInfo.getChartStage(i);
            if(chartStage.getStageName()!=null) {
                if (chartStage.getStageName() != null) {
                    CommonMethods.deleteChartHologram(chartStage.getStage_x()+0.5, chartStage.getStage_z()+0.5);
                    CommonMethods.deleteChartHologram(chartStage.getBack_stage_x()+0.5,chartStage.getBack_stage_z()+0.5);
                    CommonMethods.deleteChartHologram(chartStage.getStart_x()+0.5, chartStage.getStart_z()+0.5);
                    CommonMethods.deleteChartHologram(chartStage.getGoal_x()+0.5, chartStage.getGoal_z()+0.5);
                    if (chartStage.getCheckPoints() != null) {
                        for (ChartStage.Local_xyz local_xyz : chartStage.getCheckPoints()) {
                            CommonMethods.deleteChartHologram(local_xyz.getX()+0.5, local_xyz.getZ()+0.5);
                        }
                    }
                    CommonMethods.deleteChartHologram(chartStage.getHologram_time_x()+0.5, chartStage.getHologram_time_z()+0.5);
                    CommonMethods.deleteChartHologram(chartStage.getHologram_stageName_x()+0.5, chartStage.getHologram_stageName_z()+0.5);
                }
            }
        }
        for(int i = 1; i <= 56; i++){
            ChartStage chartStage = chartStageInfo.getChartStage(i);
            int mainStage = (i - 1) / 14 + 1;//main
            int subStage = (i - 1) % 14 + 1;//sub
            if(chartStage.getStageName()!=null) {
                Location location_stage = new Location(Bukkit.getWorld("chart"), chartStage.getStage_x()+0.5, chartStage.getStage_y()+0.3, chartStage.getStage_z()+0.5);
                CommonMethods.setHologram(location_stage, ChatColor.GOLD + "|||" + ChatColor.WHITE + "" + ChatColor.BOLD + (mainStage + "_" + subStage) + ChatColor.GOLD + "|||");
                Location location_back_stage = new Location(Bukkit.getWorld("chart"), chartStage.getBack_stage_x()+0.5, chartStage.getBack_stage_y()+0.3, chartStage.getBack_stage_z()+0.5);
                CommonMethods.setHologram(location_back_stage, ChatColor.GOLD + "|||" + ChatColor.WHITE + "" + ChatColor.BOLD + "back" + ChatColor.GOLD + "|||");
                Location location_start = new Location(Bukkit.getWorld("chart"), chartStage.getStart_x()+0.5, chartStage.getStart_y()+0.3, chartStage.getStart_z()+0.5);
                CommonMethods.setHologram(location_start, ChatColor.GOLD + "|||" + ChatColor.WHITE + "" + ChatColor.BOLD + "start" + ChatColor.GOLD + "|||");
                Location location_goal = new Location(Bukkit.getWorld("chart"), chartStage.getGoal_x()+0.5, chartStage.getGoal_y()+0.3, chartStage.getGoal_z()+0.5);
                CommonMethods.setHologram(location_goal, ChatColor.GOLD + "|||" + ChatColor.WHITE + "" + ChatColor.BOLD + "goal" + ChatColor.GOLD + "|||");

                Location location_Stage_hologram = new Location(Bukkit.getWorld("chart"), chartStage.getHologram_stageName_x()+0.5, chartStage.getHologram_stageName_y()+0.5, chartStage.getHologram_stageName_z()+0.5);
                CommonMethods.setHologram(location_Stage_hologram, ChatColor.BOLD + "* " + mainStage + " - " + subStage + " *");
                location_Stage_hologram.setY(location_Stage_hologram.getY() - 0.3);
                CommonMethods.setHologram(location_Stage_hologram, ChatColor.valueOf(chartStage.getStageColor()) + "--- " + chartStageInfo.getStageName(i) + " ---");

                setChartTime(i);
                int count = chartStageInfo.getCheckPointAmount(i);
                LinkedHashSet<ChartStage.Local_xyz> checkPoints = chartStage.getCheckPoints();
                if (checkPoints != null) {
                    for (ChartStage.Local_xyz local_xyz : checkPoints) {
                        Location location = new Location(Bukkit.getWorld("chart"), local_xyz.getX()+0.5, local_xyz.getY()+0.3, local_xyz.getZ()+0.5);
                        CommonMethods.setHologram(location, "*CheckPoint*");
                    }
                }
            }
        }
    }

    public void setIndexHologram(int i){
        ChartStage chartStage = chartStageInfo.getChartStage(i);
        int mainStage = (i - 1) / 14 + 1;//main
        int subStage = (i - 1) % 14 + 1;//sub

        if(chartStage.getStageName()!=null) {
            Location location_stage = new Location(Bukkit.getWorld("chart"), chartStage.getStage_x()+0.5, chartStage.getStage_y()+0.3, chartStage.getStage_z()+0.5);
            CommonMethods.setHologram(location_stage, ChatColor.GOLD + "|||" + ChatColor.WHITE + "" + ChatColor.BOLD + (mainStage + "_" + subStage) + ChatColor.GOLD + "|||");
            Location location_back_stage = new Location(Bukkit.getWorld("chart"), chartStage.getBack_stage_x()+0.5, chartStage.getBack_stage_y()+0.3, chartStage.getBack_stage_z()+0.5);
            CommonMethods.setHologram(location_back_stage, ChatColor.GOLD + "|||" + ChatColor.WHITE + "" + ChatColor.BOLD + "back" + ChatColor.GOLD + "|||");
            Location location_start = new Location(Bukkit.getWorld("chart"), chartStage.getStart_x()+0.5, chartStage.getStart_y()+0.3, chartStage.getStart_z()+0.5);
            CommonMethods.setHologram(location_start, ChatColor.GOLD + "|||" + ChatColor.WHITE + "" + ChatColor.BOLD + "start" + ChatColor.GOLD + "|||");
            Location location_goal = new Location(Bukkit.getWorld("chart"), chartStage.getGoal_x()+0.5, chartStage.getGoal_y()+0.3, chartStage.getGoal_z()+0.5);
            CommonMethods.setHologram(location_goal, ChatColor.GOLD + "|||" + ChatColor.WHITE + "" + ChatColor.BOLD + "goal" + ChatColor.GOLD + "|||");

            Location location_Stage_hologram = new Location(Bukkit.getWorld("chart"), chartStage.getHologram_stageName_x()+0.5, chartStage.getHologram_stageName_y()+0.5, chartStage.getHologram_stageName_z()+0.5);
            CommonMethods.setHologram(location_Stage_hologram, ChatColor.BOLD + "* " + mainStage + " - " + subStage + " *");
            location_Stage_hologram.setY(location_Stage_hologram.getY() - 0.3);
            CommonMethods.setHologram(location_Stage_hologram, ChatColor.valueOf(chartStage.getStageColor()) + "--- " + chartStageInfo.getStageName(i) + " ---");

            setChartTime(i);
            int count = chartStageInfo.getCheckPointAmount(i);
            LinkedHashSet<ChartStage.Local_xyz> checkPoints = chartStage.getCheckPoints();
            if (checkPoints != null) {
                for (ChartStage.Local_xyz local_xyz : checkPoints) {
                    Location location = new Location(Bukkit.getWorld("chart"), local_xyz.getX()+0.5, local_xyz.getY()+0.3, local_xyz.getZ()+0.5);
                    CommonMethods.setHologram(location, "*CheckPoint*");
                }
            }
        }
    }
}
