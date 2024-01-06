package net.oriserver.aether.aether.chart.stage;


import net.oriserver.aether.aether.sqlite.SQLiteAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.Hash;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class ChartStageInfo {

    private final SQLiteAPI chartStageDB;
    private final SQLiteAPI chartCheckPointDB;


    private final HashMap<Integer,String> stageNameMap = new HashMap<>();
    private final HashMap<Integer,Location> stageTPMap = new HashMap<>();
    private final HashMap<String, Integer> startMap = new HashMap<>();
    private final HashMap<Integer, Location> startTPMap = new HashMap<>();
    private final HashMap<String, Integer> goalMap = new HashMap<>();
    private final HashMap<Integer, Location> goalTPMap = new HashMap<>();
    private final HashMap<String, String> checkPointMap = new HashMap<>();
    private final HashMap<String, Location> checkPointTPMap = new HashMap<>();
    private final HashMap<Integer,Integer> checkPointAmount = new HashMap<>();
    private final HashMap<Integer, Long[]> timeStandardMap = new HashMap<>();

    public ChartStageInfo(SQLiteAPI chartStageDB,SQLiteAPI chartCheckPointDB){
        this.chartStageDB = chartStageDB;
        this.chartCheckPointDB = chartCheckPointDB;
        setAllMap();
    }

    public void setAllMap(){
        stageNameMap.clear();
        stageTPMap.clear();
        startMap.clear();
        startTPMap.clear();
        goalMap.clear();
        goalTPMap.clear();
        checkPointMap.clear();
        checkPointTPMap.clear();
        timeStandardMap.clear();

        for(int i=1;i<=56;i++){
            int main_page = (i/14)+1;
            int sub_page = i%14;
            String stage_id = main_page+"_"+sub_page;
            final int index = i;
            chartStageDB.getDB("SELECT * FROM ChartStage WHERE stage_id = ?", Arrays.asList(stage_id), rs -> {
                while (rs.next()) {
                    stageNameMap.put(index,rs.getString("stage_name"));
                    {
                        Location location = new Location(Bukkit.getWorld("chart"), rs.getDouble("stage_tp_x"), rs.getDouble("stage_tp_y"),
                            rs.getDouble("stage_tp_z"), rs.getFloat("stage_tp_yaw"), rs.getFloat("stage_tp_pitch")
                        );
                        stageTPMap.put(index, location);
                    }
                    String start = rs.getString("start_x")+","+rs.getString("start_y")+","+rs.getString("start_z");
                    startMap.put(start,index);
                    {
                        Location location = new Location(Bukkit.getWorld("chart"), rs.getDouble("start_tp_x"), rs.getDouble("start_tp_y"),
                            rs.getDouble("start_tp_z"), rs.getFloat("start_tp_yaw"), rs.getFloat("start_tp_pitch")
                        );
                        startTPMap.put(index, location);
                    }
                    String goal = rs.getString("goal_x")+","+rs.getString("goal_y")+","+rs.getString("goal_z");
                    goalMap.put(goal,index);
                    {
                        Location location = new Location(Bukkit.getWorld("chart"), rs.getDouble("goal_tp_x"), rs.getDouble("goal_tp_y"),
                                rs.getDouble("goal_tp_z"), rs.getFloat("goal_tp_yaw"), rs.getFloat("goal_tp_pitch")
                        );
                        goalTPMap.put(index, location);
                    }
                    Long[] longs = {rs.getLong("star_time_3"),rs.getLong("star_time_2"),rs.getLong("star_time_1")};
                    timeStandardMap.put(index,longs);
                }
                return null;
            });

            chartCheckPointDB.getDB("SELECT * FROM ChartCheckPoint WHERE stage_id = ? ORDER BY point ASC", Arrays.asList(stage_id), rs -> {
                int j = 1;
                while (rs.next()) {
                    checkPointMap.put(rs.getString("x")+","+rs.getString("y")+","+rs.getString("z"),index+"_"+j);
                    checkPointTPMap.put(index+"_"+j,new Location(Bukkit.getWorld("chart"),rs.getDouble("x")+0.5,rs.getDouble("y"),rs.getDouble("z")+0.5));
                    j++;
                }
                checkPointAmount.put(index,j);
                return null;
            });
        }

        List<Map.Entry<Integer, Location>> entries = new ArrayList<>(stageTPMap.entrySet());
        for(Map.Entry entry:entries){
            Location l = (Location) entry.getValue();
            Bukkit.getServer().getLogger().info("Key: " + entry.getKey()+" value: "+l.getX()+" "+l.getY()+" "+l.getZ());
        }
    }

    public void setIndexMap(int i){
        if(i == -1)return;
        int main_page = (i/14)+1;
        int sub_page = i%14;
        String stage_id = main_page+"_"+sub_page;
        final int index = i;
        chartStageDB.getDB("SELECT * FROM ChartStage WHERE stage_id = ?", Arrays.asList(stage_id), rs -> {
            while (rs.next()) {
                stageNameMap.put(index,rs.getString("stage_name"));
                {
                    Location location = new Location(Bukkit.getWorld("chart"), rs.getDouble("stage_tp_x"), rs.getDouble("stage_tp_y"),
                            rs.getDouble("stage_tp_z"), rs.getFloat("stage_tp_yaw"), rs.getFloat("stage_tp_pitch")
                    );
                    stageTPMap.put(index, location);
                }
                String start = rs.getString("start_x")+","+rs.getString("start_y")+","+rs.getString("start_z");
                startMap.put(start,index);
                {
                    Location location = new Location(Bukkit.getWorld("chart"), rs.getDouble("start_tp_x"), rs.getDouble("start_tp_y"),
                            rs.getDouble("start_tp_z"), rs.getFloat("start_tp_yaw"), rs.getFloat("start_tp_pitch")
                    );
                    startTPMap.put(index, location);
                }
                String goal = rs.getString("goal_x")+","+rs.getString("goal_y")+","+rs.getString("goal_z");
                goalMap.put(goal,index);
                {
                    Location location = new Location(Bukkit.getWorld("chart"), rs.getDouble("goal_tp_x"), rs.getDouble("goal_tp_y"),
                            rs.getDouble("goal_tp_z"), rs.getFloat("goal_tp_yaw"), rs.getFloat("goal_tp_pitch")
                    );
                    goalTPMap.put(index, location);
                }
                Long[] longs = {rs.getLong("star_time_3"),rs.getLong("star_time_2"),rs.getLong("star_time_1")};
                timeStandardMap.put(index,longs);
            }
            return null;
        });

        chartCheckPointDB.getDB("SELECT * FROM ChartCheckPoint WHERE stage_id = ? ORDER BY point ASC", Arrays.asList(stage_id), rs -> {
            int j = 1;
            while (rs.next()) {
                checkPointMap.put(rs.getString("x")+","+rs.getString("y")+","+rs.getString("z"),index+"_"+j);
                checkPointTPMap.put(index+"_"+j,new Location(Bukkit.getWorld("chart"),rs.getDouble("x")+0.5,rs.getDouble("y"),rs.getDouble("z")+0.5));
                j++;
            }
            checkPointAmount.put(i,j);
            return null;
        });
    }


    public String getStageName(int i){
        if(stageNameMap.containsKey(i)){
            return stageNameMap.get(i);
        }
        return "";
    }
    public Location getStageTP(int i){
        if(stageTPMap.containsKey(i)){
            return stageTPMap.get(i);
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
        return -1;
    }

    public int getStarRating(int number, long time) {
        Long[] timeStandard = getStandardTime(number);
        if(timeStandard==null)return -1;
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

}
