package net.oriserver.aether.aether.sqlite;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

public class SQLiteAPI {//JDBCを使ったSQLiteとの接続API
    protected final JavaPlugin plugin;
    private final String dbname;
    public SQLiteAPI(JavaPlugin plugin, String dbname){
        this.plugin = plugin;
        this.dbname = plugin.getConfig().getString("SQLite.Filename", dbname);
    }

    public void initialize(String sql){//dbファイルがあるかの確認なければ作成
        Connection conn = null;
        PreparedStatement ps = null;
        File ndir = new File(plugin.getDataFolder().getAbsolutePath());
        if(!ndir.exists())ndir.mkdirs();
        File dataFolder = new File(plugin.getDataFolder(), dbname+".db");
        if (!dataFolder.exists()){
            try {
                dataFolder.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "File write error: "+dbname+".db");
            }
        }
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (ps != null)ps.close();
                if (conn != null)conn.close();
            } catch (SQLException ex) {
                plugin.getLogger().log(Level.SEVERE, "Failed to close MySQL connection: ", ex);
            }
        }
    }
    public Connection getSQLConnection() {//指定されたデータベースに接続するメソッド
        File dataFolder = new File(plugin.getDataFolder(), dbname+".db");
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE,"SQLite exception on initialize", ex);
        } catch (ClassNotFoundException ex) {
            plugin.getLogger().log(Level.SEVERE, "You need the SQLite JBDC library. Google it. Put it in /lib folder.");
        }
        return null;
    }
    public void setDB(String sql, List<Object> parameters) {//クエリを実行するメソッド
        try (Connection conn = getSQLConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "Couldn't execute MySQL statement: ", ex);
        }
    }

    @FunctionalInterface
    public interface ResultSetHandler<T> {//クエリの内容でSQLiteからデータを取得する関数型インターフェイスの宣言
        List<T> handle(ResultSet rs) throws SQLException;
    }
    public <T> List<T> getDB(String sql, List<Object> parameters, ResultSetHandler<T> handler) {//クエリの内容でSQLiteからデータを取得する
        try (Connection conn = getSQLConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                return handler.handle(rs);
            }
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "Failed to close MySQL connection: ", ex);
            return Collections.emptyList();
        }
    }
}
