package net.oriserver.aether.aether.chart;

import net.oriserver.aether.aether.sqlite.SQLiteAPI;

import java.util.ArrayList;

public class TemporaryData {

    SQLiteAPI chartDB;

    TemporaryData(SQLiteAPI chartDB) {
        this.chartDB = chartDB;


        ArrayList<String> data = new ArrayList<>();

        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('1_1', '草の道', -1579.5, 11.001, 2068.5, 0, 0, -1580.0,11.0,2074.0, -1579.500, 11.001, 2078.500,0,20, -1558.0,11.0,2268.0, -1575.500,100.100,1649.500,74,11, 40000, 60000,120000 )");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('1_2', '森の隠れ家', -1539.5, 4.001, 2425.5, 90, 0, -1546.0,4.0,2425.0, -1548.500, 4.001, 2425.500, 90,0, -1615.0,12.0,2461.0, -1584.500,101.100,1655.500,30,21, 30000, 50000, 90000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('1_3', '寒い丘', -1540.5, 14.001, 2671.5, 45, 0, -1544.0,14.0,2674.0, -1554.500, 9.001, 2683.500, 90,0, -1577.0,27.0,2707.0, -1580.500,102.100,1666.500,-24,32, 23000, 50000, 90000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('1_4', '麦が並ぶ家', -1531.5, 14.001, 2998.5, 90, 0, -1536.0,14.0,2998.0, -1537.500, 14.001, 2998.500, 90,0, -1546.0,18.0,3055.0, -1570.500,100.100,1671.500,-161,16, 28000, 50000, 120000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('1_5', '不気味な釣り場', -1580.5, 16.001, 3280.5, 45, 0, -1585.0,16.0,3284.0, -1587.500, 16.001, 3287.500, 49,0, -1569.0,11.0,3326.0, -1557.500,100.100,1661.500,-3,11, 50000, 100000, 1300000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('1_6', '砂利の谷', -1563.5, 4.001, 3557.5, 0, 0, -1564.0,4.0,3561.0, -1563.500, 4.001, 3564.500, 0, 0, -1564.0,8.0,3717.0, -1554.500,101.100,1678.500,10,21, 30000, 60000, 120000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('1_7', 'マグマの溜池', -1551.5, 16.001, 3889.5, 90, 0, -1557.0,16.0,3889.0, -1558.500, 16.001, 3889.500, 90, 30, -1522.0,4.0,3976.0, -1556.500,101.100,1691.500,-45,17, 40000, 120000, 150000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('1_8', '谷間の川', -1558.5, 31.001, 4160.5, -90, 0, -1554.0,31.0,4160.0, -1548.500, 31.001, 4160.500, -63, 20, -1636.0,32.0,4420.0, -1550.500,101.100,1699.500,42,18, 70000, 120000, 180000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('1_9', '山間の集落跡', -1612.5, 16.001, 4644.5, -90, 0, -1609.0,16.0,4644.0, -1605.500, 16.001, 4645.500, -90, 10, -1514.0,34.0,4641.0, -1559.500,108.100,1715.500,102,42, 50000, 90000, 150000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('1_10', '朱の遺跡', -1568.5, 5.001, 4753.5, 0, 0, -1569.0,5.0,4756.0, -1568.500, 4.001, 4767.500,0, 0, -1565.0,6.0,4947.0, -1587.500,100.100,1715.500,140,43, 45000, 90000, 150000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('1_11', '行水の谷', -1531.5, 23.001, 5061.5, 0, 0, -1532.0,23.0,5065.0, -1531.500, 23.001, 5070.500,0, 20, -1529.0,18.0,5284.0, -1594.500,96.100,1710.500,47,32, 50000, 90000, 150000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('1_12', '金属結晶の洞窟', -1546.5, 60.001, 5419.5, 70, 5, -1552.0,60.0,5421.0, -1553.500, 60.001, 5421.500, 76, 9, -1543.0,11.0,5452.0, -1602.500,93.100,1718.500,108,-7, 55000, 100000, 170000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('1_13', '泥中の蓮', -1581.5, 15.001, 5664.5, 0, 0, -1582.0,15.0,5672.0, -1581.500, 15.001, 5675.500, 7, 26, -1542.0,18.0,5865.0, -1615.500,100.100,1703.500,140,26, 55000, 90000, 150000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('1_14', '沼地の館', -1591.5, 24.001, 6157.5, 0, 0, -1592.0,24.0,6162.0, -1591.500, 24.001, 6165.500,0, -16, -1592.0,30.0,6204.0, -1572.500,100.100,1635.500,90,23, 60000, 120000, 180000)");

        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('2_1', '',-2227.5, 28, 1714.5,180,0 , -2228.0,28.0,1708.0, -2227.5, 28.001, 1705.0, 175, 10, -2493.0,15.0,1577.0, -1592.500, 100.100, 1624.500, 135, 32, 40000, 60000, 120000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('2_2', '', -2950.500, 79.001, 1620.500,90,0, -2956.0,79.0,1620.0, -2988.500, 79.001, 1620.500, 90, 20, -3162.0,57.0,1622.0, -1598.500, 100.100, 1617.500, 150, 18, 30000, 50000, 90000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('2_3', '', -3629.500, 14.001, 1640.500,90,0, -3635.0,14.0,1640.0, -3638.500, 14.001, 1640.500, 90, 10, -3832.0,30.0,1630.0, -1606.500, 100.100, 1614.500, 45, 19, 23000, 50000, 90000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('2_4', '', -4225.5, 25, 1635.5,90,0, -4232.0,25.0,1635.0, -4243.500, 25.001, 1635.500, 90, 20, -4460.0,25.0,1635.0, -1612.500, 110.100, 1644.500, 43, 20, 28000, 50000, 120000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('2_5', '', -4861.500, 78.001, 1652.500,90,0, -4872.0,80.0,1650.0, -4874.500, 80.001, 1650.500, 90, 20, -5071.0,24.0,1655.0, -1627.500, 106.100, 1653.500, 50, 16, 50000, 100000, 1300000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('2_6', '', -5446.5, 4, 1621.5,90,0, -5450.0,4.0,1621.0, -5457.500, 4.001, 1621.0, 90, 20, -5901.0,4.0,1619.0, -1643.500, 92.100, 1663.500, 138, -16, 30000, 60000, 120000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('2_7', '', -6259.500, 15.001, 1642.500,90,0, -6265.0,15.0,1642.0, -6267.500, 15.001, 1642.500, 90, 20, -6453.0,18.0,1641.0, -1643.500, 96.100, 1632.500, 165, 2, 40000, 120000,150000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('2_8', '', -6830.5, 4.0, 1643.5,90,0, -6835.0,4.0,1643.0, -6838.500, 4.001, 1643.5, 90, 20, -6844.0,26.0,1643.0, -1646.500, 100.100, 1614.500, -46, 7, 70000, 120000, 180000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('2_9', '', -7415.500, 14.001, 1660.500,90,0, -7420.0,14.0,1660.0, -7422.500, 14.001, 1660.500, 90, 20, -7597.0,23.0,1660.0, -1629.500, 100.100, 1618.500, -126, 20,50000, 90000,150000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('2_10', '', -8055.5, 4.0, 1614.5,90,0, -8058.0,4.0,1614.0, -8065.500, 4.001, 1614.500, 90, 20, -8497.0,4.0,1615.0, -1616.500, 100.100, 1607.500, 173, 9, 45000, 90000, 150000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('2_11', '', -8719.5, 4.0, 1622.5,90,0, -8724.0,4.0,1622.0, -8733.500, 4.001, 1622.0, 90, 20, -9174.0,4.0,1623.0, -1621.500, 100.100, 1592.500, 146, 17, 50000, 90000, 150000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('2_12', '', -9488.500, 28.001, 1624.500,90,0, -9493.0,28.0,1624.0, -9495.500, 28.001, 1624.500, 90, 20, -9634.0,15.0,1624.0, -1630.500, 100.100, 1583.500, 45, 25, 55000, 100000, 170000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('2_13', '', -10093.500, 40.001, 1635.500,90,0, -10104.0,40.0,1635.0, -10106.500, 40.001, 1635.500, 90, 20, -10322.0,40.0,1639.0, -1636.500, 106.100, 1604.500, 108, 13, 55000, 90000, 150000)");
        data.add("INSERT OR IGNORE INTO ChartStage (stage_id, stage_name, stage_tp_x, stage_tp_y, stage_tp_z, stage_tp_yaw, stage_tp_pitch, start_x, start_y, start_z, start_tp_x, start_tp_y, start_tp_z, start_tp_yaw, start_tp_pitch, goal_x, goal_y, goal_z, goal_tp_x, goal_tp_y, goal_tp_z, goal_tp_yaw, goal_tp_pitch, star_time_3, star_time_2, star_time_1) VALUES ('2_14', '', -10838.500, 33.001, 1645.500,90,0, -10843.0,33.0,1645.0, -10844.500, 33.001, 1645.500, 90, 20, -10840.0,68.0,1645.0, -1572.500, 100.100, 1635.500, -90, 0, 60000, 120000, 180000)");



        for(String sql:data)chartDB.setDB(sql,new ArrayList<>());


    }


}
