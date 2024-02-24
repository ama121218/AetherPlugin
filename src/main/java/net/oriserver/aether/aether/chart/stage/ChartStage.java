package net.oriserver.aether.aether.chart.stage;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class ChartStage{
    private int stageId;
    private String stageColor;
    private String stageName;
    private double stage_x,stage_y,stage_z;
    private double stage_tp_x,stage_tp_y,stage_tp_z;
    private float stage_tp_yaw,stage_tp_pitch;
    private double back_stage_x,back_stage_y,back_stage_z;
    private double back_stage_tp_x,back_stage_tp_y,back_stage_tp_z;
    private float back_stage_tp_yaw,back_stage_tp_pitch;
    private double start_x,start_y,start_z;
    private double start_tp_x,start_tp_y,start_tp_z;
    private float start_tp_yaw,start_tp_pitch;
    private double goal_x,goal_y,goal_z;
    private double goal_tp_x,goal_tp_y,goal_tp_z;
    private float goal_tp_yaw,goal_tp_pitch;
    private double hologram_stageName_x,hologram_stageName_y,hologram_stageName_z;
    private double hologram_time_x,hologram_time_y,hologram_time_z;
    private LinkedHashSet<Local_xyz> checkPoints = new LinkedHashSet<>();
    private Long star_3,star_2,star_1;

    public int getStageId(){return stageId;}
    public void setStageId(int stageId){this.stageId = stageId;}
    public String getStageColor(){return stageColor;}
    public void setStageColor(String stageColor){this.stageColor = stageColor;}
    public String getStageName(){return stageName;}
    public void setStageName(String stageName){this.stageName = stageName;}
    public double getStage_x(){return stage_x;}
    public void setStage_x(double stage_x){this.stage_x = stage_x;}
    public double getStage_y(){return stage_y;}
    public void setStage_y(double stage_y){this.stage_y = stage_y;}
    public double getStage_z(){return stage_z;}
    public void setStage_z(double stage_z){this.stage_z = stage_z;}
    public double getStage_tp_x(){return stage_tp_x;}
    public void setStage_tp_x(double stage_tp_x){this.stage_tp_x = stage_tp_x;}
    public double getStage_tp_y(){return stage_tp_y;}
    public void setStage_tp_y(double stage_tp_y){this.stage_tp_y = stage_tp_y;}
    public double getStage_tp_z(){return stage_tp_z;}
    public void setStage_tp_z(double stage_tp_z){this.stage_tp_z = stage_tp_z;}
    public float getStage_tp_yaw(){return stage_tp_yaw;}
    public void setStage_tp_yaw(float stage_tp_yaw){this.stage_tp_yaw = stage_tp_yaw;}
    public float getStage_tp_pitch(){return stage_tp_pitch;}
    public void setStage_tp_pitch(float stage_tp_pitch){this.stage_tp_pitch = stage_tp_pitch;}
    public double getBack_stage_x(){return back_stage_x;}
    public void setBack_stage_x(double back_stage_x){this.back_stage_x = back_stage_x;}
    public double getBack_stage_y(){return back_stage_y;}
    public void setBack_stage_y(double back_stage_y){this.back_stage_y = back_stage_y;}
    public double getBack_stage_z() {return back_stage_z;}
    public void setBack_stage_z(double back_stage_z){this.back_stage_z = back_stage_z;}
    public double getBack_stage_tp_x() {return back_stage_tp_x;}
    public void setBack_stage_tp_x(double back_stage_tp_x){this.back_stage_tp_x = back_stage_tp_x;}
    public double getBack_stage_tp_y() {return back_stage_tp_y;}
    public void setBack_stage_tp_y(double back_stage_tp_y){this.back_stage_tp_y = back_stage_tp_y;}
    public double getBack_stage_tp_z() {return back_stage_tp_z;}
    public void setBack_stage_tp_z(double back_stage_tp_z){this.back_stage_tp_z = back_stage_tp_z;}
    public float getBack_stage_tp_yaw() {return back_stage_tp_yaw;}
    public void setBack_stage_tp_yaw(float back_stage_tp_yaw) {this.back_stage_tp_yaw = back_stage_tp_yaw;}
    public float getBack_stage_tp_pitch() {return back_stage_tp_pitch;}
    public void setBack_stage_tp_pitch(float back_stage_tp_pitch) {this.back_stage_tp_pitch = back_stage_tp_pitch;}
    public double getStart_x(){return start_x;}
    public void setStart_x(double start_x){this.start_x = start_x;}
    public double getStart_y(){return start_y;}
    public void setStart_y(double start_y){this.start_y = start_y;}
    public double getStart_z(){return start_z;}
    public void setStart_z(double start_z){this.start_z = start_z;}
    public double getStart_tp_x(){return start_tp_x;}
    public void setStart_tp_x(double start_tp_x){this.start_tp_x = start_tp_x;}
    public double getStart_tp_y(){return start_tp_y;}
    public void setStart_tp_y(double start_tp_y){this.start_tp_y = start_tp_y;}
    public double getStart_tp_z(){return start_tp_z;}
    public void setStart_tp_z(double start_tp_z){this.start_tp_z = start_tp_z;}
    public float getStart_tp_yaw(){return start_tp_yaw;}
    public void setStart_tp_yaw(float start_tp_yaw){this.start_tp_yaw = start_tp_yaw;}
    public float getStart_tp_pitch(){return start_tp_pitch;}
    public void setStart_tp_pitch(float start_tp_pitch){this.start_tp_pitch = start_tp_pitch;}
    public double getGoal_x(){return goal_x;}
    public void setGoal_x(double goal_x){this.goal_x = goal_x;}
    public double getGoal_y(){return goal_y;}
    public void setGoal_y(double goal_y){this.goal_y = goal_y;}
    public double getGoal_z(){return goal_z;}
    public void setGoal_z(double goal_z){this.goal_z = goal_z;}
    public double getGoal_tp_x(){return goal_tp_x;}
    public void setGoal_tp_x(double goal_tp_x){this.goal_tp_x = goal_tp_x;}
    public double getGoal_tp_y(){return goal_tp_y;}
    public void setGoal_tp_y(double goal_tp_y){this.goal_tp_y = goal_tp_y;}
    public double getGoal_tp_z(){return goal_tp_z;}
    public void setGoal_tp_z(double goal_tp_z){this.goal_tp_z = goal_tp_z;}
    public float getGoal_tp_yaw(){return goal_tp_yaw;}
    public void setGoal_tp_yaw(float goal_tp_yaw){this.goal_tp_yaw = goal_tp_yaw;}
    public float getGoal_tp_pitch(){return goal_tp_pitch;}
    public void setGoal_tp_pitch(float goal_tp_pitch){this.goal_tp_pitch = goal_tp_pitch;}
    public double getHologram_stageName_x(){return hologram_stageName_x;}
    public void setHologram_stageName_x(double hologram_stageName_x){this.hologram_stageName_x = hologram_stageName_x;}
    public double getHologram_stageName_y(){return hologram_stageName_y;}
    public void setHologram_stageName_y(double hologram_stageName_y){this.hologram_stageName_y = hologram_stageName_y;}
    public double getHologram_stageName_z(){return hologram_stageName_z;}
    public void setHologram_stageName_z(double hologram_stageName_z){this.hologram_stageName_z = hologram_stageName_z;}
    public double getHologram_time_x(){return hologram_time_x;}
    public void setHologram_time_x(double hologram_time_x){this.hologram_time_x = hologram_time_x;}
    public double getHologram_time_y(){return hologram_time_y;}
    public void setHologram_time_y(double hologram_time_y){this.hologram_time_y = hologram_time_y;}
    public double getHologram_time_z(){return hologram_time_z;}
    public void setHologram_time_z(double hologram_time_z){this.hologram_time_z = hologram_time_z;}
    public LinkedHashSet<Local_xyz> getCheckPoints(){return checkPoints;}
    public Long getStar_3(){return star_3;}
    public void setStar_3(Long star_3){this.star_3 = star_3;}
    public Long getStar_2(){return star_2;}
    public void setStar_2(Long star_2){this.star_2 = star_2;}
    public Long getStar_1(){return star_1;}
    public void setStar_1(Long star_1){this.star_1 = star_1;}
    public Long[] getStarTime(){return new Long[]{star_3,star_2,star_1};}

    public String getStageCoordinateString(){return stage_x+","+stage_y+","+stage_z;}
    public Location getStageTPLocation(){return new Location(Bukkit.getWorld("chart"),stage_tp_x,stage_tp_y,stage_tp_z,stage_tp_yaw,stage_tp_pitch);}
    public String getBackStageCoordinateString(){return back_stage_x+","+back_stage_y+","+back_stage_z;}
    public Location getBackStageTPLocation(){return new Location(Bukkit.getWorld("chart"),back_stage_tp_x,back_stage_tp_y,back_stage_tp_z,back_stage_tp_yaw,back_stage_tp_pitch);}
    public String getStartCoordinateString(){return start_x+","+start_y+","+start_z;}
    public Location getStartTPLocation(){return new Location(Bukkit.getWorld("chart"),start_tp_x,start_tp_y,start_tp_z,start_tp_yaw,start_tp_pitch);}
    public String getGoalCoordinateString(){return goal_x+","+goal_y+","+goal_z;}
    public Location getGoalTPLocation(){return new Location(Bukkit.getWorld("chart"),goal_tp_x,goal_tp_y,goal_tp_z,goal_tp_yaw,goal_tp_pitch);}

    public int getCheckPointAmount(){return checkPoints.size();}
    public ArrayList<String> getCheckPointStrings(){
        ArrayList<String> checkPointStrings = new ArrayList<>();
        for(Local_xyz local_xyz : checkPoints){
            checkPointStrings.add(local_xyz.getX()+","+local_xyz.getY()+","+local_xyz.getZ());
        }
        return checkPointStrings;
    }

    public static class Local_xyz{
        private double x,y,z;
        Local_xyz(double x,double y,double z){this.x = x;this.y = y;this.z = z;}
        public double getX(){return x;}
        public void setX(double x){this.x = x;}
        public double getY(){return y;}
        public void setY(double y){this.y = y;}
        public double getZ(){return z;}
        public void setZ(double z){this.z = z;}
    }
}

