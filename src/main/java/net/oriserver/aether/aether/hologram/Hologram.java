package net.oriserver.aether.aether.hologram;

import net.oriserver.aether.aether.listener.pressurelocation.chart.ChartStartTPLocation;
import net.oriserver.aether.aether.sqlite.ChartRankingDB;
import net.oriserver.aether.aether.sqlite.SQLiteManager;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.List;

public class Hologram {

    private final ChartRankingDB chartRankingDB;
    private final HashMap<Integer, Local_xyz> coordinatesChartTime = new HashMap<Integer, Local_xyz>();
    private final HashMap<Integer, Double[]> chartTimeLocal = new HashMap<Integer, Double[]>();

    public Hologram(ChartRankingDB chartRankingDB){
        this.chartRankingDB = chartRankingDB;

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

        setAllHologram();
    }

    public void setChartTime(int stage_id){
        if(chartTimeLocal.get(stage_id)!=null){
            Double[] doubles = chartTimeLocal.get(stage_id);
            Chunk chunk = Bukkit.getWorld("chart").getChunkAt((int)doubles[0].doubleValue() >> 4, (int)doubles[1].doubleValue() >> 4);
            if (!chunk.isLoaded()) {
                chunk.load();
            }
            for(Entity entity :chunk.getEntities()){
                if (entity.getType() == EntityType.ARMOR_STAND) {
                    Location local = entity.getLocation();
                    if(doubles[0] ==local.getX()&&doubles[1]==local.getZ()) {
                        Bukkit.getServer().getLogger().info(entity.getName() + "を削除");
                        entity.remove();
                    }
                }
            }
        }
        List<ChartRankingDB.RankingDataName> list = chartRankingDB.getTop5ScoreName(stage_id);
        int size = list.size();
        Local_xyz l = coordinatesChartTime.get(stage_id);
        Location location = new Location(Bukkit.getWorld("chart"),l.x,l.y+0.3,l.z);
        Double[] doubles = {location.getX(),location.getZ()};
        chartTimeLocal.put(stage_id,doubles);
        for(int i=0;i<5;i++){
            location.setY(location.getY()-(0.3));
            ArmorStand armorStand = Bukkit.getWorld("chart").spawn(location, ArmorStand.class);
            if(i<size){
                setHologram(armorStand, ChatColor.BOLD+"#"+(i+1)+" "+list.get(i).getplayer_name()+" "+getStringTime(list.get(i).getTime()));
            }else{
                setHologram(armorStand,ChatColor.BOLD+"#"+(i+1)+" "+"--"+" "+getStringTime(0));
            }
        }
    }

    public void setHologram(ArmorStand stand,String setName){
        stand.setGravity(false);
        stand.setCanPickupItems(false);
        stand.setCustomName(setName);
        stand.setVisible(false);
        stand.setCustomNameVisible(true);
        stand.setSmall(true);
    }



    public class Local_xyz{
        double x,y,z;
        Local_xyz(double x,double y,double z){
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    public String getStringTime(long time){
        return String.format("%02d:%02d:%03d", (time / (1000 * 60)) % 60, (time / 1000) % 60, time % 1000);
    }
    public void setAllHologram(){

        String[] chart_name = new String[14];
        chart_name[0] = ChatColor.GREEN+"--- 草の道 ---";
        chart_name[1] = ChatColor.BOLD+"--- 森の隠れ家 ---";
        chart_name[2] = ChatColor.BOLD+"--- 寒い丘 ---";
        chart_name[3] = ChatColor.GOLD+"--- 麦が並ぶ家 ---";
        chart_name[4] = ChatColor.AQUA+"--- 不気味な釣り場 ---";
        chart_name[5] = ChatColor.GRAY+"--- 砂利の谷 ---";
        chart_name[6] = ChatColor.RED+"--- マグマの溜池 ---";
        chart_name[7] = ChatColor.GREEN+"--- 谷間の川 ---";
        chart_name[8] = ChatColor.BOLD+"--- 山間の集落跡 ---";
        chart_name[9] = ChatColor.GRAY+"--- 朱の遺跡 ---";
        chart_name[10] = ChatColor.AQUA+"--- 行水の谷 ---";
        chart_name[11] = ChatColor.BLUE+"--- 金属結晶の洞窟 ---";
        chart_name[12] = ChatColor.LIGHT_PURPLE+"--- 泥中の蓮 ---";
        chart_name[13] = ChatColor.DARK_PURPLE+"--- 沼地の館 ---";

        Local_xyz[] local_xyz_start = new Local_xyz[14];
        local_xyz_start[0] = new Local_xyz(-1579.5, 11.5, 2074.5);
        local_xyz_start[1] = new Local_xyz(-1545.5, 4.5, 2425.5);
        local_xyz_start[2] = new Local_xyz(-1543.5, 14.5, 2674.5);
        local_xyz_start[3] = new Local_xyz(-1535.5, 14.5, 2998.5);
        local_xyz_start[4] = new Local_xyz(-1584.5, 16.5, 3284.5);
        local_xyz_start[5] = new Local_xyz(-1563.5, 4.5, 3561.5);
        local_xyz_start[6] = new Local_xyz(-1556.5, 16.5, 3889.5);
        local_xyz_start[7] = new Local_xyz(-1553.5, 31.5, 4160.5);
        local_xyz_start[8] = new Local_xyz(-1608.5, 16.5, 4644.5);
        local_xyz_start[9] = new Local_xyz(-1568.5, 5.5, 4756.5);
        local_xyz_start[10] = new Local_xyz(-1531.5, 23.5, 5065.5);
        local_xyz_start[11] = new Local_xyz(-1551.5, 60.5, 5421.5);
        local_xyz_start[12] = new Local_xyz(-1581.5, 15.5, 5672.5);
        local_xyz_start[13] = new Local_xyz(-1591.5, 24.5, 6162.5);

        Local_xyz[] local_xyz_number = new Local_xyz[14];
        local_xyz_number[0] = new Local_xyz(-1577.500, 11.500, 2071.500);
        local_xyz_number[1] = new Local_xyz(-1544.500, 4.500, 2427.500);
        local_xyz_number[2] = new Local_xyz(-1541.500, 14.500, 2674.500);
        local_xyz_number[3] = new Local_xyz(-1534.500, 14.500, 3000.500);
        local_xyz_number[4] = new Local_xyz(-1582.500, 16.500, 3284.500);
        local_xyz_number[5] = new Local_xyz(-1561.500, 4.500, 3560.500);
        local_xyz_number[6] = new Local_xyz(-1555.500, 16.500, 3891.500);
        local_xyz_number[7] = new Local_xyz(-1554.500, 31.500, 4158.500);
        local_xyz_number[8] = new Local_xyz(-1609.500, 16.500, 4642.500);
        local_xyz_number[9] = new Local_xyz(-1566.500, 5.500, 4755.500);
        local_xyz_number[10] = new Local_xyz(-1529.500, 23.500, 5064.500);
        local_xyz_number[11] = new Local_xyz(-1550.500, 60.500, 5423.500);
        local_xyz_number[12] = new Local_xyz(-1579.500, 15.500, 5671.500);
        local_xyz_number[13] = new Local_xyz(-1589.500, 24.500, 6161.500);

        for(int i=1;i<=14;i++){
            World world = Bukkit.getWorld("chart");
            int chunkX = (int) local_xyz_start[i - 1].x>> 4;
            int chunkZ = (int) local_xyz_start[i - 1].z >> 4;
            for (int dx = -1; dx <= 1; dx++) {
                for (int dz = -1; dz <= 1; dz++) {
                    Chunk chunk = world.getChunkAt(chunkX + dx, chunkZ + dz);
                    if (!chunk.isLoaded()) {
                        chunk.load();
                    }
                }
            }
            for (Entity entity : Bukkit.getWorld("chart").getEntities()) {
                if (entity.getType() == EntityType.ARMOR_STAND) {
                    Bukkit.getServer().getLogger().info(entity.getName() + "を削除");
                    entity.remove();
                }
            }
        }


        for (int i = 1; i <= 14; i++) {

            int mainStage = (i - 1) / 14 + 1; // 14で割った結果+1がメインステージになります。
            int subStage = (i - 1) % 14 + 1; // 14で割った余りがサブステージになります。
            String stage_id =  ChatColor.BOLD + "* " + mainStage + " - " + subStage + " *";
            String stage_name = chart_name[i-1];
            String start = ChatColor.GOLD+"|||"+ChatColor.WHITE+""+ChatColor.BOLD+"start"+ChatColor.GOLD+"|||";

            Location location_number = new Location(Bukkit.getWorld("chart"),local_xyz_number[i-1].x,local_xyz_number[i-1].y,local_xyz_number[i-1].z);
            Location location_name = new Location(Bukkit.getWorld("chart"),local_xyz_number[i-1].x,local_xyz_number[i-1].y-0.3,local_xyz_number[i-1].z);
            Location location_start = new Location(Bukkit.getWorld("chart"),local_xyz_start[i-1].x,local_xyz_start[i-1].y,local_xyz_start[i-1].z);

            ArmorStand armorStand1 = Bukkit.getWorld("chart").spawn(location_number, ArmorStand.class);
            setHologram(armorStand1, stage_id);
            ArmorStand armorStand2 = Bukkit.getWorld("chart").spawn(location_name, ArmorStand.class);
            setHologram(armorStand2, stage_name);
            ArmorStand armorStand3 = Bukkit.getWorld("chart").spawn(location_start, ArmorStand.class);
            setHologram(armorStand3, start);

            setChartTime(i);
        }





    }



}
