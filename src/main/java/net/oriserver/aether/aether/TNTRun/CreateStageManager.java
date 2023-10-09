package net.oriserver.aether.aether.TNTRun;

import net.oriserver.aether.aether.sqlite.SQLiteAPI;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class CreateStageManager extends SQLiteAPI {
    final private TNTRunMain tntRunMain;
    final private HashMap<String,CreateStage> createMap = new HashMap<>();

    CreateStageManager(JavaPlugin plugin, TNTRunMain tntRunMain){

        super(plugin,"TNTRun");
        this.tntRunMain = tntRunMain;


    }






}
