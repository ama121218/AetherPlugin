package net.oriserver.aether.aether.particle;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

@Component
public class ParticleManager {

    private final int range = 30;

    private final HashMap<String,ParticleTask> particleTaskMap = new HashMap<>();
    private final HashSet<String> blockPlayerParticleSet = new HashSet<>();
    private final Particle particle;
    private final Plugin plugin;

    @Autowired
    public ParticleManager(Plugin plugin){
        this.plugin = plugin;
        particle = new Particle();
    }

    public void setPlayerParticle(Player p,org.bukkit.Particle particle, Particle.ParticleAction particleAction){
        if(blockPlayerParticleSet.contains(p.getUniqueId().toString())){
            p.sendMessage("パーティクルをブロックしているため表示できません");
            return;
        }
        p.sendMessage("パーティクルをオンにしました。");
        String player_uuid = p.getUniqueId().toString();
        if(particleTaskMap.containsKey(player_uuid))particleTaskMap.get(player_uuid).cancel();
        HashSet<Player> nearPlayers = new HashSet<>();
        for(Player worldPlayer : p.getWorld().getPlayers()) {
            if(blockPlayerParticleSet.contains(worldPlayer.getUniqueId().toString()))continue;
            if(worldPlayer.getLocation().distance(p.getLocation()) <= range) {
                nearPlayers.add(worldPlayer);
            }
        }
        ParticleTask task = new ParticleTask(p,particle,particleAction,nearPlayers);
        particleTaskMap.put(p.getUniqueId().toString(),task);
        task.runTaskTimer(plugin, 20L, 5L);
    }

    public ParticleTask getPlayerParticle(String player_UUID){return particleTaskMap.get(player_UUID);}

    public void cancelPlayerParticle(Player player){
        ParticleTask particleTask = particleTaskMap.remove(player.getUniqueId().toString());
        if(particleTask != null) particleTask.cancel();
        player.sendMessage("パーティクルをオフにしました。");
    }

    public void onBlockPlayerParticle(Player p){
        blockPlayerParticleSet.add(p.getUniqueId().toString());
        for(ParticleTask task:particleTaskMap.values()){
            task.getNearPlayersSet().remove(p);
        }
        p.sendMessage("パーティクルをブロックしました。");
    }

    public void offBlockPlayerParticle(Player p){
        blockPlayerParticleSet.remove(p.getUniqueId().toString());
        p.sendMessage("パーティクルをブロックを解除しました。");
    }

    public Particle getParticle(){return particle;}

    public class ParticleTask extends BukkitRunnable {
        private int runCount = 0;
        private final HashSet<Player> nearPlayers;
        private final Particle.ParticleAction particleAction;
        private final org.bukkit.Particle particle;
        private final Player p;
        public ParticleTask(Player p,org.bukkit.Particle particle,Particle.ParticleAction particleAction,HashSet<Player> nearPlayers) {
            this.p = p;
            this.nearPlayers = nearPlayers;
            this.particleAction = particleAction;
            this.particle = particle;
        }
        @Override
        public void run() {
            particleAction.display(p.getLocation(),particle,nearPlayers,runCount);
            if(runCount >= 50){
                this.cancel();
                particleTaskMap.remove(p.getUniqueId().toString());
                return;
            }
            runCount++;
        }
        public HashSet<Player> getNearPlayersSet() {
            return nearPlayers;
        }
    }

    public void setQuitPlayer(Player p){
        ParticleTask particleTask = particleTaskMap.remove(p.getUniqueId().toString());
        if(particleTask != null) particleTask.cancel();
        for(ParticleTask task:particleTaskMap.values()){
            task.getNearPlayersSet().remove(p);
        }
    }

}
