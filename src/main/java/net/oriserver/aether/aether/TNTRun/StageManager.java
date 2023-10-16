package net.oriserver.aether.aether.TNTRun;

import net.oriserver.aether.aether.sqlite.SQLiteAPI;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;

public class StageManager extends SQLiteAPI {

    final private TNTRunMain tntRunMain;
    final private JavaPlugin plugin;
    final private HashMap<String,Stage> stageMap = new HashMap<>();

    StageManager(JavaPlugin plugin,TNTRunMain tntRunMain){
        super(plugin,"TNTRun");
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
        super.initialize(sql);
        this.initialize();
    }

    public void initialize(){
        try(Connection conn = getSQLConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM TNTRunStage");
            ResultSet rs = ps.executeQuery()){
            while(rs.next()) {
                String stageName = rs.getString("stage_name");
                double x1 = rs.getDouble("x1");
                double y1 = rs.getDouble("y1");
                double z1 = rs.getDouble("z1");
                double x2 = rs.getDouble("x2");
                double y2 = rs.getDouble("y2");
                double z2 = rs.getDouble("z2");
                int maxPlayer = rs.getInt("max_player");
                int minPlayer = rs.getInt("min_player");
                double speed = rs.getDouble("disappear_speed"); // assuming this is a double, change if it's not
                int deathLine = rs.getInt("death_line");
                double sx = rs.getDouble("sx");
                double sy = rs.getDouble("sy");
                double sz = rs.getDouble("sz");
                Stage stage = new Stage(stageName, x1, y1, z1, x2, y2, z2, maxPlayer, minPlayer, speed, deathLine, sx, sy, sz);
                stageMap.put(stageName,stage);
            }
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "Couldn't execute MySQL statement: ", ex);
        }
    }


    public void updateStage(String stageName) {
        try (Connection conn = getSQLConnection();
             PreparedStatement ps = conn.prepareStatement( "SELECT * FROM TNTRunStage WHERE stage_name = ?")) {
                ps.setString(1, stageName);
                try (ResultSet rs = ps.executeQuery()){
                    while(rs.next()) {
                        double x1 = rs.getDouble("x1");
                        double y1 = rs.getDouble("y1");
                        double z1 = rs.getDouble("z1");
                        double x2 = rs.getDouble("x2");
                        double y2 = rs.getDouble("y2");
                        double z2 = rs.getDouble("z2");
                        int maxPlayer = rs.getInt("max_player");
                        int minPlayer = rs.getInt("min_player");
                        double speed = rs.getDouble("disappear_speed"); // assuming this is a double, change if it's not
                        int deathLine = rs.getInt("death_line");
                        double sx = rs.getDouble("sx");
                        double sy = rs.getDouble("sy");
                        double sz = rs.getDouble("sz");
                        Stage stage = new Stage(stageName, x1, y1, z1, x2, y2, z2, maxPlayer, minPlayer, speed, deathLine, sx, sy, sz);
                        stageMap.put(stageName, stage);
                    }
                }
        }catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "Couldn't execute MySQL statement: ", ex);
        }
    }

    public void addStage(String stageName) {
        try (Connection conn = getSQLConnection();
             PreparedStatement ps = conn.prepareStatement( "SELECT * FROM TNTRunStage WHERE stage_name = ?")) {
            ps.setString(1, stageName);
            try (ResultSet rs = ps.executeQuery()){
                while(rs.next()) {
                    double x1 = rs.getDouble("x1");
                    double y1 = rs.getDouble("y1");
                    double z1 = rs.getDouble("z1");
                    double x2 = rs.getDouble("x2");
                    double y2 = rs.getDouble("y2");
                    double z2 = rs.getDouble("z2");
                    int maxPlayer = rs.getInt("max_player");
                    int minPlayer = rs.getInt("min_player");
                    double speed = rs.getDouble("disappear_speed"); // assuming this is a double, change if it's not
                    int deathLine = rs.getInt("death_line");
                    double sx = rs.getDouble("sx");
                    double sy = rs.getDouble("sy");
                    double sz = rs.getDouble("sz");
                    Stage stage = new Stage(stageName, x1, y1, z1, x2, y2, z2, maxPlayer, minPlayer, speed, deathLine, sx, sy, sz);
                    stageMap.put(stageName, stage);
                }
            }
        }catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "Couldn't execute MySQL statement: ", ex);
        }
    }

    public void deleteStage(String stageName){stageMap.remove(stageName);}


    public Stage getStage(String name){
        if(!stageMap.containsKey(name))return null;
        return stageMap.get(name);
    }




}
