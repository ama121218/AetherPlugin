package net.oriserver.aether.aether.TNTRun;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class TNTRunMain {

    final private JavaPlugin plugin;

    final private StageManager stageManager;
    final private LobbyManager lobbyManager;
    final private GameManager gameManager;
    final private CreateStageManager createStageManager;

    public TNTRunMain(JavaPlugin plugin) {

        this.plugin = plugin;

        // 各マネージャークラスのインスタンスを生成
        this.stageManager = new StageManager(plugin,this);
        this.createStageManager = new CreateStageManager(this.plugin,this);
        this.lobbyManager = new LobbyManager(this);
        this.gameManager = new GameManager(this);

        // その他の初期化処理...


        // 各マネージャーのゲッター

    }
    public StageManager getStageManager () {
        return stageManager;
    }
    public CreateStageManager getCreateStageManager(){
        return createStageManager;
    }

    public LobbyManager getLobbyManager () {
        return lobbyManager;
    }

    public GameManager getGameManager () {
        return gameManager;
    }
}
