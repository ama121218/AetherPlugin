package net.oriserver.aether.aether.TNTRun;

import net.oriserver.aether.aether.sqlite.SQLiteAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CreateStageManager extends SQLiteAPI {
    final private TNTRunMain tntRunMain;
    final private JavaPlugin plugin;
    final private HashMap<String,CreateStage> createMap = new HashMap<>();

    CreateStageManager(JavaPlugin plugin, TNTRunMain tntRunMain){

        super(plugin,"TNtRunStage");
        this.tntRunMain = tntRunMain;
        this.plugin = plugin;

        String sql = "CREATE TABLE IF NOT EXISTS TNTRunStage (" +
                "`stage_name` varchar NOT NULL," +
                "`x1` varchar NOT NULL," +
                "`y1` varchar NOT NULL," +
                "`z1` varchar NOT NULL," +
                "`x2` varchar NOT NULL," +
                "`y2` varchar NOT NULL," +
                "`z2` varchar NOT NULL," +
                "`max_player` int NOT NULL," +
                "`min_player` int  NOT NULL," +
                "`death_line` int  NOT NULL," +
                "`disappear_speed` varchar NOT NULL," +
                "`sx` varchar NOT NULL," +
                "`sy` varchar NOT NULL," +
                "`sz` varchar NOT NULL," +
                "`create_player` varchar NOT NULL," +
                "PRIMARY KEY (`stage_name`)" +
                ");";
        initialize(sql);

    }

    public void createStage(Player p){
        if(createMap.containsKey(String.valueOf(p.getUniqueId()))){
            p.sendMessage("作成中のステージがあります");
            return;
        }
        CreateStage createStage = new CreateStage(p.getName(),this);
        Bukkit.getServer().getPluginManager().registerEvents(createStage, this.plugin);
        createMap.put(String.valueOf(p.getUniqueId()),createStage);
    }

    public void quitStage(Player p){
        if(!createMap.containsKey(String.valueOf(p.getUniqueId()))){
            p.sendMessage("作成中のステージがありません");
            return;
        }
        CreateStage createStage = createMap.get(String.valueOf(p.getUniqueId()));
        HandlerList.unregisterAll(createStage);
        createMap.remove(String.valueOf(p.getUniqueId()),createStage);
    }
    public void completeStage(Player p){
        CreateStage createStage = createMap.get(String.valueOf(p.getUniqueId()));



        Object[] objects = createStage.getData();
        setData(objects);
        HandlerList.unregisterAll(createStage);
        createMap.remove(String.valueOf(p.getUniqueId()),createStage);
    }
    public void setData(Object[] objects){
        setDB("INSERT OR IGNORE INTO TNTRunStage (stage_name,x1,y1,z1,x2,y2,z2,max_player,min_player,death_line,disappear_speed,sx,sy,sz,create_player) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);",
                Arrays.asList(
                        (String)objects[0],
                        (double)objects[1],
                        (double)objects[2],
                        (double)objects[3],
                        (double)objects[4],
                        (double)objects[5],
                        (double)objects[6],
                        (int)objects[7],
                        (int)objects[8],
                        (int)objects[9],
                        (double)objects[10],
                        (double)objects[11],
                        (double)objects[12],
                        (double)objects[13],
                        (String)objects[14]
                ));
    }
    public boolean isStage(String name){
        List<Object> exists = getDB("SELECT 1 FROM TNTRunStage WHERE stage_name = ?", Arrays.asList(name), rs -> {
            List<Object> pt = new ArrayList<>();
            pt.add(rs.next());
            return pt;
        });
        return (boolean)exists.get(0);
    }
}
