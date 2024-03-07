package net.oriserver.aether.aether.sqlite.playerDB;

import net.oriserver.aether.aether.sqlite.SQLiteAPI;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//プレイヤーのUUIDとプレイヤー名を保存するDBクラス
//初めて入ったかの確認などを行う
//プレイヤー名に変更があったの判定
public class PlayerUUIDDB extends SQLiteAPI {

    public PlayerUUIDDB(JavaPlugin plugin, String dbname){
        super(plugin,dbname);
        String sql = "CREATE TABLE IF NOT EXISTS Player_name (" +
                "`player_uuid` varchar NOT NULL," +
                "`player_name` varchar NOT NULL," +
                "PRIMARY KEY (`player_uuid`)" +
                ");";
        initialize(sql);
    }


    public boolean isPlayerInDatabase(String uuid){//データがあればtrue、なければfalseを返して保存
        List<Object> is = getDB("SELECT player_name FROM Player_name WHERE player_uuid = ?", Arrays.asList(uuid), rs -> {
            List<Object> isPlayer = new ArrayList<>();
            if(rs.next()) {
                isPlayer.add(true);
            }else{
                isPlayer.add(false);
            }
            return isPlayer;
        });
        return (boolean)is.get(0);
    }
    public boolean isChangeName(String uuid,String name){//プレイヤー名に変更があったかを確認
        List<Object> p_name = getDB("SELECT player_name FROM Player_name WHERE player_uuid = ?", Arrays.asList(uuid), rs -> {
            List<Object> player_name = new ArrayList<>();
            while(rs.next()){
                player_name.add("player_name");
            }
            return player_name;
        });
        return name.equals((String) p_name.get(0));
    }

    public void updatePlayer_name(String uuid,String name){//プレイヤー名の更新
        setDB("UPDATE Player_name SET player_name = ? WHERE player_uuid = ?",Arrays.asList(name,uuid));
    }

    public void insertPlayer_name(String uuid,String name){//プレイヤー名とUUIDの保存
        setDB("INSERT OR IGNORE INTO Player_name (player_uuid,player_name) VALUES(?,?);",Arrays.asList(uuid,name));
    }




}
