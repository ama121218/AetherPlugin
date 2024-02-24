package net.oriserver.aether.aether.chart.stage;

import net.oriserver.aether.aether.chart.ChartManager;
import net.oriserver.aether.aether.sqlite.SQLiteAPI;
import net.oriserver.aether.aether.sqlite.SQLiteManager;
import net.oriserver.aether.aether.statics.CommonMethods;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.util.*;

public class ChartStageInfo {

    private final SQLiteAPI chartStageDB;
    private final SQLiteAPI chartCheckPointDB;
    private final ChartManager chartManager;

    private final HashMap<Integer,ChartStage> chartStageMap = new HashMap<>();


    private final HashMap<Integer, String> stageNameMap = new HashMap<>();
    private final HashMap<String, Integer> stageMap = new HashMap<>();
    private final HashMap<Integer,Location> stageTPMap = new HashMap<>();
    private final HashMap<String, Integer> backStageMap = new HashMap<>();
    private final HashMap<Integer,Location> backStageTPMap = new HashMap<>();
    private final HashMap<String, Integer> startMap = new HashMap<>();
    private final HashMap<Integer, Location> startTPMap = new HashMap<>();
    private final HashMap<String, Integer> goalMap = new HashMap<>();
    private final HashMap<Integer, Location> goalTPMap = new HashMap<>();
    private final HashMap<String, String> checkPointMap = new HashMap<>();
    private final HashMap<String, Location> checkPointTPMap = new HashMap<>();
    private final HashMap<Integer,Integer> checkPointAmount = new HashMap<>();
    private final HashMap<Integer, Long[]> timeStandardMap = new HashMap<>();

    public ChartStageInfo(SQLiteManager sqLiteManager, ChartManager chartManager){
        this.chartStageDB = sqLiteManager.getChartStageDB();
        this.chartCheckPointDB = sqLiteManager.getChartCheckPointDB();
        this.chartManager = chartManager;
        setAllMap();
    }

    public void setAllMap(){
        chartStageMap.clear();
        stageMap.clear();
        stageTPMap.clear();
        backStageMap.clear();
        backStageTPMap.clear();
        startMap.clear();
        startTPMap.clear();
        goalMap.clear();
        goalTPMap.clear();
        checkPointMap.clear();
        checkPointTPMap.clear();
        timeStandardMap.clear();

        for(int i=1;i<=56;i++){
            ChartStage chartStage = setIndexStageMap(i);
            if(chartStage.getStageName()!=null) {
                stageNameMap.put(i,chartStage.getStageName());
                stageMap.put(chartStage.getStageCoordinateString(), i);
                stageTPMap.put(i,chartStage.getStageTPLocation());
                backStageMap.put(chartStage.getBackStageCoordinateString(), i);
                backStageTPMap.put(i,chartStage.getBackStageTPLocation());
                startMap.put(chartStage.getStartCoordinateString(), i);
                startTPMap.put(i,chartStage.getStartTPLocation());
                goalMap.put(chartStage.getGoalCoordinateString(), i);
                goalTPMap.put(i,chartStage.getGoalTPLocation());
                int j = 0;
                if(chartStage.getCheckPoints()!=null) {
                    for (ChartStage.Local_xyz local_xyz : chartStage.getCheckPoints()) {
                        j++;
                        checkPointMap.put(local_xyz.getX() + "," + local_xyz.getY() + "," + local_xyz.getZ(), i + "_" + (j));
                        checkPointTPMap.put(i + "_" + (j), new Location(Bukkit.getWorld("chart"), local_xyz.getX(), local_xyz.getY(), local_xyz.getZ()));
                    }
                }
                checkPointAmount.put(i,j);
                timeStandardMap.put(i,chartStage.getStarTime());
            }
        }
    }
    public void setIndexMap(int i){
        ChartStage pastChartStage = chartStageMap.get(i);
        if(pastChartStage.getStageName()!=null) {
            CommonMethods.deleteChartHologram(pastChartStage.getStage_x()+0.5,pastChartStage.getStage_z()+0.5);
            CommonMethods.deleteChartHologram(pastChartStage.getBack_stage_x()+0.5,pastChartStage.getBack_stage_z()+0.5);
            CommonMethods.deleteChartHologram(pastChartStage.getStart_x()+0.5,pastChartStage.getStart_z()+0.5);
            CommonMethods.deleteChartHologram(pastChartStage.getGoal_x()+0.5,pastChartStage.getGoal_z()+0.5);
            CommonMethods.deleteChartHologram(pastChartStage.getHologram_stageName_x()+0.5,pastChartStage.getHologram_stageName_z()+0.5);
            CommonMethods.deleteChartHologram(pastChartStage.getHologram_time_x()+0.5,pastChartStage.getHologram_time_z()+0.5);
            stageMap.remove(pastChartStage.getStageCoordinateString());
            startMap.remove(pastChartStage.getStartCoordinateString());
            goalMap.remove(pastChartStage.getGoalCoordinateString());
            int j = 0;
            if(pastChartStage.getCheckPoints()!=null) {
                for (ChartStage.Local_xyz local_xyz : pastChartStage.getCheckPoints()) {
                    j++;
                    CommonMethods.deleteChartHologram(local_xyz.getX()+0.5, local_xyz.getZ()+0.5);
                    checkPointMap.remove(local_xyz.getX() + "," + local_xyz.getY() + "," + local_xyz.getZ());
                    checkPointTPMap.remove(i + "_" + (j));
                }
            }
        }
        ChartStage thisChartStage = setIndexStageMap(i);
        if(thisChartStage.getStageName()!=null) {
            stageNameMap.put(i, thisChartStage.getStageName());
            stageMap.put(thisChartStage.getStageCoordinateString(), i);
            stageTPMap.put(i, thisChartStage.getStageTPLocation());
            backStageMap.put(thisChartStage.getBackStageCoordinateString(), i);
            backStageTPMap.put(i, thisChartStage.getBackStageTPLocation());
            startMap.put(thisChartStage.getStartCoordinateString(), i);
            startTPMap.put(i, thisChartStage.getStartTPLocation());
            goalMap.put(thisChartStage.getGoalCoordinateString(), i);
            goalTPMap.put(i, thisChartStage.getGoalTPLocation());
            int j = 0;
            if(thisChartStage.getCheckPoints()!=null) {
                for (ChartStage.Local_xyz local_xyz : thisChartStage.getCheckPoints()) {
                    j++;
                    checkPointMap.put(local_xyz.getX() + "," + local_xyz.getY() + "," + local_xyz.getZ(), i + "_" + (j));
                    checkPointTPMap.put(i + "_" + (j), new Location(Bukkit.getWorld("chart"), local_xyz.getX(), local_xyz.getY(), local_xyz.getZ()));
                }
            }
            checkPointAmount.put(i, j);
            timeStandardMap.put(i, thisChartStage.getStarTime());
            chartManager.getChartHologram().setIndexHologram(i);
        }
        for(Integer k:checkPointAmount.values()){
            //Bukkit.getServer().getLogger().info(""+k);
        }
    }

    public ChartStage getChartStage(int i){return chartStageMap.get(i);}
    public HashMap<Integer,ChartStage> getChartStageMap(){return chartStageMap;}

    public ChartStage setIndexStageMap(int i){
        ChartStage chartStage = new ChartStage();
        chartStage.setStageId(i);
        int main_page = (i - 1) / 14 + 1;
        int sub_page = (i - 1) % 14 + 1;
        String stage_id = main_page + "_" + sub_page;

        chartStageDB.getDB("SELECT * FROM ChartStage WHERE stage_id = ?", Arrays.asList(stage_id), rs -> {
            while (rs.next()){
                chartStage.setStageColor(rs.getString("stage_color"));
                chartStage.setStageName(rs.getString("stage_name"));
                chartStage.setStage_x(rs.getDouble("stage_x"));
                chartStage.setStage_y(rs.getDouble("stage_y"));
                chartStage.setStage_z(rs.getDouble("stage_z"));
                chartStage.setStage_tp_x(rs.getDouble("stage_tp_x"));
                chartStage.setStage_tp_y(rs.getDouble("stage_tp_y"));
                chartStage.setStage_tp_z(rs.getDouble("stage_tp_z"));
                chartStage.setStage_tp_yaw(rs.getFloat("stage_tp_yaw"));
                chartStage.setStage_tp_pitch(rs.getFloat("stage_tp_pitch"));
                chartStage.setBack_stage_x(rs.getDouble("back_stage_x"));
                chartStage.setBack_stage_y(rs.getDouble("back_stage_y"));
                chartStage.setBack_stage_z(rs.getDouble("back_stage_z"));
                chartStage.setBack_stage_tp_x(rs.getDouble("back_stage_tp_x"));
                chartStage.setBack_stage_tp_y(rs.getDouble("back_stage_tp_y"));
                chartStage.setBack_stage_tp_z(rs.getDouble("back_stage_tp_z"));
                chartStage.setBack_stage_tp_yaw(rs.getFloat("back_stage_tp_yaw"));
                chartStage.setBack_stage_tp_pitch(rs.getFloat("back_stage_tp_pitch"));
                chartStage.setStart_x(rs.getDouble("start_x"));
                chartStage.setStart_y(rs.getDouble("start_y"));
                chartStage.setStart_z(rs.getDouble("start_z"));
                chartStage.setStart_tp_x(rs.getDouble("start_tp_x"));
                chartStage.setStart_tp_y(rs.getDouble("start_tp_y"));
                chartStage.setStart_tp_z(rs.getDouble("start_tp_z"));
                chartStage.setStart_tp_yaw(rs.getFloat("start_tp_yaw"));
                chartStage.setStart_tp_pitch(rs.getFloat("start_tp_pitch"));
                chartStage.setGoal_x(rs.getDouble("goal_x"));
                chartStage.setGoal_y(rs.getDouble("goal_y"));
                chartStage.setGoal_z(rs.getDouble("goal_z"));
                chartStage.setGoal_tp_x(rs.getDouble("goal_tp_x"));
                chartStage.setGoal_tp_y(rs.getDouble("goal_tp_y"));
                chartStage.setGoal_tp_z(rs.getDouble("goal_tp_z"));
                chartStage.setGoal_tp_yaw(rs.getFloat("goal_tp_yaw"));
                chartStage.setGoal_tp_pitch(rs.getFloat("goal_tp_pitch"));
                chartStage.setHologram_stageName_x(rs.getDouble("hologram_stageName_x"));
                chartStage.setHologram_stageName_y(rs.getDouble("hologram_stageName_y"));
                chartStage.setHologram_stageName_z(rs.getDouble("hologram_stageName_z"));
                chartStage.setHologram_time_x(rs.getDouble("hologram_time_x"));
                chartStage.setHologram_time_y(rs.getDouble("hologram_time_y"));
                chartStage.setHologram_time_z(rs.getDouble("hologram_time_z"));
                chartStage.setStar_3(rs.getLong("star_time_3"));
                chartStage.setStar_2(rs.getLong("star_time_2"));
                chartStage.setStar_1(rs.getLong("star_time_1"));
            }
            return null;
        });

        chartCheckPointDB.getDB("SELECT * FROM ChartCheckPoint WHERE stage_id = ? ORDER BY point ASC", Arrays.asList(stage_id), rs -> {
            int j = 0;
            while (rs.next()) {
                j++;
                ChartStage.Local_xyz local_xyz = new ChartStage.Local_xyz(rs.getDouble("x"), rs.getDouble("y"), rs.getDouble("z"));
                chartStage.getCheckPoints().add(local_xyz);
            }
            checkPointAmount.put(i,j);
            return null;
        });
        chartStageMap.put(i,chartStage);
        return chartStage;
    }



    public HashMap<Integer, String> getStageNameMap(){return this.stageNameMap;}
    public HashMap<String, Integer> getStartMap(){return this.startMap;}
    public HashMap<String, Integer> getGoalMap(){return this.goalMap;}

    public String getStageName(int i){
        if(stageNameMap.containsKey(i)){
            return stageNameMap.get(i);
        }
        return "";
    }
    public int getStage(String location){
        if(stageMap.containsKey(location)){
            return stageMap.get(location);
        }
        return -1;
    }
    public Location getStageTP(int i){
        if(stageTPMap.containsKey(i)){
            return stageTPMap.get(i);
        }
        return null;
    }
    public int getBackStage(String location){
        if(backStageMap.containsKey(location)){
            return backStageMap.get(location);
        }
        return -1;
    }
    public Location getBackStageTP(int i){
        if(backStageTPMap.containsKey(i)){
            return backStageTPMap.get(i);
        }
        return null;
    }
    public int getStartStage(String location){
        if(startMap.containsKey(location)){
            return startMap.get(location);
        }
        return -1;
    }
    public Location getStartLocation(int i){
        if(startTPMap.containsKey(i)){
            return startTPMap.get(i);
        }
        return null;
    }
    public int getGoalStage(String location){
        if(goalMap.containsKey(location)){
            return goalMap.get(location);
        }
        return -1;
    }
    public Location getGoalLocation(int i){
        if(goalTPMap.containsKey(i)){
            return goalTPMap.get(i);
        }
        return null;
    }
    public String getCheckPoint(String location){
        if(checkPointMap.containsKey(location)){
            return checkPointMap.get(location);
        }
        return "";
    }
    public Location getCheckPointLocation(String s){
        if(checkPointTPMap.containsKey(s)){
            return checkPointTPMap.get(s);
        }
        return null;
    }
    public Long[] getStandardTime(int i){
        if(timeStandardMap.containsKey(i)){
            return timeStandardMap.get(i);
        }
        return null;
    }
    public int getCheckPointAmount(int i){
        if(checkPointAmount.containsKey(i)){
            return checkPointAmount.get(i);
        }
        return 0;
    }

    public int getStarRating(int number, long time) {
        Long[] timeStandard = getStandardTime(number);
        if(timeStandard==null)return -1;
        if(time==0)return 0;
        if (time <= timeStandard[0]) {
            return 3; // スター3
        } else if (time <= timeStandard[1]) {
            return 2; // スター2
        } else if (time <= timeStandard[2]) {
            return 1; // スター1
        } else {
            return 0; // スター0 (基準タイムを全て超えた場合)
        }
    }

    public String getStarString(int star){
        if(star==0)       return org.bukkit.ChatColor.YELLOW + "✧✧✧";
        else if (star==1) return org.bukkit.ChatColor.YELLOW + "✦✧✧";
        else if (star==2) return org.bukkit.ChatColor.YELLOW + "✦✦✧";
        else              return ChatColor.YELLOW + "✦✦✦";
    }

    public String getStringTime(long time){
        return String.format("%02d:%02d:%03d", (time / (1000 * 60)) % 60, (time / 1000) % 60, time % 1000);
    }
    public String[] getStringTimes(Long[] longs){
        String[] strings = new String[3];
        for(int i=0;i<3;i++){
            strings[i] = ChatColor.WHITE + String.format("%02d:%02d:%03d", (longs[i] / (1000 * 60)) % 60, (longs[i] / 1000) % 60, longs[i] % 1000);
        }
        return strings;
    }
    public int getStage_id(String stage_id){
        if (stage_id == null || stage_id.isEmpty())return -1;
        String[] parts = stage_id.split("_");
        if(parts.length != 2)return -1;

        try {
            return (Integer.parseInt(parts[0])-1)*14 +Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public <K, V> K getKey(HashMap<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
