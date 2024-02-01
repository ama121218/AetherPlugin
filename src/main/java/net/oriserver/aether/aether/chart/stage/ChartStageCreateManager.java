package net.oriserver.aether.aether.chart.stage;

import net.oriserver.aether.aether.chart.ChartManager;
import net.oriserver.aether.aether.chart.events.ChartCreateToolClickEvent;
import net.oriserver.aether.aether.chart.events.ChartInventoryClickEvent;
import net.oriserver.aether.aether.chart.hologram.ChartHologram;
import net.oriserver.aether.aether.chart.stage.ChartStageCreate;
import net.oriserver.aether.aether.chart.stage.ChartStageInfo;
import net.oriserver.aether.aether.sqlite.SQLiteAPI;
import net.oriserver.aether.aether.statics.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ChartStageCreateManager {
    private final HashMap<String, ChartStageCreate> createMap = new HashMap<>();

    private final SQLiteAPI chartStageDB;
    private final SQLiteAPI chartCheckPointDB;
    private final ChartStageInfo chartStageInfo;
    private final ChartHologram chartHologram;
    private final JavaPlugin plugin;
    public ChartStageCreateManager(JavaPlugin plugin, SQLiteAPI chartStageDB, SQLiteAPI chartCheckPointDB, ChartStageInfo chartStageInfo,ChartHologram chartHologram) {
        this.plugin = plugin;
        this.chartStageDB = chartStageDB;
        this.chartCheckPointDB = chartCheckPointDB;
        this.chartStageInfo = chartStageInfo;
        this.chartHologram = chartHologram;
    }

    public void create(Player p, String stage_id) {
        if (createMap.containsKey(String.valueOf(p.getUniqueId()))) {
            p.sendMessage("作成中のステージがあります");
            return;
        }
        p.sendMessage(stage_id + " " + "を作成します");
        ChartStageCreate createChartStage = new ChartStageCreate(p.getName(), stage_id, this);
        Bukkit.getServer().getPluginManager().registerEvents(createChartStage, this.plugin);
        createMap.put(String.valueOf(p.getUniqueId()), createChartStage);
        p.getInventory().addItem(Item.createitem(Material.SHULKER_SHELL, 1, ChatColor.WHITE+"Chart Stage Create Tool"));
        Bukkit.getPluginManager().callEvent(new ChartCreateToolClickEvent(p));
    }

    public void quit(Player p) {
        if (!createMap.containsKey(String.valueOf(p.getUniqueId()))) {
            p.sendMessage("作成中のステージがありません");
            return;
        }
        ChartStageCreate createChartStage = createMap.get(String.valueOf(p.getUniqueId()));
        HandlerList.unregisterAll(createChartStage);
        createMap.remove(String.valueOf(p.getUniqueId()), createChartStage);
        p.sendMessage("作成をやめました");
        Item.removeCustomNamedItemFromInventory(p.getInventory(), Material.SHULKER_SHELL, ChatColor.WHITE+"Chart Stage Create Tool");
        Item.removeCustomNamedItemFromInventory(p.getInventory(), Material.IRON_PLATE, ChatColor.WHITE+"SetCheckpoint ContinuousAddition");
        Item.removeCustomNamedItemFromInventory(p.getInventory(), Material.IRON_PLATE, ChatColor.WHITE+"SetCheckpoint");
    }

    public void complete(Player p, ArrayList<Object> list,ArrayList<Double[]> checkPoint_list) {
        if (!createMap.containsKey(String.valueOf(p.getUniqueId()))) {p.sendMessage("作成中のステージがありません");return;}
        ChartStageCreate createChartStage = createMap.get(String.valueOf(p.getUniqueId()));
        setData(createChartStage,list,checkPoint_list);
        HandlerList.unregisterAll(createChartStage);
        int stage_id = chartStageInfo.getStage_id(createChartStage.getStage_id());
        if(stage_id == -1){p.sendMessage("エラー");return;}
        chartStageInfo.setIndexMap(stage_id);//hologram
        createMap.remove(String.valueOf(p.getUniqueId()), createChartStage);
        p.sendMessage(createChartStage.getStage_id()+"のステージを作成しました");

        Item.removeCustomNamedItemFromInventory(p.getInventory(), Material.SHULKER_SHELL, ChatColor.WHITE+"Chart Stage Create Tool");
        Item.removeCustomNamedItemFromInventory(p.getInventory(), Material.IRON_PLATE, ChatColor.WHITE+"SetCheckpoint ContinuousAddition");
    }

    public void rework(Player p, String stage_id) {
        if (createMap.containsKey(String.valueOf(p.getUniqueId()))) {
            p.sendMessage("作成中のステージがあります");
            return;
        }
        if(!isStage(stage_id)){p.sendMessage(stage_id+"はありません");return;}
        p.sendMessage(stage_id + " " + "を作り直します");
        ArrayList<Object> stage_list = getStageData(stage_id);
        ArrayList<Double[]> checkPoint_list = getCheckPointData(stage_id);
        ChartStageCreate createChartStage = new ChartStageCreate(p.getName(), stage_id, this);
        Bukkit.getServer().getPluginManager().registerEvents(createChartStage, this.plugin);
        createMap.put(String.valueOf(p.getUniqueId()), createChartStage);
        createChartStage.rework(stage_list,checkPoint_list);
        p.getInventory().addItem(Item.createitem(Material.SHULKER_SHELL, 1, ChatColor.WHITE+"Chart Stage Create Tool"));
    }


    public ArrayList<Double[]> getCheckPointData(String stage_id) {
        List<Double[]> checkPointData = chartCheckPointDB.getDB("SELECT * FROM ChartCheckPoint WHERE stage_id = ? ORDER BY point ASC", Arrays.asList(stage_id), rs -> {
            List<Double[]> cd = new ArrayList<>();
            while (rs.next()) {
                Double[] doubles = new Double[3];

                doubles[0] = rs.getDouble("x");
                doubles[1] = rs.getDouble("y");
                doubles[2] = rs.getDouble("z");

                cd.add(doubles);
            }
            return cd;
        });
        return (ArrayList<Double[]>) checkPointData;
    }
    public ArrayList<Object> getStageData(String stage_id) {
        List<Object> stageData = chartStageDB.getDB("SELECT * FROM ChartStage WHERE stage_id = ?", Arrays.asList(stage_id), rs -> {
            List<Object> cd = new ArrayList<>();
            while (rs.next()) {
                cd.add(rs.getString("stage_name"));
                cd.add(rs.getDouble("stage_tp_x"));
                cd.add(rs.getDouble("stage_tp_y"));
                cd.add(rs.getDouble("stage_tp_z"));
                cd.add(rs.getFloat("stage_tp_yaw"));
                cd.add(rs.getFloat("stage_tp_pitch"));
                cd.add(rs.getDouble("start_x"));
                cd.add(rs.getDouble("start_y"));
                cd.add(rs.getDouble("start_z"));
                cd.add(rs.getDouble("start_tp_x"));
                cd.add(rs.getDouble("start_tp_y"));
                cd.add(rs.getDouble("start_tp_z"));
                cd.add(rs.getFloat("start_tp_yaw"));
                cd.add(rs.getFloat("start_tp_pitch"));
                cd.add(rs.getDouble("goal_x"));
                cd.add(rs.getDouble("goal_y"));
                cd.add(rs.getDouble("goal_z"));
                cd.add(rs.getDouble("goal_tp_x"));
                cd.add(rs.getDouble("goal_tp_y"));
                cd.add(rs.getDouble("goal_tp_z"));
                cd.add(rs.getFloat("goal_tp_yaw"));
                cd.add(rs.getFloat("goal_tp_pitch"));
                cd.add(rs.getLong("star_time_3"));
                cd.add(rs.getLong("star_time_2"));
                cd.add(rs.getLong("star_time_1"));
            }
            return cd;
        });
        return (ArrayList<Object>) stageData;
    }

    public void setData(ChartStageCreate createChartStage, ArrayList<Object> list, ArrayList<Double[]> checkPoint_List) {
        if(isStage(createChartStage.getStage_id()))deleteData(createChartStage.getStage_id());
        chartStageDB.setDB("INSERT OR IGNORE INTO ChartStage (stage_id,stage_name,stage_tp_x,stage_tp_y,stage_tp_z,stage_tp_yaw,stage_tp_pitch,start_x,start_y,start_z,start_tp_x,start_tp_y,start_tp_z,start_tp_yaw,start_tp_pitch,goal_x,goal_y,goal_z,goal_tp_x,goal_tp_y,goal_tp_z,goal_tp_yaw,goal_tp_pitch,star_time_3,star_time_2,star_time_1) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);" ,list);
        for (int i = 0; i < checkPoint_List.size(); i++) {
            Double[] doubles = checkPoint_List.get(i);
            chartHologram.setCheckPointWhenCreateStage(doubles);
            chartCheckPointDB.setDB("INSERT OR IGNORE INTO ChartCheckPoint (stage_id,point,x,y,z) VALUES(?,?,?,?,?);", Arrays.asList(createChartStage.getStage_id(), i+1, doubles[0], doubles[1], doubles[2]));
        }
    }


    public boolean isStage(String stage_id) {
        List<Object> exists = chartStageDB.getDB("SELECT 1 FROM ChartStage WHERE stage_id = ?", Arrays.asList(stage_id), rs -> {
            List<Object> pt = new ArrayList<>();
            pt.add(rs.next());
            return pt;
        });
        return (boolean) exists.get(0);
    }

    public void deleteData(String stage_id) {
        chartStageDB.setDB("DELETE FROM ChartStage WHERE stage_id = ?", Arrays.asList(stage_id));
        chartHologram.deleteOldCheckPoint(getCheckPointData(stage_id));
        chartCheckPointDB.setDB("DELETE FROM ChartCheckPoint WHERE stage_id = ?", Arrays.asList(stage_id));
    }

    public ChartStageCreate getCreateChartStage(Player p) {
        if (!createMap.containsKey(String.valueOf(p.getUniqueId()))) {
            p.sendMessage("作成中のステージがありません");
            return null;
        }
        return createMap.get(String.valueOf(p.getUniqueId()));
    }

}
