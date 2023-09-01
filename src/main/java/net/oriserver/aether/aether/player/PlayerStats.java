package net.oriserver.aether.aether.player;

import net.oriserver.aether.aether.statics.Item;
import net.oriserver.aether.aether.sqlite.SQLiteManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PlayerStats {
    private Player player;
    private int level;
    private int global;
    private int chart;
    private int star;
    private int AP;

    private int jumpcount;
    private String location;
    private Long past_time;

    private String permission;
    private String status;

    private String chatroom;
    private String[] friend;
    private String[] headblock;

    private boolean particleonoff;
    private boolean mailonoff;
    private boolean friendonoff;
    private boolean chatroomonoff;
    private boolean playersidebaronoff;

    private PlayerSidebar playerSidebar;
    private Long join_time;

    private int phone;
    private int partition;
    private int checkpoint;

    private String language;

    PlayerStats(Player player,SQLiteManager sqLiteManager){
        this.player = player;
        String uuid = String.valueOf(player.getUniqueId());

        ArrayList<Integer> arrayList_R = sqLiteManager.getPlayerDBManagerR().getPlayerData(uuid);
        if(arrayList_R==null)return;
        this.level = arrayList_R.get(0);
        this.global = arrayList_R.get(1);
        this.chart = arrayList_R.get(2);
        this.star = arrayList_R.get(3);
        this.AP = arrayList_R.get(4);

        ArrayList<Object> arrayList_JQ = sqLiteManager.getPlayerDBManagerJQ().getData(uuid);
        if(arrayList_JQ==null)return;
        this.jumpcount = (int) arrayList_JQ.get(0);
        this.location = (String) arrayList_JQ.get(1);
        this.past_time = (long) arrayList_JQ.get(2);

        ArrayList<Object> arrayList_Setting = sqLiteManager.getPlayerDBManagerSetting().getPlayerData(uuid);
        if(arrayList_Setting==null)return;
        this.particleonoff = (boolean) arrayList_Setting.get(0);
        this.mailonoff = (boolean) arrayList_Setting.get(1);
        this.friendonoff = (boolean) arrayList_Setting.get(2);
        this.chatroomonoff = (boolean) arrayList_Setting.get(3);
        this.playersidebaronoff = (boolean) arrayList_Setting.get(4);

        ArrayList<Object> arrayList_Phone = sqLiteManager.getPhoneSetting().getData(uuid);
        if(arrayList_Phone==null)return;
        this.phone = (int) arrayList_Phone.get(0);
        this.partition = (int) arrayList_Phone.get(1);
        this.checkpoint = (int) arrayList_Phone.get(2);

        ArrayList<Object> arrayList_HeadBlock = sqLiteManager.getPlayerDBManagerHeadBlock().getData(uuid);
        if(arrayList_HeadBlock==null)return;
        this.headblock = new String[]{String.valueOf(arrayList_HeadBlock.get(0)),
                String.valueOf(arrayList_HeadBlock.get(1)),
                String.valueOf(arrayList_HeadBlock.get(2)),
                String.valueOf(arrayList_HeadBlock.get(3))
        };

        Item.player_partition.put(uuid,this.partition);
        this.chatroom = this.chatroomonoff ? "" : "General";
        this.playerSidebar = new PlayerSidebar(player.getName(),this.level,this.global,this.star,this.AP,this.chatroom,this.location);


    }

    public Player getPlayer() {return this.player;}
    public int getLevel() {return this.level;}
    public void setLevel(int level) {
        this.level = level;
        this.playerSidebar.setlevel(level);
        setListName();
    }
    public int getGlobal() {return this.global;}
    public void setGlobal(int global) {
        this.global = global;
        this.playerSidebar.setGlobal(global);
        //
    }
    public int getChart() {return this.chart;}
    public void setChart(int chart) {this.chart = chart;}
    public int getStar() {return this.star;}
    public void setStar(int star) {
        this.star = star;
        this.playerSidebar.setStar(star);
        //listname
    }
    public int getAetherpoint() {return this.AP;}
    public void setAetherpoint(int aetherpoint) {
        this.AP = aetherpoint;
        this.playerSidebar.setAP(aetherpoint);
        //listname
    }
    public int getJumpcount() {return this.jumpcount;}
    public void setJumpcount1() {this.jumpcount += 1;}
    public String getLocation() {return this.location;}
    public void setLocation(String location) {
        this.location = location;
        this.getPlayerSidebar().setLocation(location);
    }
    public Long getPast_time() {return this.past_time;}
    public void setPast_time(Long past_time) {this.past_time = past_time;}
    public String getStatus(){return this.status;}
    public void setStatus(String status){this.status = status;}
    public String getChatroom(){return this.chatroom;}
    public void setChatroom(String chatroom){
        this.chatroom = chatroom;
        this.playerSidebar.setChatroom(chatroom.equals("")?"--":chatroom);
    }


    public boolean[] getSetting(){
        boolean[] booleans = new boolean[5];
        booleans[0] = particleonoff;
        booleans[1] = mailonoff;
        booleans[2] = friendonoff;
        booleans[3] = chatroomonoff;
        booleans[4] = playersidebaronoff;
        return booleans;
    }

    public boolean isParticleonoff() {return this.particleonoff;}
    public void setParticleonoff(boolean particleonoff) {this.particleonoff = particleonoff;}
    public boolean isMailonoff() {return this.mailonoff;}
    public void setMailonoff(boolean mailonoff) {this.mailonoff = mailonoff;}
    public boolean isFriendonoff() {return this.friendonoff;}
    public void setFriendonoff(boolean friendonoff) {this.friendonoff = friendonoff;}
    public boolean isChatroomonoff() {return this.chatroomonoff;}
    public void setChatroomonoff(boolean chatroomonoff) {this.chatroomonoff = chatroomonoff;}
    public boolean isPlayersidebaronoff() {return this.playersidebaronoff;}
    public void setPlayersidebaronoff(boolean playersidebaronoff) {this.playersidebaronoff = playersidebaronoff;}

    public int getPhone(){return this.phone;}
    public void setPhone(int phone){this.phone = phone;}
    public int getPartition(){return this.partition;}
    public void setPartition(int partition){this.partition = partition;}
    public int getCheckpoint(){return this.checkpoint;}
    public void setCheckpoint(int checkpoint){this.checkpoint = checkpoint;}

    public PlayerSidebar getPlayerSidebar() {
        return playerSidebar;
    }

    public String[] getHeadblock(){return this.headblock;}
    public void setHeadblock(String[] headblock){this.headblock = headblock;}

    public void setListName(){
        if(this.level==121){
            player.setPlayerListName(player.getName()+ ChatColor.GRAY+" ["+ChatColor.AQUA+ChatColor.BOLD+120+"+"+ChatColor.GRAY+"] ");
        }else if(this.level>=122){
            player.setPlayerListName(player.getName()+ ChatColor.GRAY+" ["+ChatColor.RED+ChatColor.BOLD+(level-121)+ChatColor.GRAY+"] ");
        }else{
            player.setPlayerListName(player.getName()+ ChatColor.GRAY+" ["+ChatColor.AQUA+ChatColor.BOLD+level+ChatColor.GRAY+"] ");
        }
    }
    public void setPlayer(Player player){this.player = player;}

    public Long getJoin_time(){return this.join_time;}
    public void setJoin_time(Long time){this.join_time=time;}
}
