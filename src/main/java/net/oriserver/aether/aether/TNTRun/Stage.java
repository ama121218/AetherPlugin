package net.oriserver.aether.aether.TNTRun;

public class Stage {
    private String name;
    private double x1,y1,z1,x2,y2,z2;
    private int maxPlayer;
    private int minPlayer;
    private double speed;
    private int deathLine;
    private double sx,sy,sz;

    Stage(String name,double x1,double y1,double z1,double x2,double y2,double z2,int maxPlayer,int minPlayer,double speed,int deathLine,double sx,double sy,double sz){
        this.name = name;
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
        this.maxPlayer = maxPlayer;
        this.minPlayer = minPlayer;
        this.speed = speed;
        this.deathLine = deathLine;
        this.sx = sx;
        this.sy = sy;
        this.sz = sz;
    }

    public String getName() {
        return name;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getZ1() {
        return z1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public double getZ2() {
        return z2;
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }

    public int getMinPlayer() {
        return minPlayer;
    }

    public double getSpeed() {
        return speed;
    }

    public int getDeathLine() {
        return deathLine;
    }

    public double getSx() {
        return sx;
    }

    public double getSy() {
        return sy;
    }

    public double getSz() {
        return sz;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public void setZ1(double z1) {
        this.z1 = z1;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public void setZ2(double z2) {
        this.z2 = z2;
    }

    public void setMaxPlayer(int maxPlayer) {
        this.maxPlayer = maxPlayer;
    }

    public void setMinPlayer(int minPlayer) {
        this.minPlayer = minPlayer;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDeathLine(int deathLine) {
        this.deathLine = deathLine;
    }

    public void setSx(double sx) {
        this.sx = sx;
    }

    public void setSy(double sy) {
        this.sy = sy;
    }

    public void setSz(double sz) {
        this.sz = sz;
    }


}
