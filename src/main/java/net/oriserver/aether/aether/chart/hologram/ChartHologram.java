package net.oriserver.aether.aether.chart.hologram;

import net.oriserver.aether.aether.chart.ChartRankingDB;
import net.oriserver.aether.aether.chart.stage.ChartStageInfo;
import net.oriserver.aether.aether.statics.CommonMethods;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.*;

public class ChartHologram {

    private final ChartRankingDB chartRankingDB;
    private final HashMap<Integer, Local_xyz> coordinatesChartTime = new HashMap<Integer, Local_xyz>();
    private final HashMap<Integer, Local_xyz> coordinatesChartNumber = new HashMap<>();
    private final HashMap<Integer, ChatColor> stageNameColor = new HashMap<>();
    private final ChartStageInfo chartStageInfo;

    public ChartHologram(ChartRankingDB chartRankingDB, ChartStageInfo chartStageInfo){
        this.chartStageInfo = chartStageInfo;
        this.chartRankingDB = chartRankingDB;
        putData();
        setAllHologram();
    }

    public void setChartTime(int stage_id){
        if(coordinatesChartTime.containsKey(stage_id)){
            Local_xyz local_xyz = coordinatesChartTime.get(stage_id);
            Location local = new Location(Bukkit.getWorld("chart"),local_xyz.x,local_xyz.y,local_xyz.z);
            CommonMethods.deleteHologram(local);
            List<ChartRankingDB.RankingDataName> list = chartRankingDB.getTop5ScoreName(stage_id);
            Location location = new Location(Bukkit.getWorld("chart"),local_xyz.x,local_xyz.y+0.3,local_xyz.z);
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
    public class Local_xyz{
        double x,y,z;
        Local_xyz(double x,double y,double z){this.x = x;this.y = y;this.z = z;}
    }
    public String getStringTime(long time){
        return String.format("%02d:%02d:%03d", (time / (1000 * 60)) % 60, (time / 1000) % 60, time % 1000);
    }
    public void setAllHologram(){

        Local_xyz[] local_xyz_start = new Local_xyz[56];
        Local_xyz[] local_xyz_goal = new Local_xyz[56];
        HashMap<String,Integer> startMap = chartStageInfo.getStartMap();
        HashMap<String,Integer> goalMap = chartStageInfo.getGoalMap();
        for(String locationString :startMap.keySet()){
            String[] doublesString = locationString.split(",");
            double[] doubles = {Double.parseDouble(doublesString[0]),Double.parseDouble(doublesString[1]),Double.parseDouble(doublesString[2])};
            local_xyz_start[startMap.get(locationString)-1] = new Local_xyz(doubles[0]+0.5,doubles[1]+0.3,doubles[2]+0.5);
        }
        for(String locationString :goalMap.keySet()){
            String[] doublesString = locationString.split(",");
            double[] doubles = {Double.parseDouble(doublesString[0]),Double.parseDouble(doublesString[1]),Double.parseDouble(doublesString[2])};
            local_xyz_goal[goalMap.get(locationString)-1] = new Local_xyz(doubles[0]+0.5,doubles[1]+0.3,doubles[2]+0.5);
        }
        for(int i=1;i<=56;i++){//delete
            if(local_xyz_start[i-1]!=null) {
                Local_xyz local_xyz = local_xyz_start[i-1];
                Location location = new Location(Bukkit.getWorld("chart"), local_xyz.x, local_xyz.y, local_xyz.z);
                CommonMethods.deleteHologram(location);
            }
            if(local_xyz_goal[i-1]!=null) {
                Local_xyz local_xyz = local_xyz_goal[i-1];
                Location location = new Location(Bukkit.getWorld("chart"), local_xyz.x, local_xyz.y, local_xyz.z);
                CommonMethods.deleteHologram(location);
            }
            if(coordinatesChartNumber.containsKey(i)){
                Local_xyz local_xyz = coordinatesChartNumber.get(i);
                Location location = new Location(Bukkit.getWorld("chart"), local_xyz.x, local_xyz.y, local_xyz.z);
                CommonMethods.deleteHologram(location);
            }
            int count = chartStageInfo.getCheckPointAmount(i);
            for(int j=0;j<count;j++) {
                String checkPointString = i +"_" + j;
                if(chartStageInfo.getGoalMap().containsKey(checkPointString)){
                    Location location = chartStageInfo.getCheckPointLocation(checkPointString).clone();
                    location.setX(location.getX()+0.5);
                    location.setY(location.getY()+0.3);
                    location.setZ(location.getZ()+0.5);
                    CommonMethods.deleteHologram(location);
                }
            }
        }
        for (int i = 1; i <= 56; i++) {//spawn
            int mainStage = (i - 1) / 14 + 1;//main
            int subStage = (i - 1) % 14 + 1;//sub

            if(local_xyz_start[i-1]!=null) {
                Location location_start = new Location(Bukkit.getWorld("chart"),local_xyz_start[i-1].x,local_xyz_start[i-1].y,local_xyz_start[i-1].z);
                CommonMethods.setHologram(location_start, ChatColor.GOLD+"|||"+ChatColor.WHITE+""+ChatColor.BOLD+"start"+ChatColor.GOLD+"|||");
            }
            if(local_xyz_goal[i-1]!=null) {
                Location location_start = new Location(Bukkit.getWorld("chart"),local_xyz_goal[i-1].x,local_xyz_goal[i-1].y,local_xyz_goal[i-1].z);
                CommonMethods.setHologram(location_start, ChatColor.GOLD+"|||"+ChatColor.WHITE+""+ChatColor.BOLD+"goal"+ChatColor.GOLD+"|||");
            }
            if(coordinatesChartNumber.containsKey(i)){
                Local_xyz local_xyz = coordinatesChartNumber.get(i);
                Location location = new Location(Bukkit.getWorld("chart"), local_xyz.x, local_xyz.y, local_xyz.z);
                CommonMethods.setHologram(location, ChatColor.BOLD + "* " + mainStage + " - " + subStage + " *");
                location.setY(local_xyz.y-0.3);
                CommonMethods.setHologram(location, stageNameColor.get(i)+"--- "+chartStageInfo.getStageName(i)+" ---");
            }
            setChartTime(i);
            int count = chartStageInfo.getCheckPointAmount(i);
            Bukkit.getServer().getLogger().info(""+count);
            for(int j=0;j<count;j++) {
                String checkPointString = i +"_" + j;
                if(chartStageInfo.getGoalMap().containsKey(checkPointString)){
                    Location location = chartStageInfo.getCheckPointLocation(checkPointString).clone();
                    location.setX(location.getX()+0.5);
                    location.setY(location.getY()+0.3);
                    location.setZ(location.getZ()+0.5);
                    CommonMethods.setHologram(location, "*CheckPoint*");
                }
            }
        }
    }

    public void setCheckPointWhenCreateStage(Double[] doubles){
        Location location = new Location(Bukkit.getWorld("chart"),doubles[0]+0.5,doubles[1]+0.3,doubles[2]+0.5);
        CommonMethods.setHologram(location,"*CheckPoint*");
    }

    public void setStartWhenCreateStage(String locationString){
        String[] doublesString = locationString.split(",");
        double[] doubles = {Double.parseDouble(doublesString[0]),Double.parseDouble(doublesString[1]),Double.parseDouble(doublesString[2])};
        Location location = new Location(Bukkit.getWorld("chart"),doubles[0]+0.5,doubles[1]+0.3,doubles[2]+0.5);
        CommonMethods.setHologram(location,ChatColor.GOLD+"|||"+ChatColor.WHITE+""+ChatColor.BOLD+"start"+ChatColor.GOLD+"|||");
    }
    public void setGaolWhenCreateStage(String locationString){
        String[] doublesString = locationString.split(",");
        double[] doubles = {Double.parseDouble(doublesString[0]),Double.parseDouble(doublesString[1]),Double.parseDouble(doublesString[2])};
        Location location = new Location(Bukkit.getWorld("chart"),doubles[0]+0.5,doubles[1]+0.3,doubles[2]+0.5);
        CommonMethods.setHologram(location,ChatColor.GOLD+"|||"+ChatColor.WHITE+""+ChatColor.BOLD+"goal"+ChatColor.GOLD+"|||");
    }

    public void deleteWhenCreateStage(String locationString){
        if(locationString==null)return;
        String[] doublesString = locationString.split(",");
        double[] doubles = {Double.parseDouble(doublesString[0]),Double.parseDouble(doublesString[1]),Double.parseDouble(doublesString[2])};
        Location location = new Location(Bukkit.getWorld("chart"),doubles[0]+0.5,doubles[1]+0.3,doubles[2]+0.5);
        CommonMethods.deleteHologram(location);
    }
    public void deleteOldStageName(int i){
        Local_xyz local_xyz = coordinatesChartNumber.get(i);
        Location location = new Location(Bukkit.getWorld("chart"),local_xyz.x,local_xyz.y,local_xyz.z);
        CommonMethods.deleteHologram(location);
    }

    public void deleteOldCheckPoint(ArrayList<Double[]> checkPoint_list){
        if(checkPoint_list==null)return;
        for(Double[] doubles:checkPoint_list) {
            Location location = new Location(Bukkit.getWorld("chart"), doubles[0]+0.5,doubles[1]+0.3,doubles[2]+0.5);
            CommonMethods.deleteHologram(location);
        }
    }

    private void putData(){
        coordinatesChartTime.put(1, new Local_xyz(-1583.5, 11.75, 2071.5));
        coordinatesChartTime.put(2, new Local_xyz(-1543.5, 4.75, 2422.5));
        coordinatesChartTime.put(3, new Local_xyz(-1543.5, 14.75, 2671.5));
        coordinatesChartTime.put(4, new Local_xyz(-1532.5, 14.75, 2996.0));
        coordinatesChartTime.put(5, new Local_xyz(-1584.5, 16.75, 3281.5));
        coordinatesChartTime.put(6, new Local_xyz(-1566.0, 4.75, 3559.5));
        coordinatesChartTime.put(7, new Local_xyz(-1554.5, 16.75, 3887.0));
        coordinatesChartTime.put(8, new Local_xyz(-1557.5, 31.75, 4163.5));
        coordinatesChartTime.put(9, new Local_xyz(-1611.5, 16.75, 4646.5));
        coordinatesChartTime.put(10, new Local_xyz(-1570.5, 5.75, 4753.5));
        coordinatesChartTime.put(11, new Local_xyz(-1535.5, 23.75, 5062.0));
        coordinatesChartTime.put(12, new Local_xyz(-1549.5, 60.75, 5418.5));
        coordinatesChartTime.put(13, new Local_xyz(-1585.5, 15.75, 5668.5));
        coordinatesChartTime.put(14, new Local_xyz(-1593.0, 24.75, 6161.0));

        coordinatesChartTime.put(15, new Local_xyz(-2224.500,28.75,1711.500));
        coordinatesChartTime.put(16, new Local_xyz(-2953.500,79.75,1617.500));
        coordinatesChartTime.put(17, new Local_xyz(-3630.500,14.75,1636.500));
        coordinatesChartTime.put(18, new Local_xyz(-4228.500,25.75,1631.500));
        coordinatesChartTime.put(19, new Local_xyz(-4868.500,79.75,1646.500));
        coordinatesChartTime.put(20, new Local_xyz(-5447.500,4.75,1618.500));
        coordinatesChartTime.put(21, new Local_xyz(-6262.500,15.75,1639.500));
        coordinatesChartTime.put(22, new Local_xyz(-6832.500,4.75,1641.500));
        coordinatesChartTime.put(23, new Local_xyz(-7417.500,14.75,1657.500));
        coordinatesChartTime.put(24, new Local_xyz(-8055.500,4.75,1611.500));
        coordinatesChartTime.put(25, new Local_xyz(-8721.500,4.75,1619.500));
        coordinatesChartTime.put(26, new Local_xyz(-9490.500,28.75,1621.500));
        coordinatesChartTime.put(27, new Local_xyz(-10098.500,40.75,1631.500));
        coordinatesChartTime.put(28, new Local_xyz(-10839.500,33.75,1643.500));

        coordinatesChartTime.put(29, new Local_xyz(-695.500,73.75,1637.500));
        coordinatesChartTime.put(30, new Local_xyz(6.500,14.75,1638.500));
        coordinatesChartTime.put(31, new Local_xyz(603.500,14.75,1575.500));
        coordinatesChartTime.put(32, new Local_xyz(1284.500,19.75,1673.500));
        coordinatesChartTime.put(33, new Local_xyz(1717.500,16.75,1562.500));
        coordinatesChartTime.put(34, new Local_xyz(2364.500,19.75,1669.500));
        coordinatesChartTime.put(35, new Local_xyz(3069.500,14.75,1638.500));
        coordinatesChartTime.put(36, new Local_xyz(3624.500,13.75,1507.500));
        coordinatesChartTime.put(37, new Local_xyz(4199.500,14.75,1654.500));
        coordinatesChartTime.put(38, new Local_xyz(4739.500,14.75,1636.500));
        coordinatesChartTime.put(39, new Local_xyz(5370.500,14.75,1566.500));
        coordinatesChartTime.put(40, new Local_xyz(6060.500,90.75,1612.500));
        coordinatesChartTime.put(41, new Local_xyz(6552.500,21.75,1658.500));
        coordinatesChartTime.put(42, new Local_xyz(7259.500,14.75,1792.500));


        coordinatesChartNumber.put(1,new Local_xyz(-1577.500, 11.500, 2071.500));
        coordinatesChartNumber.put(2,new Local_xyz(-1544.500, 4.500, 2427.500));
        coordinatesChartNumber.put(3,new Local_xyz(-1541.500, 14.500, 2674.500));
        coordinatesChartNumber.put(4,new Local_xyz(-1534.500, 14.500, 3000.500));
        coordinatesChartNumber.put(5,new Local_xyz(-1582.500, 16.500, 3284.500));
        coordinatesChartNumber.put(6,new Local_xyz(-1561.500, 4.500, 3560.500));
        coordinatesChartNumber.put(7,new Local_xyz(-1555.500, 16.500, 3891.500));
        coordinatesChartNumber.put(8,new Local_xyz(-1554.500, 31.500, 4158.500));
        coordinatesChartNumber.put(9,new Local_xyz(-1609.500, 16.500, 4642.500));
        coordinatesChartNumber.put(10,new Local_xyz(-1566.500, 5.500, 4755.500));
        coordinatesChartNumber.put(11,new Local_xyz(-1529.500, 23.500, 5064.500));
        coordinatesChartNumber.put(12,new Local_xyz(-1550.500, 60.500, 5423.500));
        coordinatesChartNumber.put(13,new Local_xyz(-1579.500, 15.500, 5671.500));
        coordinatesChartNumber.put(14,new Local_xyz(-1589.500, 24.500, 6161.500));

        coordinatesChartNumber.put(15,new Local_xyz(-2229.500,28.50,1710.500));
        coordinatesChartNumber.put(16,new Local_xyz(-2953.500,79.50,1622.500));
        coordinatesChartNumber.put(17,new Local_xyz(-3632.500,14.50,1643.500));
        coordinatesChartNumber.put(18,new Local_xyz(-4229.500,25.50,1638.500));
        coordinatesChartNumber.put(19,new Local_xyz(-4868.500,79.50,1653.500));
        coordinatesChartNumber.put(20,new Local_xyz(-5447.500,4.50,1623.500));
        coordinatesChartNumber.put(21,new Local_xyz(-6263.500,15.50,1644.500));
        coordinatesChartNumber.put(22,new Local_xyz(-6833.500,4.50,1645.500));
        coordinatesChartNumber.put(23,new Local_xyz(-7418.500,14.50,1662.500));
        coordinatesChartNumber.put(24,new Local_xyz(-8056.500,4.50,1616.500));
        coordinatesChartNumber.put(25,new Local_xyz(-8722.500,4.50,1624.500));
        coordinatesChartNumber.put(26,new Local_xyz(-9491.500,28.50,1626.500));
        coordinatesChartNumber.put(27,new Local_xyz(-10101.500,40.50,1638.500));
        coordinatesChartNumber.put(28,new Local_xyz(-10839.500,33.50,1647.500));

        coordinatesChartNumber.put(29,new Local_xyz(-695.500,73.50,1633.500));
        coordinatesChartNumber.put(30,new Local_xyz(8.500,14.50,1633.500));
        coordinatesChartNumber.put(31,new Local_xyz(607.500,14.50,1575.500));
        coordinatesChartNumber.put(32,new Local_xyz(1277.500,19.50,1677.500));
        coordinatesChartNumber.put(33,new Local_xyz(1723.500,16.50,1558.500));
        coordinatesChartNumber.put(34,new Local_xyz(2360.500,19.50,1665.500));
        coordinatesChartNumber.put(35,new Local_xyz(3069.500,14.50,1632.500));
        coordinatesChartNumber.put(36,new Local_xyz(3627.500,13.50,1504.500));
        coordinatesChartNumber.put(37,new Local_xyz(4200.500,14.50,1650.500));
        coordinatesChartNumber.put(38,new Local_xyz(4740.500,14.50,1632.500));
        coordinatesChartNumber.put(39,new Local_xyz(5373.500,14.50,1563.500));
        coordinatesChartNumber.put(40,new Local_xyz(6061.500,90.50,1607.500));
        coordinatesChartNumber.put(41,new Local_xyz(6553.500,21.50,1654.500));
        coordinatesChartNumber.put(42,new Local_xyz(7256.500,14.50,1795.500));

        coordinatesChartNumber.put(43,new Local_xyz(725.500,14.50,1795.500));
        coordinatesChartNumber.put(44,new Local_xyz(725.500,14.50,1795.500));
        coordinatesChartNumber.put(45,new Local_xyz(725.500,14.50,1795.500));
        coordinatesChartNumber.put(46,new Local_xyz(725.500,14.50,1795.500));
        coordinatesChartNumber.put(47,new Local_xyz(725.500,14.50,1795.500));
        coordinatesChartNumber.put(48,new Local_xyz(725.500,14.50,1795.500));
        coordinatesChartNumber.put(49,new Local_xyz(725.500,14.50,1795.500));
        coordinatesChartNumber.put(50,new Local_xyz(725.500,14.50,1795.500));
        coordinatesChartNumber.put(51,new Local_xyz(725.500,14.50,1795.500));
        coordinatesChartNumber.put(52,new Local_xyz(725.500,14.50,1795.500));
        coordinatesChartNumber.put(53,new Local_xyz(725.500,14.50,1795.500));
        coordinatesChartNumber.put(54,new Local_xyz(725.500,14.50,1795.500));
        coordinatesChartNumber.put(55,new Local_xyz(725.500,14.50,1795.500));
        coordinatesChartNumber.put(56,new Local_xyz(725.500,14.50,1795.500));


        stageNameColor.put(1,ChatColor.GREEN);
        stageNameColor.put(2,ChatColor.BOLD);
        stageNameColor.put(3,ChatColor.BOLD);
        stageNameColor.put(4,ChatColor.GOLD);
        stageNameColor.put(5,ChatColor.AQUA);
        stageNameColor.put(6,ChatColor.GRAY);
        stageNameColor.put(7,ChatColor.RED);
        stageNameColor.put(8,ChatColor.GREEN);
        stageNameColor.put(9,ChatColor.BOLD);
        stageNameColor.put(10,ChatColor.GRAY);
        stageNameColor.put(11,ChatColor.AQUA);
        stageNameColor.put(12,ChatColor.BLUE);
        stageNameColor.put(13,ChatColor.LIGHT_PURPLE);
        stageNameColor.put(14,ChatColor.DARK_PURPLE);

        stageNameColor.put(15,ChatColor.WHITE);
        stageNameColor.put(16,ChatColor.WHITE);
        stageNameColor.put(17,ChatColor.WHITE);
        stageNameColor.put(18,ChatColor.WHITE);
        stageNameColor.put(19,ChatColor.WHITE);
        stageNameColor.put(20,ChatColor.WHITE);
        stageNameColor.put(21,ChatColor.WHITE);
        stageNameColor.put(22,ChatColor.WHITE);
        stageNameColor.put(23,ChatColor.WHITE);
        stageNameColor.put(24,ChatColor.WHITE);
        stageNameColor.put(25,ChatColor.WHITE);
        stageNameColor.put(26,ChatColor.WHITE);
        stageNameColor.put(27,ChatColor.WHITE);
        stageNameColor.put(28,ChatColor.WHITE);

        stageNameColor.put(29,ChatColor.WHITE);
        stageNameColor.put(30,ChatColor.WHITE);
        stageNameColor.put(31,ChatColor.WHITE);
        stageNameColor.put(32,ChatColor.WHITE);
        stageNameColor.put(33,ChatColor.WHITE);
        stageNameColor.put(34,ChatColor.WHITE);
        stageNameColor.put(35,ChatColor.WHITE);
        stageNameColor.put(36,ChatColor.WHITE);
        stageNameColor.put(37,ChatColor.WHITE);
        stageNameColor.put(38,ChatColor.WHITE);
        stageNameColor.put(39,ChatColor.WHITE);
        stageNameColor.put(40,ChatColor.WHITE);
        stageNameColor.put(41,ChatColor.WHITE);
        stageNameColor.put(42,ChatColor.WHITE);

        stageNameColor.put(43,ChatColor.WHITE);
        stageNameColor.put(44,ChatColor.WHITE);
        stageNameColor.put(45,ChatColor.WHITE);
        stageNameColor.put(46,ChatColor.WHITE);
        stageNameColor.put(47,ChatColor.WHITE);
        stageNameColor.put(48,ChatColor.WHITE);
        stageNameColor.put(49,ChatColor.WHITE);
        stageNameColor.put(50,ChatColor.WHITE);
        stageNameColor.put(51,ChatColor.WHITE);
        stageNameColor.put(52,ChatColor.WHITE);
        stageNameColor.put(53,ChatColor.WHITE);
        stageNameColor.put(54,ChatColor.WHITE);
        stageNameColor.put(55,ChatColor.WHITE);
        stageNameColor.put(56,ChatColor.WHITE);
    }

}
