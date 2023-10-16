package net.oriserver.aether.aether.TNTRun;

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

public class CreateStageManager extends SQLiteAPI {
    final private TNTRunMain tntRunMain;
    final private JavaPlugin plugin;
    final private HashMap<String,CreateStage> createMap = new HashMap<>();

    CreateStageManager(JavaPlugin plugin, TNTRunMain tntRunMain){

        super(plugin,"TNTRunStage");
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
        p.getInventory().addItem(Item.createitem(Material.SHULKER_SHELL,1,"TNTRun create stage tool"));
    }

    public void quitStage(Player p){
        if(!createMap.containsKey(String.valueOf(p.getUniqueId()))){
            p.sendMessage("作成中のステージがありません");
            return;
        }
        CreateStage createStage = createMap.get(String.valueOf(p.getUniqueId()));
        HandlerList.unregisterAll(createStage);
        createMap.remove(String.valueOf(p.getUniqueId()),createStage);
        p.sendMessage("作成をやめました");
        Item.removeCustomNamedItemFromInventory(p.getInventory(),Material.SHULKER_SHELL,"TNTRun create stage tool");
        Item.removeCustomNamedItemFromInventory(p.getInventory(),Material.WOOD_PICKAXE, "TNTRun_Setting");
    }
    public boolean completeStage(Player p){
        CreateStage createStage = createMap.get(String.valueOf(p.getUniqueId()));
        Object[] objects = createStage.getData();
        for(Object object:objects){
            if(object==null){
                p.sendMessage(ChatColor.DARK_RED+"作成中のステージに設定していないパラメータがあります");
                return false;
            }
        }
        setData(objects);
        HandlerList.unregisterAll(createStage);
        createMap.remove(String.valueOf(p.getUniqueId()),createStage);
        p.sendMessage("作成しました");
        Item.removeCustomNamedItemFromInventory(p.getInventory(),Material.SHULKER_SHELL,"TNTRun create stage tool");
        Item.removeCustomNamedItemFromInventory(p.getInventory(),Material.WOOD_PICKAXE, "TNTRun_Setting");
        return true;
    }

    public void reworkStage(Player p,String name){
        ArrayList<Object> list = getData(name);
        CreateStage createStage = new CreateStage(p.getName(),this);
        Bukkit.getServer().getPluginManager().registerEvents(createStage, this.plugin);
        createMap.put(String.valueOf(p.getUniqueId()),createStage);

        createStage.reworkStage(list);
    }


    public ArrayList<Object> getData(String name) {
        List<Object> stagedata = getDB("SELECT * FROM TNTRunStage WHERE stage_name = ?", Arrays.asList(name), rs -> {
            List<Object> pd = new ArrayList<>();
            while(rs.next()){
                pd.add(rs.getString("stage_name"));
                pd.add(rs.getDouble("x1"));
                pd.add(rs.getDouble("y1"));
                pd.add(rs.getDouble("z1"));
                pd.add(rs.getDouble("x2"));
                pd.add(rs.getDouble("y2"));
                pd.add(rs.getDouble("z2"));
                pd.add(rs.getInt("max_player"));
                pd.add(rs.getInt("min_player"));
                pd.add(rs.getInt("death_line"));
                pd.add(rs.getInt("disappear_speed"));
                pd.add(rs.getDouble("sx"));
                pd.add(rs.getDouble("sy"));
                pd.add(rs.getDouble("sz"));
                pd.add(rs.getString("create_player"));
            }
            return pd;
        });
        return  (ArrayList<Object>) stagedata;
    }



    public void setData(Object[] objects){
        if(isStage((String)objects[0])){
           deteleData((String)objects[0]);
        }
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
    public void deteleData(String name){
        setDB("DELETE FROM TNTRunStage WHERE stage_name = ?",Arrays.asList(name));
    }
    public CreateStage getCreateStage(Player p){
        if(!createMap.containsKey(String.valueOf(p.getUniqueId()))){
            p.sendMessage("作成中のステージがありません");
            return null;
        }
        return createMap.get(String.valueOf(p.getUniqueId()));
    }
}
