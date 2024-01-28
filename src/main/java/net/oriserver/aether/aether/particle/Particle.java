package net.oriserver.aether.aether.particle;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.HashSet;

public class Particle {

    ArrayList<double[]> wingParticleList;
    ArrayList<double[]> shieldParticleList;

    public Particle(){
        wingParticleList = new ArrayList<>();
        wingParticleList.add(new double[]{-2, 2.5, -0.5});
        wingParticleList.add(new double[]{-1.5, 2.3, -0.5});
        wingParticleList.add(new double[]{-1, 2.0, -0.5});
        wingParticleList.add(new double[]{-0.5, 2.0, -0.5});
        wingParticleList.add(new double[]{-0.7, 1.2, -0.5});
        wingParticleList.add(new double[]{0, 1.7, -0.5});
        wingParticleList.add(new double[]{0.7, 1.2, -0.5});
        wingParticleList.add(new double[]{0.5, 2.0, -0.5});
        wingParticleList.add(new double[]{1, 2.2, -0.5});
        wingParticleList.add(new double[]{1.5, 2.5, -0.5});
        wingParticleList.add(new double[]{2, 2.7, -0.5});

        shieldParticleList = new ArrayList<>();
        shieldParticleList.add(new double[]{0.5, 2, 0.5});
        shieldParticleList.add(new double[]{0, 2, 0.7});
        shieldParticleList.add(new double[]{-0.5, 2, 0.5});
        shieldParticleList.add(new double[]{-0.7, 2, 0});
        shieldParticleList.add(new double[]{-0.5, 2, -0.5});
        shieldParticleList.add(new double[]{0, 2, -0.7});
        shieldParticleList.add(new double[]{0.5, 2, -0.5});
        shieldParticleList.add(new double[]{0.7, 2, 0});
    }

    public final ParticleAction circleParticle = (location,particle,targetSet,runCount) -> {
        for (float f = 0; f < 360; f = (float) (f + 10)) {
            Location local = location.clone().add(Math.sin(Math.toRadians(f)) * 1, +0.2, Math.cos(Math.toRadians(f)) * 1);
            for(Player target:targetSet)target.spawnParticle(particle,local,0,0,0,0,0);
        }
    };

    public final ParticleAction ringParticle = (location, particle,targetSet,runCount) -> {
        for (float f = 0; f < 360; f = (float) (f + 10)) {
            Location local = location.clone().add(Math.sin(Math.toRadians(f)) * 0.3, 2, Math.cos(Math.toRadians(f)) * 0.3);
            for(Player target:targetSet)target.spawnParticle(particle,local,0,0,0,0,0);
        }
    };

    public final ParticleAction wingParticle = (location, particle,targetSet,runCount) -> {
        double angle = Math.toRadians(getAngle(location.getYaw()));
        for (double[] d : wingParticleList) {
            Location local = location.clone().add((Math.sin(angle) * d[2]) + (Math.cos(angle) * d[0]), d[1], ((Math.cos(angle) * d[2]) - (Math.sin(angle) * d[0])));
            for(Player target:targetSet)target.spawnParticle(particle,local,0,0,0,0,0);
        }
    };


    public final ParticleAction shieldParticle = (location, particle,targetSet,runCount) -> {
        double angle = Math.toRadians((runCount % 10)*36);
        for (double[] d : shieldParticleList) {
            Location local = location.clone().add((Math.sin(angle) * d[2]) * 1 + (Math.cos(angle) * d[0]) * 1, d[1], ((Math.cos(angle) * d[2] * 1) - (Math.sin(angle) * d[0]) * 1));
            for(Player target:targetSet)target.spawnParticle(particle,local,0,0,0,0,0);
        }
    };




    @FunctionalInterface
    public interface ParticleAction {
        void display(Location location, org.bukkit.Particle particle, HashSet<Player> targetSet,int count);
    }

    public double getAngle(double Yaw){
        if(Yaw>0)return Math.abs(((Yaw)%360)-360);
        else return Math.abs(Yaw) % 360;
    }

}
