package net.oriserver.aether.aether.TNTRun;

public class Game {
    /*前バージョンからの移植進行中
    public void gamestart(){
        BukkitRunnable task_start = new BukkitRunnable() {
            @Override
            public void run() {
                if(TNTRUNPlayer.size()<minplayer){
                    for (org.bukkit.entity.Player player : TNTRUNPlayer) {
                        player.sendMessage("人数が足りなくなったためプレイヤーの入室を待ちます。");
                        task_start_boolean = false;
                        this.cancel();
                    }
                }
                else if (lobbystartcount == 0) {
                    for (org.bukkit.entity.Player player : TNTRUNPlayer) {
                        player.sendMessage("アリーナへテレポートします。");
                        player.teleport(location_startposition);
                        lobby_player = false;
                    }
                    setStart();
                    this.cancel();
                }
                else {
                    for (org.bukkit.entity.Player player : TNTRUNPlayer) {
                        player.sendMessage(String.valueOf(lobbystartcount));
                    }
                }
                lobbystartcount--;
            }
        };
        if(task_start_boolean)return;
        task_start_boolean = true;
        task_start.runTaskTimer(AmaPlugin.getPlugin(), 0L, 20L);
    }
    public void setStart(){
            BukkitRunnable task = new BukkitRunnable() {
                @Override
                public void run() {
                    if (startcount == 0) {
                        for (org.bukkit.entity.Player player : TNTRUNPlayer) {
                            player.sendMessage("ゲームスタート");
                        }
                        rungame();
                        this.cancel();
                    }
                    else {
                        for (org.bukkit.entity.Player player : TNTRUNPlayer) {
                            player.sendMessage(String.valueOf(startcount));
                        }
                    }
                    startcount--;
                }
            };
            task.runTaskTimer(AmaPlugin.getPlugin(), 0L, 20L);
    }
    private void rungame() {
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                if(TNTRUNPlayer.size()<=1){
                    this.cancel();
                    gameover();
                }
                for (org.bukkit.entity.Player player : TNTRUNPlayer) {
                    if (!player.isOnline()) {
                        outtntrunplayer(player);
                        return;
                    }
                    if (player.isOnGround()&&player.getLocation().getWorld().getName().equals("tntrun")){
                        Bukkit.getServer().getLogger().info(" "+ player.getLocation().getY() +" "+deathline+" "+player.getLocation().getX()+" "+xx1+" " + xx2+" "+player.getLocation().getZ()+" "+ zz1+" "+ zz2);
                        if ((player.getLocation().getY() <= deathline) || (player.getLocation().getX() < -49) || (player.getLocation().getX() > -49+xsize) || (player.getLocation().getZ() < 101) || (player.getLocation().getZ() > 101+zsize)) {
                            setTNTRUNPlayer_spectator(player);
                            outtntrunplayer(player);
                            return;
                        }
                        breakblock_death(player.getLocation().getX(),player.getLocation().getY(),player.getLocation().getZ());
                    }
                }
            }
        };
        task.runTaskTimer(AmaPlugin.getPlugin(), 0L, 1L);
    }

    private void breakblock_death(Double x,Double y,Double z){
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getServer().getLogger().info(" "+x+" "+y+" "+z);
                Bukkit.getServer().getLogger().info(""+xx1+" "+xx2+" "+yy1+" "+yy2+" "+zz1+" "+zz2+" ");
                if (TNTRUNPlayer.size() <= 1) return;
                Location location = new Location(Bukkit.getWorld("tntrun"),x,y,z);
                location.setY(y - 0.1);
                Location locationb = location.clone();
                locationb.setX(x + 0.299);
                locationb.setZ(z + 0.299);
                if (location.getBlock().getType() == Material.BARRIER) return;
                locationb.getBlock().setType(Material.AIR);
                locationb = location.clone();
                locationb.setX(x + 0.299);
                locationb.setZ(z - 0.299);
                locationb.getBlock().setType(Material.AIR);
                locationb = location.clone();
                locationb.setX(x - 0.299);
                locationb.setZ(z + 0.299);
                locationb.getBlock().setType(Material.AIR);
                locationb = location.clone();
                locationb.setX(x - 0.299);
                locationb.setZ(z - 0.299);
                locationb.getBlock().setType(Material.AIR);
            }
        };
        task.runTaskLater(AmaPlugin.getPlugin(), (long) (20*0.5));
    }
    public void gameover(){
        Location location = new Location(Bukkit.getWorld("tntrun"),0,10,0);
        for(org.bukkit.entity.Player player :TNTRUNPlayer_spectator) {
            player.setFlying(false);
            player.teleport(location);
            player.sendMessage(ChatColor.YELLOW + "1th : " + TNTRUNPlayer.get(0).getName());
        }
        Player win = TNTRUNPlayer.get(0);
        win.setFlying(false);
        win.teleport(location);
        win.sendMessage(ChatColor.YELLOW +"1th : "+win.getName());
        stageclear();
        tntrun_player_reset();
        task_start_boolean = false;
    }
    public void stageclear(){
        Location locala = new Location(Bukkit.getWorld("tntrun"),-49,4,101);
        Location locals = new Location(Bukkit.getWorld("tntrun"),-49,4,101);
        for(double n=0;n<= xsize-1;n++){
            for(double m=0;m<=ysize-1;m++){
                for(double l=0;l<=zsize-1;l++){
                    locals.setX(locala.getX()+n);
                    locals.setY(locala.getY()+m);
                    locals.setZ(locala.getZ()+l);
                    Block block = locals.getBlock();
                    block.setType(Material.AIR);
                }
            }
        }
    }
    public void tntrun_player_reset() {
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                TNTRUNPlayer =null;
                TNTRUNPlayer_spectator = null;
            }
        };
        task.runTaskLater(AmaPlugin.getPlugin(), 20);
    }
     */
}
