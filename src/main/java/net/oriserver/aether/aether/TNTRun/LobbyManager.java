package net.oriserver.aether.aether.TNTRun;

import java.util.HashMap;

public class LobbyManager {
    final private TNTRunMain tntRunMain;
    final private HashMap<String,Lobby> stageMap = new HashMap<>();
    final private StageManager stageManager;

    LobbyManager(TNTRunMain tntRunMain){
        this.tntRunMain = tntRunMain;
        stageManager = tntRunMain.getStageManager();
    }



    public void createLobby(String stageName,String lobbyCreatorName){
        Stage stage = stageManager.getStage(stageName);
        if(stage==null)return;

        Lobby lobby = new Lobby(stage,lobbyCreatorName);

    }



}
