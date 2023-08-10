package net.oriserver.aether.aether.player;

import net.oriserver.aether.aether.Item;
import net.oriserver.aether.aether.sqlite.PlayerDBManagerJQ;
import net.oriserver.aether.aether.sqlite.PlayerDBManagerR;
import net.oriserver.aether.aether.sqlite.SQLiteManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PlayerStats {
    private final Player player;
    private int level;
    private int global;
    private int chart;
    private int star;
    private int aetherpoint;

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
        this.aetherpoint = arrayList_R.get(4);

        ArrayList<Object> arrayList_JQ = sqLiteManager.getPlayerDBManagerJQ().getPlayerData(uuid);
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

        ArrayList<Object> arrayList_Phone = sqLiteManager.getPlayerDBManagerPhone().getData(uuid);
        if(arrayList_Phone==null)return;
        this.phone = (int) arrayList_Phone.get(0);
        this.partition = (int) arrayList_Phone.get(1);
        this.checkpoint = (int) arrayList_Phone.get(2);

        Item.player_partition.put(uuid,this.partition);

        this.playerSidebar = new PlayerSidebar();
    }

    public Player getPlayer() {return this.player;}
    public int getLevel() {return this.level;}
    public void setLevel(int level) {this.level = level;}
    public int getGlobal() {return this.global;}
    public void setGlobal(int global) {this.global = global;}
    public int getChart() {return this.chart;}
    public void setChart(int chart) {this.chart = chart;}
    public int getStar() {return this.star;}
    public void setStar(int star) {this.star = star;}
    public int getAetherpoint() {return this.aetherpoint;}
    public void setAetherpoint(int aetherpoint) {this.aetherpoint = aetherpoint;}
    public int getJumpcount() {return this.jumpcount;}
    public void setJumpcount(int jumpcount) {this.jumpcount = jumpcount;}
    public String getLocation() {return this.location;}
    public void setLocation(String location) {this.location = location;}
    public Long getPast_time() {return this.past_time;}
    public void setPast_time(Long past_time) {this.past_time = past_time;}
    public String getStatus(){return this.status;}
    public void setStatus(String status){this.status = status;}


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



    public String[] getHeadblock(){return this.headblock;}
    public void setHeadblock(String[] headblock){this.headblock = headblock;}


}
