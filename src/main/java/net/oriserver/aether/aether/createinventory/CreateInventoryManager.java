package net.oriserver.aether.aether.createinventory;

import org.bukkit.entity.Player;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CreateInventoryManager {//プレイヤーのインベントリーを拡張するシステム

    HashMap<String, CreateInventory> createInventoryMap = new HashMap<String, CreateInventory>();

    public void createInventory(Player p,String s){
        if(createInventoryMap.get(String.valueOf(p.getUniqueId()))==null){
            CreateInventory createInventory = new CreateInventory(p);
            createInventory.createInventory(p,s);
            createInventoryMap.put(String.valueOf(p.getUniqueId()),createInventory);
        }else{
            createInventoryMap.get(String.valueOf(p.getUniqueId())).createInventory(p,s);
        }
    }
    public void setInventory1(Player p){
        if(createInventoryMap.get(String.valueOf(p.getUniqueId()))==null)p.sendMessage("保存されているインベントリーはありません");
        else createInventoryMap.get(String.valueOf(p.getUniqueId())).setInventory1(p);
    }
    public void setInventory2(Player p){
        if(createInventoryMap.get(String.valueOf(p.getUniqueId()))==null)p.sendMessage("保存されているインベントリーはありません");
        else createInventoryMap.get(String.valueOf(p.getUniqueId())).setInventory2(p);
    }
    public void deleteInventory(Player p){
        if(createInventoryMap.get(String.valueOf(p.getUniqueId()))==null)p.sendMessage("保存されているインベントリーはありません");
        else createInventoryMap.get(String.valueOf(p.getUniqueId())).deleteInventory(p);
    }
    public void changeName(Player p,String s){
        if(createInventoryMap.get(String.valueOf(p.getUniqueId()))==null){
            CreateInventory createInventory = new CreateInventory(p);
            createInventory.changeName(s);
            createInventoryMap.put(String.valueOf(p.getUniqueId()),createInventory);
        }else{
            createInventoryMap.get(String.valueOf(p.getUniqueId())).changeName(s);
        }
    }
}
