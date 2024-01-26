package net.oriserver.aether.aether.particle;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ParticleManager {

    private final int range = 30;

    private final HashMap<String,BukkitRunnable> playerParticleMap = new HashMap<>();
    private final HashSet<String> blockPlayerParticleSet = new HashSet<>();
    private final Particle particle;
    private final Plugin plugin;
    public ParticleManager(Plugin plugin){
        this.plugin = plugin;
        particle = new Particle();
    }

    public void setPlayerParticle(Player p,org.bukkit.Particle particle, Particle.ParticleAction particleAction){
        p.sendMessage("パーティクルをオンにしました。");
        String player_uuid = p.getUniqueId().toString();
        if(playerParticleMap.containsKey(player_uuid))playerParticleMap.get(player_uuid).cancel();
        HashSet<Player> nearbyPlayers = new HashSet<>();
        for(Player worldPlayer : p.getWorld().getPlayers()) {
            if(blockPlayerParticleSet.contains(worldPlayer.getUniqueId().toString()))continue;
            if(worldPlayer.getLocation().distance(p.getLocation()) <= range) {
                nearbyPlayers.add(worldPlayer);
            }
        }
        BukkitRunnable task = new BukkitRunnable() {
            private int runCount = 0;
            @Override
            public void run() {
                particleAction.display(p.getLocation(),particle,nearbyPlayers);
                if(runCount >= 50){this.cancel();return;}
                runCount++;
            }
        };
        playerParticleMap.put(p.getUniqueId().toString(),task);
        task.runTaskTimer(plugin, 20L, 5L);
    }

    public BukkitRunnable getPlayerParticle(String player_UUID){return playerParticleMap.get(player_UUID);}
    public void cancelPlayerParticle(Player player){
        playerParticleMap.get(player.getUniqueId().toString()).cancel();
        playerParticleMap.remove(player.getUniqueId().toString());
        player.sendMessage("パーティクルをオフにしました。");
    }


    public Particle getParticle(){return particle;}



}
