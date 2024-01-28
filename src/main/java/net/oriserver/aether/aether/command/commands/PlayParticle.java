package net.oriserver.aether.aether.command.commands;

import net.oriserver.aether.aether.particle.ParticleManager;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class PlayParticle  implements CommandExecutor{
    private final ParticleManager particleManager;
    Particle[] particles;
    public PlayParticle(ParticleManager particleManager){
        this.particleManager = particleManager;
        particles = Particle.values();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage("このコマンドはプレイヤーのみ使用可能");
            return true;
        }
        Player player = (Player) sender;
        if(player.isOp()){
            if(args[0].equals("stop")){
                particleManager.cancelPlayerParticle(player);
                player.sendMessage("パーティクルを止めました。");
                return true;
            }
            if(args.length == 1){
                try {
                    int kind = Integer.parseInt(args[0]);
                    if(!(kind>=1&&kind<=particles.length)){
                        player.sendMessage("今は"+particles.length+"種類までです。");
                        return true;
                    }else{
                        particleManager.setPlayerParticle(player,particles[kind-1],particleManager.getParticle().circleParticle);
                    }
                }catch (NumberFormatException e){
                    player.sendMessage("数字のみです");
                }
            }
            else if(args.length == 2){
                try {
                    int kind = Integer.parseInt(args[0]);
                    int i = Integer.parseInt(args[1]);
                    if(!(kind>=1&&kind<=particles.length)){
                        player.sendMessage("今は"+particles.length+"種類までです。");
                        return true;
                    }
                    if(i==1){
                        particleManager.setPlayerParticle(player,particles[kind-1],particleManager.getParticle().circleParticle);
                    }else if(i==2){
                        particleManager.setPlayerParticle(player,particles[kind-1],particleManager.getParticle().ringParticle);
                    }else if(i==3){
                        particleManager.setPlayerParticle(player,particles[kind-1],particleManager.getParticle().wingParticle);
                    }else if(i==4){
                        particleManager.setPlayerParticle(player,particles[kind-1],particleManager.getParticle().shieldParticle);
                    }else{
                        player.sendMessage("今は４種類までです。");return true;
                    }
                }
                catch (NumberFormatException e){
                    player.sendMessage("数字のみです");
                }
            }
            else{
                player.sendMessage("無効なコマンドです");
            }
        }
        return true;
    }
}
