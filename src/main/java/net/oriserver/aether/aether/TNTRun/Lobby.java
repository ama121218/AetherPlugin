package net.oriserver.aether.aether.TNTRun;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashSet;

public class Lobby {
    private String lobbyName;
    private Stage stage;
    private String lobbyCreatorName;
    private boolean isRun = false;
    private int minPlayer;
    private Game game;
    private BukkitTask countdownTask;
    private static final int COUNTDOWN_SECONDS = 10;

    private HashSet<Player> playerSet = new HashSet<>();
    Lobby(Stage stage,String lobbyCreatorName){
        this.stage = stage;
        this.lobbyCreatorName = lobbyCreatorName;
    }


    public void addPlayer(Player p){
        if(this.playerSet.size()>=stage.getMaxPlayer()){
            p.sendMessage("このロビーは満員です");
            return;
        }
        playerSet.add(p);
        if(this.playerSet.size()>=stage.getMinPlayer()){
            startPreGame();
        }
    }
    public void removePlayer(Player p){
        playerSet.remove(p);
    }


    public void startPreGame() {
        if (game == null) game = new Game();
        if (countdownTask == null) { // タスクがまだスケジュールされていない場合のみ
            countdownTask = new BukkitRunnable() {
                int countdown = COUNTDOWN_SECONDS;

                @Override
                public void run() {
                    if (playerSet.size() < stage.getMinPlayer()) {
                        // プレイヤーが最小数未満の場合、カウントダウンをキャンセル
                        this.cancel();
                        countdownTask = null;
                        // ゲーム開始をキャンセルするメッセージをプレイヤーに送信
                        for (Player p : playerSet) {
                            if (p != null) {
                                p.sendMessage(ChatColor.RED + "最低人数のプレイヤーに満たないためゲームをキャンセルします");
                            }
                        }
                        return;
                    }

                    // メッセージをプレイヤーに送信
                    for (Player p : playerSet) {
                        if (p != null) {
                            p.sendMessage(ChatColor.GREEN + "ゲーム開始まで " + countdown + " 秒です");
                        }
                    }

                    countdown--;
                    if (countdown < 0) {
                        // カウントダウンが0になったら、ゲームを開始してタスクをキャンセル
                        this.cancel();
                        countdownTask = null;
                        game.start();
                    }
                }
            }.runTaskTimer(/* プラグインのインスタンス */, 0L, 20L); // 20 ticks = 1 second
        }
    }

    public void stopPreGame() {
        if (countdownTask != null) {
            countdownTask.cancel();
            countdownTask = null;
        }
    }




    public HashSet<String> getPlayerSet(){
        return this.playerSet;
    }


}
