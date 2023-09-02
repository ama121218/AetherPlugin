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
                        //Bukkit.getServer().getLogger().info(entity.getName() + "を削除");
                        entity.remove();
                    }
                }
            }
        }
        List<ChartRankingDB.RankingDataName> list = chartRankingDB.getTop5ScoreName(stage_id);
        int size = list.size();
        Local_xyz l = coordinatesChartTime.get(stage_id);
        if(l==null) return;
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

        String[] chart_name = new String[42];
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

        chart_name[14] = ChatColor.WHITE+"---  ---";
        chart_name[15] = ChatColor.WHITE+"---  ---";
        chart_name[16] = ChatColor.WHITE+"---  ---";
        chart_name[17] = ChatColor.WHITE+"---  ---";
        chart_name[18] = ChatColor.WHITE+"---  ---";
        chart_name[19] = ChatColor.WHITE+"---  ---";
        chart_name[20] = ChatColor.WHITE+"---  ---";
        chart_name[21] = ChatColor.WHITE+"---  ---";
        chart_name[22] = ChatColor.WHITE+"---  ---";
        chart_name[23] = ChatColor.WHITE+"---  ---";
        chart_name[24] = ChatColor.WHITE+"---  ---";
        chart_name[25] = ChatColor.WHITE+"---  ---";
        chart_name[26] = ChatColor.WHITE+"---  ---";
        chart_name[27] = ChatColor.WHITE+"---  ---";

        chart_name[28] = ChatColor.WHITE+"---  ---";
        chart_name[29] = ChatColor.WHITE+"---  ---";
        chart_name[30] = ChatColor.WHITE+"---  ---";
        chart_name[31] = ChatColor.WHITE+"---  ---";
        chart_name[32] = ChatColor.WHITE+"---  ---";
        chart_name[33] = ChatColor.WHITE+"---  ---";
        chart_name[34] = ChatColor.WHITE+"---  ---";
        chart_name[35] = ChatColor.WHITE+"---  ---";
        chart_name[36] = ChatColor.WHITE+"---  ---";
        chart_name[37] = ChatColor.WHITE+"---  ---";
        chart_name[38] = ChatColor.WHITE+"---  ---";
        chart_name[39] = ChatColor.WHITE+"---  ---";
        chart_name[40] = ChatColor.WHITE+"---  ---";
        chart_name[41] = ChatColor.WHITE+"---  ---";


        Local_xyz[] local_xyz_start = new Local_xyz[42];
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

        local_xyz_start[14] = new Local_xyz(-2227.5, 28.5, 1708.5);
        local_xyz_start[15] = new Local_xyz(-2955.5, 79.5, 1620.5);
        local_xyz_start[16] = new Local_xyz(-3634.5, 14.5, 1640.5);
        local_xyz_start[17] = new Local_xyz(-4231.5, 25.5, 1635.5);
        local_xyz_start[18] = new Local_xyz(-4871.5, 80.5, 1650.5);
        local_xyz_start[19] = new Local_xyz(-5449.5, 4.5, 1621.5);
        local_xyz_start[20] = new Local_xyz(-6264.5, 15.5, 1642.5);
        local_xyz_start[21] = new Local_xyz(-6834.5, 4.5, 1643.5);
        local_xyz_start[22] = new Local_xyz(-7419.5, 14.5, 1660.5);
        local_xyz_start[23] = new Local_xyz(-8057.5, 4.5, 1614.5);
        local_xyz_start[24] = new Local_xyz(-8723.5, 4.5, 1622.5);
        local_xyz_start[25] = new Local_xyz(-9492.5, 28.5, 1624.5);
        local_xyz_start[26] = new Local_xyz(-10103.5, 40.5, 1635.5);
        local_xyz_start[27] = new Local_xyz(-10842.5, 33.5, 1645.5);

        local_xyz_start[28] = new Local_xyz(-691.5, 72.5, 1635.5);
        local_xyz_start[29] = new Local_xyz(9.5, 14.5, 1635.5);
        local_xyz_start[30] = new Local_xyz(605.5, 14.5, 1580.5);
        local_xyz_start[31] = new Local_xyz(1278.5, 19.5, 1674.5);
        local_xyz_start[32] = new Local_xyz(1722.5, 16.5, 1561.5);
        local_xyz_start[33] = new Local_xyz(2363.5, 19.5, 1666.5);
        local_xyz_start[34] = new Local_xyz(3071.5, 14.5, 1635.5);
        local_xyz_start[35] = new Local_xyz(3627.5, 13.5, 1507.5);
        local_xyz_start[36] = new Local_xyz(4201.5, 14.5, 1652.5);
        local_xyz_start[37] = new Local_xyz(4741.5, 14.5, 1634.5);
        local_xyz_start[38] = new Local_xyz(5373.5, 14.5, 1566.5);
        local_xyz_start[39] = new Local_xyz(6062.5, 90.5, 1609.5);
        local_xyz_start[40] = new Local_xyz(6556.5, 21.5, 1656.5);
        local_xyz_start[41] = new Local_xyz(7256.5, 14.5, 1792.5);

        Local_xyz[] local_xyz_number = new Local_xyz[42];
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

        local_xyz_number[14] = new Local_xyz(-2229.500,28.50,1710.500);
        local_xyz_number[15] = new Local_xyz(-2953.500,79.50,1622.500);
        local_xyz_number[16] = new Local_xyz(-3632.500,14.50,1643.500);
        local_xyz_number[17] = new Local_xyz(-4229.500,25.50,1638.500);
        local_xyz_number[18] = new Local_xyz(-4868.500,79.50,1653.500);
        local_xyz_number[19] = new Local_xyz(-5447.500,4.50,1623.500);
        local_xyz_number[20] = new Local_xyz(-6263.500,15.50,1644.500);
        local_xyz_number[21] = new Local_xyz(-6833.500,4.50,1645.500);
        local_xyz_number[22] = new Local_xyz(-7418.500,14.50,1662.500);
        local_xyz_number[23] = new Local_xyz(-8056.500,4.50,1616.500);
        local_xyz_number[24] = new Local_xyz(-8722.500,4.50,1624.500);
        local_xyz_number[25] = new Local_xyz(-9491.500,28.50,1626.500);
        local_xyz_number[26] = new Local_xyz(-10101.500,40.50,1638.500);
        local_xyz_number[27] = new Local_xyz(-10839.500,33.50,1647.500);

        local_xyz_number[28] = new Local_xyz(-695.500,73.50,1633.500);
        local_xyz_number[29] = new Local_xyz(8.500,14.50,1633.500);
        local_xyz_number[30] = new Local_xyz(607.500,14.50,1575.500);
        local_xyz_number[31] = new Local_xyz(1277.500,19.50,1677.500);
        local_xyz_number[32] = new Local_xyz(1723.500,16.50,1558.500);
        local_xyz_number[33] = new Local_xyz(2360.500,19.50,1665.500);
        local_xyz_number[34] = new Local_xyz(3069.500,14.50,1632.500);
        local_xyz_number[35] = new Local_xyz(3627.500,13.50,1504.500);
        local_xyz_number[36] = new Local_xyz(4200.500,14.50,1650.500);
        local_xyz_number[37] = new Local_xyz(4740.500,14.50,1632.500);
        local_xyz_number[38] = new Local_xyz(5373.500,14.50,1563.500);
        local_xyz_number[39] = new Local_xyz(6061.500,90.50,1607.500);
        local_xyz_number[40] = new Local_xyz(6553.500,21.50,1654.500);
        local_xyz_number[41] = new Local_xyz(7256.500,14.50,1795.500);


        for(int i=1;i<=42;i++){
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
                    //Bukkit.getServer().getLogger().info(entity.getName() + "を削除");
                    entity.remove();
                }
            }
        }


        for (int i = 1; i <= 42; i++) {

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
